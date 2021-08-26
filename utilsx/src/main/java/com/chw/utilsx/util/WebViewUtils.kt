package com.chw.utilsx.util

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import java.lang.reflect.Method

/**
 * @author hanwei.chen
 * 2021/8/23 16:21
 */
object WebViewUtils {
    private const val TAG = "WebViewUtils"

    @SuppressLint("SoonBlockedPrivateApi", "PrivateApi", "DiscouragedPrivateApi")
    fun hookWebView() {
        val sdkInt = Build.VERSION.SDK_INT
        try {
            val factoryClass = Class.forName("android.webkit.WebViewFactory")
            val field = factoryClass.getDeclaredField("sProviderInstance")
            field.isAccessible = true
            var sProviderInstance = field[null]
            if (sProviderInstance != null) {
                Log.i(TAG, "sProviderInstance isn't null")
                return
            }
            val getProviderClassMethod: Method = when {
                sdkInt > Build.VERSION_CODES.LOLLIPOP_MR1 -> factoryClass.getDeclaredMethod("getProviderClass")
                sdkInt == Build.VERSION_CODES.LOLLIPOP_MR1 -> factoryClass.getDeclaredMethod("getFactoryClass")
                else -> {
                    Log.i(TAG, "Don't need to Hook WebView")
                    return
                }
            }
            getProviderClassMethod.isAccessible = true
            val factoryProviderClass = getProviderClassMethod.invoke(factoryClass) as Class<*>
            val delegateClass = Class.forName("android.webkit.WebViewDelegate")
            val delegateConstructor = delegateClass.getDeclaredConstructor()
            delegateConstructor.isAccessible = true
            if (sdkInt >= Build.VERSION_CODES.O) {
                val chromiumMethodName =
                    factoryClass.getDeclaredField("CHROMIUM_WEBVIEW_FACTORY_METHOD")
                chromiumMethodName.isAccessible = true
                var chromiumMethodNameStr = chromiumMethodName[null] as? String
                if (chromiumMethodNameStr == null) {
                    chromiumMethodNameStr = "create"
                }
                val staticFactory =
                    factoryProviderClass.getMethod(chromiumMethodNameStr, delegateClass)
                if (staticFactory != null) {
                    sProviderInstance =
                        staticFactory.invoke(null, delegateConstructor.newInstance())
                }
            } else {
                val providerConstructor = factoryProviderClass.getConstructor(delegateClass)
                if (providerConstructor != null) {
                    providerConstructor.isAccessible = true
                    sProviderInstance =
                        providerConstructor.newInstance(delegateConstructor.newInstance())
                }
            }
            if (sProviderInstance != null) {
                field["sProviderInstance"] = sProviderInstance
                Log.i(TAG, "Hook success!")
            } else {
                Log.i(TAG, "Hook failed!")
            }
        } catch (e: Throwable) {
            Log.w(TAG, e)
        }
    }

    fun destroyWebView(webView: WebView?) {
        if (webView == null) return
        try {
            (webView.parent as? ViewGroup)?.removeAllViews()
            webView.stopLoading()
            webView.settings.javaScriptEnabled = false
            webView.clearHistory()
            webView.clearView()
            webView.removeAllViews()
            webView.destroy()
        } catch (e: Exception) {
        }
    }

    fun initWebView(webView: WebView?) {
        if (webView == null) return
        webView.settings.displayZoomControls = false
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView.settings.defaultTextEncodingName = "UTF-8"
        webView.settings.blockNetworkImage = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
    }
}