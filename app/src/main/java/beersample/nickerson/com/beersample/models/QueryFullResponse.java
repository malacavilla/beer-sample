package beersample.nickerson.com.beersample.models;

import com.google.gson.annotations.SerializedName;

/**
 * Main request for querying a user's feed.
 */
public final class QueryFullResponse {

    @SerializedName("response")
    public QueryResponse response;
}
