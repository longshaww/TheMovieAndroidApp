//change package to your project package
package vn.edu.huflit.themovieapp1;

import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Entertainment contain basic property of the <b>movie</b>, <b>tv</b> and <b>person</b>
 */
public class Entertainment {
    @SerializedName("id")
    public String id;
    @SerializedName("vote_count")
    public int vote_count;
    @SerializedName("poster_path")
    public String poster_path;
    @SerializedName("overview")
    public String overview;
    @SerializedName("backdrop_path")
    public String backdrop_path;
    @SerializedName("original_language")
    public String original_language;
    @SerializedName("media_type")
    public String media_type = "";
    @SerializedName("genre_ids")
    public int[] genre_ids;
    @SerializedName("vote_average")
    public double vote_average;
    @SerializedName("popularity")
    public double popularity;
}

class PersonItem extends Entertainment {
    @SerializedName("adult")
    public boolean adult;
    /*
     * gender option
     * 0 - Not specified
     * 1 - Female
     * 2 - Male
     * */
    @SerializedName("gender")
    public int gender;
    /*
     * include: MovieItem, TVItem
     * using Factory class to create List
     * */
    public List<Entertainment> known_for;

    @SerializedName("known_for_department")
    public String known_for_department;
    @SerializedName("name")
    public String name;
    @SerializedName("profile_path")
    public String profile_path;
}