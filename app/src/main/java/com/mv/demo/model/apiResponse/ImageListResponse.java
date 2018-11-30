
package com.mv.demo.model.apiResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ImageListResponse implements Parcelable {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("width")
    @Expose
    public Integer width;
    @SerializedName("height")
    @Expose
    public Integer height;
    @SerializedName("color")
    @Expose
    public String color;
    @SerializedName("likes")
    @Expose
    public Integer likes;
    @SerializedName("liked_by_user")
    @Expose
    public Boolean likedByUser;
    @SerializedName("user")
    @Expose
    public User user;
    @SerializedName("current_user_collections")
    @Expose
    public List<Object> currentUserCollections = new ArrayList<Object>();
    @SerializedName("urls")
    @Expose
    public Urls urls;
    @SerializedName("categories")
    @Expose
    public List<Category> categories = new ArrayList<Category>();
    @SerializedName("links")
    @Expose
    public Links__ links;
    public final static Parcelable.Creator<ImageListResponse> CREATOR = new Creator<ImageListResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ImageListResponse createFromParcel(Parcel in) {
            return new ImageListResponse(in);
        }

        public ImageListResponse[] newArray(int size) {
            return (new ImageListResponse[size]);
        }

    };

    protected ImageListResponse(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.width = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.height = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.color = ((String) in.readValue((String.class.getClassLoader())));
        this.likes = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.likedByUser = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.user = ((User) in.readValue((User.class.getClassLoader())));
        in.readList(this.currentUserCollections, (java.lang.Object.class.getClassLoader()));
        this.urls = ((Urls) in.readValue((Urls.class.getClassLoader())));
        in.readList(this.categories, (Category.class.getClassLoader()));
        this.links = ((Links__) in.readValue((Links__.class.getClassLoader())));
    }

    public ImageListResponse() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(createdAt);
        dest.writeValue(width);
        dest.writeValue(height);
        dest.writeValue(color);
        dest.writeValue(likes);
        dest.writeValue(likedByUser);
        dest.writeValue(user);
        dest.writeList(currentUserCollections);
        dest.writeValue(urls);
        dest.writeList(categories);
        dest.writeValue(links);
    }

    public int describeContents() {
        return 0;
    }

}
