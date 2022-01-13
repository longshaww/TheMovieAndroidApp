//change package to your project package
package vn.edu.huflit.themovieapp1;

import static java.lang.Thread.sleep;

import android.graphics.Movie;
import android.os.Parcel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MovieAPI {
    public Account account;

    public String  session_id;
    public ListData<Entertainment> trending, search;
    public Boolean isAccess;

    public ListData<MovieItem> top_rated_movie, similar_movie, popular_movie, upcoming, my_favorite_movie, my_rated_movie, my_watchlist_movie;

    public ListData<TVItem> top_rated_tv, similar_tv, popular_tv, my_favorite_tv, my_rated_tv, my_watchlist_tv;

    public Detail.Genres[] genres_movie, genres_tv;

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private final String APIKEY;

    final private Gson gson = new Gson();

    final private OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    /**
     * <i>constructor build a Object</i>
     *
     * @param APIKEY from the movie db api
     */
    public MovieAPI(String APIKEY) {
        this.APIKEY = APIKEY;
        isAccess = checkAPIKey();
        if (isAccess)
            Init();
    }


    private void Init() {
        //region Variable basic
        search = new ListData<>();
        trending = new ListData<>();
        top_rated_movie = new ListData<>();
        top_rated_tv = new ListData<>();
        similar_movie = new ListData<>();
        similar_tv = new ListData<>();
        popular_movie = new ListData<>();
        popular_tv = new ListData<>();
        upcoming = new ListData<>();
        genres_movie = getGenres(true);
        genres_tv = getGenres(false);
        //endregion

        //region Variable with account
        my_favorite_movie = new ListData<>();
        my_favorite_tv = new ListData<>();
        my_rated_movie = new ListData<>();
        my_rated_tv = new ListData<>();
        my_watchlist_movie = new ListData<>();
        my_watchlist_tv = new ListData<>();

        //endregion
    }

    //region Request Methods
    private String requestServer(String path) {
        return requestServer(path, "", null);
    }

    private String requestServer(String path, String params) {
        return requestServer(path, params, null);
    }


    @Nullable
    private String requestServer(String path, String params, RequestBody body) {
        // declare responseString and status response for request
        final String[] res = {null};
        final boolean[] status = {false};

        //not access => error
        if (!isAccess) return null;

        // make url with APIKEY & language
        String HOST = "https://api.themoviedb.org/3";
        String url = HOST + path + "?language=vi&api_key=" + APIKEY + (params.isEmpty() ? "" : "&" + params) + (session_id == null ? "" : "&session_id=" + session_id);

        System.out.println(url);
        Request request = new Request.Builder().method(body == null ? "GET" : "POST", body).url(url).build();

        // send request and listen event response
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.out.println(e);
                status[0] = true;
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    res[0] = responseBody.string();
                    status[0] = true;
                }
            }
        });

        // await response from server (tired)
        while (true) {
            if (status[0]) break;
            try {
                sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        return res[0];
    }


    private Boolean checkAPIKey() {
        // bypass requestServer
        isAccess = true;
        String response = requestServer("/authentication/guest_session/new");

        if (response == null) return null;

        JsonObject json = JsonParser.parseString(response).getAsJsonObject();

        if (!json.get("success").getAsBoolean()) return false;
        //session_id = json.get("guest_session_id").getAsString();
        return true;
    }

    private Detail.Genres[] getGenres(boolean type) {
        String response = requestServer(String.format("/genre/%s/list", type ? "movie" : "tv"));

        if (response == null) return null;

        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();

        return gson.fromJson(jsonObject.get("genres"), Detail.Genres[].class);
    }


    public List<Entertainment> getTrending() {
        String response = requestServer("/trending/all/day");

        if (response == null) return null;

        JsonObject json = JsonParser.parseString(response).getAsJsonObject();

        JsonArray result = json.get("results").getAsJsonArray();

        trending.nextPage++;
        trending.list.addAll(Factory.MvAndTv(result));

        return trending.list;
    }

    private Detail getDetail(String id, boolean type) {
        String response = requestServer(String.format("/%s/%s", type ? "movie" : "tv", id));

        if (response == null) return null;

        return gson.fromJson(response, type ? MovieDetail.class : TVDetail.class);
    }


    public MovieDetail getDetailMovie(String id) {
        return (MovieDetail) getDetail(id, true);
    }

    public TVDetail getDetailTV(String id) {
        return (TVDetail) getDetail(id, false);
    }

    public List<MovieItem> getTopRatedMovie() {
        String response = requestServer("/movie/top_rated", "page=" + top_rated_movie.nextPage);

        if (response == null) return null;

        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();

        Type listType = new TypeToken<List<MovieItem>>() {
        }.getType();

        top_rated_movie.nextPage++;

        top_rated_movie.list.addAll(gson.fromJson(jsonObject.get("results"), listType));

        return top_rated_movie.list;
    }

    public List<TVItem> getTopRatedTV() {
        String response = requestServer("/tv/top_rated", "page=" + top_rated_tv.nextPage);

        if (response == null) return null;

        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        Type listType = new TypeToken<List<TVItem>>() {
        }.getType();

        top_rated_tv.nextPage++;

        top_rated_tv.list.addAll(gson.fromJson(jsonObject.get("results"), listType));

        return top_rated_tv.list;
    }

    public List<MovieItem> getSimilarMovie(String id) {
        String response = requestServer("/movie/" + id + "/similar", "page=" + similar_movie.nextPage);
        if (response == null) return null;

        if (similar_movie.id.equals(id))
            similar_movie.setId(id);
        else
            similar_movie.nextPage++;

        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        Type listType = new TypeToken<List<MovieItem>>() {
        }.getType();

        similar_movie.list.addAll(gson.fromJson(jsonObject.get("results"), listType));

        return similar_movie.list;
    }


    public List<TVItem> getSimilarTV(String id) {
        String response = requestServer("/tv/" + id + "/similar", "page=" + similar_tv.nextPage);
        if (response == null) return null;

        if (similar_tv.id.equals(id))
            similar_tv.nextPage++;
        else
            similar_tv.setId(id);

        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        Type listType = new TypeToken<List<TVItem>>() {
        }.getType();
        similar_tv.list.addAll(gson.fromJson(jsonObject.get("results"), listType));
        return similar_tv.list;
    }

    public List<MovieItem> getPopularMovie() {
        String response = requestServer("/movie/popular", "page=" + popular_movie.nextPage);
        if (response == null) return null;

        popular_movie.nextPage++;

        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        Type listType = new TypeToken<List<MovieItem>>() {
        }.getType();
        popular_movie.list.addAll(gson.fromJson(jsonObject.get("results"), listType));
        return popular_movie.list;
    }

    public List<TVItem> getPopularTV() {
        String response = requestServer("/tv/popular", "page=" + popular_tv.nextPage);
        if (response == null) return null;

        popular_tv.nextPage++;

        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        Type listType = new TypeToken<List<TVItem>>() {
        }.getType();
        popular_tv.list.addAll(gson.fromJson(jsonObject.get("results"), listType));
        return popular_tv.list;
    }

    public List<MovieItem> getUpcoming() {
        String response = requestServer("/movie/upcoming");
        if (response == null) return null;

        upcoming.nextPage++;

        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        Type listType = new TypeToken<List<MovieItem>>() {
        }.getType();

        upcoming.list.addAll(gson.fromJson(jsonObject.get("results"), listType));

        return upcoming.list;
    }
    public List<Entertainment> search(String query) {
        String response = requestServer("/search/multi", "include_adult=true&page=" + search.nextPage + "&query=" + query);

        if (response == null) return null;

        if (search.id.equals(query))
            search.setId(query);
        else
            search.nextPage++;

        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        search.list.addAll(Factory.MultiSearchList(jsonObject.get("results").getAsJsonArray()));

        return search.list;
    }

    public Object[] getCredit(String id, boolean type) {
        String response = requestServer(String.format("/%s/%s/credits", type ? "movie" : "tv", id));

        if (response == null) return null;
        JsonObject json = JsonParser.parseString(response).getAsJsonObject();

        JsonArray cast = json.get("cast").getAsJsonArray();
        JsonArray crew = json.get("crew").getAsJsonArray();

        Type castType = new TypeToken<List<Cast>>() {
        }.getType();
        Type crewType = new TypeToken<List<Crew>>() {
        }.getType();
        System.out.println("cast");
        System.out.println(cast);
        List<Cast> casts = gson.fromJson(cast, castType);
        List<Crew> crews = gson.fromJson(crew, crewType);

        Object[] obj = new Object[2];
        obj[0] = casts;
        obj[1] = crews;
        return obj;
    }

}


class ListData<T> {
    public int nextPage;
    public List<T> list;
    public String id;

    public ListData() {
        list = new ArrayList<T>();
        id = "";
        nextPage = 1;
    }

    public void setId(String id) {
        this.id = id;
        nextPage = 1;
        list.clear();
    }

    public String toString() {
        return String.format("size: %d, next page: %d", list.size(), nextPage);
    }
}