package beersample.nickerson.com.beersample.models;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a location in Untappd.
 */
public class UntappdLocation {

    @SerializedName("brewery_city")
    public String city;

    @SerializedName("brewery_state")
    public String state;

    @SerializedName("lat")
    public double latitude;

    @SerializedName("lng")
    public double longitude;
}
