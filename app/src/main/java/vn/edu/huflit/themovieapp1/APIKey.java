package vn.edu.huflit.themovieapp1;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

public class APIKey {
    private Context context;
    MovieAPI api = new MovieAPI("743a82500e05c3b60a15c2d5030bc55f");
//    APIKey apiKey = new APIKey();

    public APIKey(Context context) {
        Intent i = new Intent(context, DetailsMovieActivity.class);
        i.putExtra("APIKey", (Parcelable) api);
        context.startActivity(i);
    }
    APIKey apiKey = new APIKey(this.context);
}




