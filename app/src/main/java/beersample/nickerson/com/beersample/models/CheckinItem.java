package beersample.nickerson.com.beersample.models;

import com.google.gson.annotations.SerializedName;

/**
 * Represents an item that was checked in.
 */
public class CheckinItem {

    @SerializedName("beer")
    public Beer beer;

    @SerializedName("brewery")
    public Brewery brewery;
}
