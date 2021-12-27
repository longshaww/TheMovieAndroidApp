package vn.edu.huflit.themovieapp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class FavouriteCrud {
    SQLiteDatabase database;
    FavouriteHelper helper;

    public FavouriteCrud(Context context) {
        helper = new FavouriteHelper(context);
        database = helper.getWritableDatabase();
    }

    public Cursor getAllData() {
        String[] column = {
                FavouriteHelper.ID_COLUMN,
                FavouriteHelper.POSTER_PATH_COLUMN,
                FavouriteHelper.TITLE_COLUMN,
        };
        Cursor cursor = null;
        cursor = database.query(FavouriteHelper.TABLE_NAME_FAVOURITE
                , column, null, null, null, null,
                FavouriteHelper.ID_COLUMN + " DESC");
        return cursor;
    }

    public long Add(Favourite favourite) {
        ContentValues values = new ContentValues();
        values.put(FavouriteHelper.ID_COLUMN,
                favourite.getId());
        values.put(FavouriteHelper.POSTER_PATH_COLUMN,
                favourite.getPoster_path());
        values.put(FavouriteHelper.TITLE_COLUMN,
                favourite.getTitle());
        return database.insert(FavouriteHelper.
                TABLE_NAME_FAVOURITE, null, values);
    }

    public long Delete(Favourite favourite) {
        return database.delete(FavouriteHelper.TABLE_NAME_FAVOURITE, FavouriteHelper
                .TITLE_COLUMN + " = " + "'" +
                favourite.getTitle() + "'", null);
    }

}
