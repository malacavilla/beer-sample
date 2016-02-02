package beersample.nickerson.com.beersample.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Represents an item that was checked in.
 */
public class CheckinItem implements Parcelable {

    @SerializedName("beer")
    public Beer beer;

    @SerializedName("brewery")
    public Brewery brewery;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(beer, flags);
        out.writeParcelable(brewery, flags);
    }

    public static final Parcelable.Creator<CheckinItem> CREATOR = new Parcelable
            .Creator<CheckinItem>() {
        public CheckinItem createFromParcel(Parcel in) {
            return new CheckinItem(in);
        }

        public CheckinItem[] newArray(int size) {
            return new CheckinItem[size];
        }
    };

    private CheckinItem(Parcel in) {
        beer = in.readParcelable(Beer.class.getClassLoader());
        brewery = in.readParcelable(Brewery.class.getClassLoader());
    }
}
