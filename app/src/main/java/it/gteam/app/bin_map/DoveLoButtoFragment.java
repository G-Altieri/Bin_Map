package it.gteam.app.bin_map;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import it.gteam.app.bin_map.databinding.FragmentCalendarBinding;
import it.gteam.app.bin_map.databinding.FragmentDovelobuttoBinding;


public class DoveLoButtoFragment extends Fragment {

    private FragmentDovelobuttoBinding binding;
    private WebView mywebView;

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            binding = FragmentDovelobuttoBinding.inflate(inflater, container, false);

            binding.webviewDovelobutto.setWebViewClient(new WebViewClient());
            binding.webviewDovelobutto.loadUrl("https://www.asmaq.it/dove-lo-butto");
            WebSettings webSettings=binding.webviewDovelobutto.getSettings();
            webSettings.setJavaScriptEnabled(true);

            return binding.getRoot();

        }


    public class mywebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view,url,favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}

