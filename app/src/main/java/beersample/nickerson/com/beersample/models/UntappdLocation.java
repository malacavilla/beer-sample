package beersample.nickerson.com.beersample.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a location in Untappd.
 */
public class UntappdLocation implements Parcelable {

    @SerializedName("brewery_city")
    public String city;

    @SerializedName("brewery_state")
    public String state;

    @SerializedName("lat")
    public double latitude;

    @SerializedName("lng")
    public double longitude;

    public UntappdLocation() {

    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(city);
        out.writeString(state);
        out.writeDouble(latitude);
        out.writeDouble(longitude);
    }

    public static final Parcelable.Creator<UntappdLocation> CREATOR = new Parcelable
            .Creator<UntappdLocation>() {
        public UntappdLocation createFromParcel(Parcel in) {
            return new UntappdLocation(in);
        }

        public UntappdLocation[] newArray(int size) {
            return new UntappdLocation[size];
        }
    };

    private UntappdLocation(Parcel in) {
        city = in.readString();
        state = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }
}
