package beersample.nickerson.com.beersample.models;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a beer in Untappd.
 */
public class Beer {

    @SerializedName("bid")
    public long id;

    @SerializedName("beer_name")
    public String name;

    @SerializedName("beer_label")
    public String label;

    @SerializedName("beer_style")
    public String style;

    @SerializedName("beer_abv")
    public double abv;

    @SerializedName("auth_rating")
    public int rating;

    @SerializedName("wish_list")
    public boolean wishList;

    @SerializedName("beer_active")
    public int active;
}
