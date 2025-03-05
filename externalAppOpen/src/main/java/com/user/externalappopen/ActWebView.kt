package com.user.externalappopen

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.MotionEffect
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

internal class ActWebView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.act_web_view)
        val webView : WebView = findViewById(R.id.webView)
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true

        webView.webViewClient= WebViewClient()
        webView.addJavascriptInterface(WebAppInterface(this),"AndroidInterface")

        val param1 = intent.getStringExtra("param1") ?: ""
        val param2 = intent.getStringExtra("param2") ?: ""

        val url = "https://chamodh98.github.io/TestSigleWeb?param1=$param1&param2=$param2"
        Log.e("URL", url )
        webView.loadUrl(url)


    }

    fun openApp(paramA: String) {
        if (isAppInstalled(this, "com.ipay.mobile")) {
//            finish()
            val intent = Intent()
            intent.component = ComponentName("com.ipay.mobile", "com.ipay.mobile.v2.view.ActSplash")
//            Add Extra
            intent.data = Uri.parse("https://ipay.lk/sdkPayment/$paramA")

            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Log.e(MotionEffect.TAG, "onCreate: App not Found")
            }
        } else {
            val webView2 : WebView = findViewById(R.id.webView)
            val url = "https://ipay.lk"
            Log.e("URL", url )
            webView2.loadUrl(url)
        }


    }

    inner class WebAppInterface(private val activity: ActWebView) {

        @JavascriptInterface
        fun receiveParams(param: String) {
            activity.runOnUiThread {
                Toast.makeText(activity,"Received -: $param", Toast.LENGTH_LONG).show()
            }
            openApp(param)
        }
    }

    fun isAppInstalled(context: Context, packageName: String): Boolean {
        return context.packageManager.getLaunchIntentForPackage(packageName) != null
    }
}