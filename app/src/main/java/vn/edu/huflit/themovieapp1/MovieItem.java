package vn.edu.huflit.themovieapp1;

import com.google.gson.annotations.SerializedName;

public class MovieItem extends Entertainment {
    @SerializedName("title")
    public String title;
    @SerializedName("original_title")
    public String original_title;
    @SerializedName("release_date")
    public String release_date;
    @SerializedName("video")
    public Boolean video;
    @SerializedName("adult")
    public Boolean adult;

    public String toString() {
        return String.format("id: %s, title: %s, original_title: %s, media_type: %s", id, title, original_title, media_type);
    }
}
