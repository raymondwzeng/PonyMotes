//package com.dinsfire.ponymotes
//
//import android.annotation.SuppressLint
//
//class WebActivity : Activity() {
//    private var webView: WebView? = null
//    @SuppressLint("SetJavaScriptEnabled")
//    protected fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_web)
//        val bundle: Bundle = getIntent().getExtras()
//        val url: String = bundle.getString("url")
//        val title: String = bundle.getString("title")
//        setTitle(title)
//        webView = findViewById(R.id.webview) as WebView?
//        val webSettings: WebSettings = webView.getSettings()
//        webSettings.setJavaScriptEnabled(true)
//        webView.loadUrl(url)
//    }
//}