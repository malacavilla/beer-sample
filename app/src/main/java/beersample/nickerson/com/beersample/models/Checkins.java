package beersample.nickerson.com.beersample.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Represents the checkins of a user.
 */
public class Checkins {

    @SerializedName("count")
    public long count;

    @SerializedName("items")
    public ArrayList<CheckinItem> items;
}
