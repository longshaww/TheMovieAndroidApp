package vn.edu.huflit.themovieapp1;

import com.google.gson.annotations.SerializedName;

public class TVItem extends Entertainment {
    @SerializedName("name")
    public String name;
    @SerializedName("original_name")
    public String original_name;
    @SerializedName("first_air_date")
    public String first_air_date;
    @SerializedName("origin_country")
    public String[] origin_country;

    public TVItem(String id, String type, String name, String poster_path, String description, double vote) {
        super();
        this.id = id;
        this.media_type = type;
        this.name = name;
        this.poster_path = poster_path;
        this.overview = description;
        this.vote_average = vote;
    }

    @Override
    public String toString() {
        return String.format("id: %s, title: %s, original_title: %s, media_type: %s", id, name, original_name, media_type);
    }
}
