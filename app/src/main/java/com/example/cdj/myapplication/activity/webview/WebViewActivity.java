package com.example.cdj.myapplication.activity.webview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.cdj.myapplication.R;
import com.example.cdj.myapplication.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description :
 * Created by vic
 * Created Time 2017/6/30
 */

public class WebViewActivity extends BaseActivity {
	@Bind(R.id.webview)
	WebView webView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base_web_view);
		ButterKnife.bind(this);
		loadHtml();
	}

	public void loadHtml() {
//		WebView webView = new WebView(this);

		WebSettings webSettings = webView.getSettings();
		webSettings.setBlockNetworkImage(false);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setBuiltInZoomControls(true);
		webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setSavePassword(true);
		webSettings.setSaveFormData(true);
		webSettings.setGeolocationEnabled(true);
//		webSettings.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");     // enable Web Storage: localStorage, sessionStorage
		webSettings.setDomStorageEnabled(true);
		webView.requestFocus();
		webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_INSET);
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
//		webView.requestFocus();
		webView.loadUrl("file:////android_asset/test.html");
		webView.zoomOut();
//		webView.setWebViewClient(new WebViewClient() {
//			@Override
//			public boolean shouldOverrideUrlLoading(WebView view, String url) {
//				// Otherwise allow the OS to handle things like tel, mailto,
//				// etc.
//				return true;
//			}
//		});
//		webView.loadUrl("www.baidu.com");
	}
}
