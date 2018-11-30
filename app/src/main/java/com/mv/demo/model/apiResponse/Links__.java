
package com.mv.demo.model.apiResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links__ implements Parcelable {

    @SerializedName("self")
    @Expose
    public String self;
    @SerializedName("html")
    @Expose
    public String html;
    @SerializedName("download")
    @Expose
    public String download;
    public final static Parcelable.Creator<Links__> CREATOR = new Creator<Links__>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Links__ createFromParcel(Parcel in) {
            return new Links__(in);
        }

        public Links__[] newArray(int size) {
            return (new Links__[size]);
        }

    };

    protected Links__(Parcel in) {
        this.self = ((String) in.readValue((String.class.getClassLoader())));
        this.html = ((String) in.readValue((String.class.getClassLoader())));
        this.download = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Links__() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(self);
        dest.writeValue(html);
        dest.writeValue(download);
    }

    public int describeContents() {
        return 0;
    }

}
