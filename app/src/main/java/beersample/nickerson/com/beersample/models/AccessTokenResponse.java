package beersample.nickerson.com.beersample.models;

import com.google.gson.annotations.SerializedName;

/**
 * Object containg the OAuth 2.0 access token.
 */
public final class AccessTokenResponse {

    @SerializedName("access_token")
    public String accessToken;
}
