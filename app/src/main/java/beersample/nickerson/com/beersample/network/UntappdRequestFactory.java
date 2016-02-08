package beersample.nickerson.com.beersample.network;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONObject;

import beersample.nickerson.com.beersample.PreferenceKeys;
import beersample.nickerson.com.beersample.R;
import beersample.nickerson.com.beersample.models.AccessTokenFullResponse;
import beersample.nickerson.com.beersample.models.QueryFullResponse;

/**
 * Factory for creating Untappd API requests.
 */
public final class UntappdRequestFactory {
    private final static String AUTHORIZE_FORMAT = "https://untappd" +
            ".com/oauth/authorize/?client_id=%s&client_secret=%s&response_type=code" +
            "&redirect_url=%s&code=%s";

    private final static String QUERY_USER_FEED_FORMAT = "https://api.untappd" +
            ".com/v4/user/checkins/%s?client_id=%s&client_secret=%s";

    private final static String RECENT_CHECKINS_FORMAT = "https://api.untappd" +
            ".com/v4/checkin/recent/?access_token=%s";

    public static GsonRequest<QueryFullResponse> getUserFeed(final Context context, final String
            userName, final Response.Listener<QueryFullResponse> responseListener) {
        final String url = String.format(QUERY_USER_FEED_FORMAT, userName, context.getString(R
                .string.client_id), context.getString(R.string.client_secret));

        return new GsonRequest<>(url, QueryFullResponse.class,
                null, responseListener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {
            }
        });
    }

    public static GsonRequest<AccessTokenFullResponse> getAccessToken(final Context context,
            final String redirectUrl, final String code,
            final Response.Listener<AccessTokenFullResponse> responseListener) {
        final String url = String.format(AUTHORIZE_FORMAT, context.getString(R.string.client_id),
                context.getString(R.string.client_secret), redirectUrl, code);

        return new GsonRequest<>(url, AccessTokenFullResponse.class,
                null, responseListener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {
            }
        });
    }

    public static GsonRequest<QueryFullResponse> getRecentCheckins(final Context context,
            final Response.Listener<QueryFullResponse> responseListener) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences(PreferenceKeys
                .SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
        final String accessToken = sharedPreferences.getString(PreferenceKeys
                .PREFERENCE_OAUTH_2_ACCESS_TOKEN, "");

        final String url = String.format(RECENT_CHECKINS_FORMAT, accessToken);

        return new GsonRequest<>(url, QueryFullResponse.class,
                null, responseListener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {
            }
        });
    }
}
