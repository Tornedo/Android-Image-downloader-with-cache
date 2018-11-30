
package com.mv.demo.model.apiResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category implements Parcelable {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("photo_count")
    @Expose
    public Integer photoCount;
    @SerializedName("links")
    @Expose
    public Links_ links;
    public final static Parcelable.Creator<Category> CREATOR = new Creator<Category>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        public Category[] newArray(int size) {
            return (new Category[size]);
        }

    };

    protected Category(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.photoCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.links = ((Links_) in.readValue((Links_.class.getClassLoader())));
    }

    public Category() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeValue(photoCount);
        dest.writeValue(links);
    }

    public int describeContents() {
        return 0;
    }

}
