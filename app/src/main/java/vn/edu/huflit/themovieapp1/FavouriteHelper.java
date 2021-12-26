package vn.edu.huflit.themovieapp1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class FavouriteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="FavouriteMovies";
    public static final String TABLE_NAME_FAVOURITE ="Favourtie";

    public static final String ID_COLUMN = "id";
    public static final String POSTER_PATH_COLUMN = "poster_path";
    public static final String TITLE_COLUMN = "title";

    private static final String CREATE_TABLE_FAVOURITE = ""
            + "create table " + TABLE_NAME_FAVOURITE + " ( "
            + ID_COLUMN + " integer primary key ,"
            + POSTER_PATH_COLUMN + " text not null, "
            + TITLE_COLUMN + " text not null );";

    public FavouriteHelper(@Nullable Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FAVOURITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        

    }
}
