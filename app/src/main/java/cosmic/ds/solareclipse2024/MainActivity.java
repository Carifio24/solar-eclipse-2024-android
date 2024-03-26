package cosmic.ds.solareclipse2024;

import android.os.Bundle;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import cosmic.ds.solareclipse2024.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

       final MainActivityBinding binding = MainActivityBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());

       // We need this WebViewClient so that URLs don't automatically get opened
       // inside of a browser
       binding.webview.setWebViewClient(new WebViewClient() {
           @Override
           public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
               view.loadUrl(request.getUrl().toString());
               return true;
           }
       });

       // For debugging purposes, it's nice to see console messages
       binding.webview.setWebChromeClient(new WebChromeClient() {
           @Override
           public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
               android.util.Log.d("SolarEclipse console", consoleMessage.message());
               return true;
           }
       });

       final WebSettings settings = binding.webview.getSettings();
       settings.setJavaScriptEnabled(true);
       settings.setAllowFileAccessFromFileURLs(true);
       settings.setDomStorageEnabled(true);

       binding.webview.loadUrl("https://projects.cosmicds.cfa.harvard.edu/solar-eclipse-2024/");
    }

}
