package beersample.nickerson.com.beersample.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONObject;

import beersample.nickerson.com.beersample.R;
import beersample.nickerson.com.beersample.models.QueryFullResponse;

/**
 * Created by cnick on 2/1/2016.
 */
public final class UntappdRequestFactory {
    private final static String QUERY_USER_FEED_FORMAT = "https://api.untappd" +
            ".com/v4/user/checkins/%s?client_id=%s&client_secret=%s";

    public static GsonRequest<QueryFullResponse> getUserFeed(final Context context, final String
            userName, final Response.Listener<QueryFullResponse> responseListener) {
        final String url = String.format(QUERY_USER_FEED_FORMAT, userName, context.getString(R
                .string.client_id), context.getString(R.string.client_secret));

        return new GsonRequest<>(url, QueryFullResponse.class,
                null, responseListener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {
                int ii = 0;
                ++ii;
            }
        });
    }
}
