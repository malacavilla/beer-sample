package beersample.nickerson.com.beersample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.volley.RequestQueue;

import javax.inject.Inject;

/**
 * Activity for logging in with OAuth
 */
public class OAuthLoginActivity extends AppCompatActivity {
    public static final String ARG_OAUTH_2_CODE = "oauth_2_code";

    private static String REDIRECT_URI = "http://localhost";
    private static String OAUTH_URL = "https://untappd.com/oauth/authenticate/";

    private WebView mWebView;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_oauth_login);

        mSharedPreferences = getSharedPreferences(PreferenceKeys.SHARED_PREFERENCE_KEY,
                MODE_PRIVATE);

        final String clientId = getString(R.string.client_id);

        mWebView = (WebView) findViewById(R.id.oauth_web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(OAUTH_URL + "?redirect_url=" + REDIRECT_URI +
                "&response_type=token&client_id=" + clientId);
        mWebView.setWebViewClient(new WebViewClient() {

            boolean authComplete = false;

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                if (url.contains("#access_token=") && authComplete != true) {
                    final Uri uri = Uri.parse(url);
                    final String fragment = uri.getFragment();
                    final String token = fragment.replace("access_token=", "");

                    authComplete = true;

                    final SharedPreferences.Editor edit = mSharedPreferences.edit();
                    edit.putString(PreferenceKeys.PREFERENCE_OAUTH_2_ACCESS_TOKEN, token);
                    edit.commit();

                    final Intent intent = new Intent(OAuthLoginActivity.this,
                            UserCheckinsActivity.class);
                    OAuthLoginActivity.this.startActivity(intent);
                }
            }
        });
    }
}
