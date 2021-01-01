package com.project.mywebview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO 2: tambahin webview
        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.javaScriptEnabled = true

        // TODO 4: tambahin client
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                view?.loadUrl("javascript:alert('Web Dicoding berhasil dimuat')")
            }
        }

        // TODO 5: tambahin chrome client
        webView.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(
                view: WebView?,
                url: String?,
                message: String?,
                result: JsResult?
            ): Boolean {
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
                result?.confirm()
                return true
            }
        }
        webView.loadUrl("https://www.dicoding.com")


    }
}