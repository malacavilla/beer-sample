package beersample.nickerson.com.beersample.models;

import com.google.gson.annotations.SerializedName;

/**
 * Main response for getting an OAuth 2.0 access token.
 */
public class AccessTokenFullResponse {

    @SerializedName("response")
    public AccessTokenResponse response;
}
