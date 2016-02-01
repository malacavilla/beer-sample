package beersample.nickerson.com.beersample.models;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a brewery.
 */
public class Brewery {

    @SerializedName("brewery_id")
    public long id;

    @SerializedName("brewery_name")
    public String name;

    @SerializedName("brewery_slug")
    public String slug;

    @SerializedName("brewery_label")
    public String label;

    @SerializedName("country_name")
    public String country;

    @SerializedName("location")
    public UntappdLocation location;

    @SerializedName("brewery_active")
    public int active;
}
