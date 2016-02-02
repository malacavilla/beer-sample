package beersample.nickerson.com.beersample.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a beer in Untappd.
 */
public class Beer implements Parcelable {

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

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(id);
        out.writeString(name);
        out.writeString(label);
        out.writeString(style);
        out.writeDouble(abv);
        out.writeInt(rating);
        out.writeInt(wishList ? 1 : 0);
        out.writeInt(active);
    }

    public static final Parcelable.Creator<Beer> CREATOR = new Parcelable
            .Creator<Beer>() {
        public Beer createFromParcel(Parcel in) {
            return new Beer(in);
        }

        public Beer[] newArray(int size) {
            return new Beer[size];
        }
    };

    private Beer(Parcel in) {
        id = in.readLong();
        name = in.readString();
        label = in.readString();
        style = in.readString();
        abv = in.readDouble();
        rating = in.readInt();
        wishList = in.readInt() == 1 ? true : false;
        active = in.readInt();
    }
}
