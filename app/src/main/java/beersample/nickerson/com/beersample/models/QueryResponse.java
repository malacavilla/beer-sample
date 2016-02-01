package beersample.nickerson.com.beersample.models;

import com.google.gson.annotations.SerializedName;

/**
 * Full query response object.
 */
public final class QueryResponse {

    @SerializedName("checkins")
    public Checkins checkins;
}
