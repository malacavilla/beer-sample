package beersample.nickerson.com.beersample.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a brewery.
 */
public class Brewery implements Parcelable {

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

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(id);
        out.writeString(name);
        out.writeString(slug);
        out.writeString(label);
        out.writeString(country);
        out.writeParcelable(location, flags);
        out.writeInt(active);
    }

    public static final Parcelable.Creator<Brewery> CREATOR = new Parcelable
            .Creator<Brewery>() {
        public Brewery createFromParcel(Parcel in) {
            return new Brewery(in);
        }

        public Brewery[] newArray(int size) {
            return new Brewery[size];
        }
    };

    private Brewery(Parcel in) {
        id = in.readLong();
        name = in.readString();
        slug = in.readString();
        label = in.readString();
        country = in.readString();
        location = in.readParcelable(UntappdLocation.class.getClassLoader());
        active = in.readInt();
    }
}
