package vn.edu.huflit.themovieapp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FavouriteHelper{

    Context context;
    String dbName = "Favorite.db";

    public FavouriteHelper(Context context){
        this.context = context;
        CreateTable();
    }

    private SQLiteDatabase openDB() {
        return context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
    }

    private void closeDB(SQLiteDatabase db) {
        db.close();
    }

    private void CreateTable() {
        SQLiteDatabase db = openDB();

        String sqlFavorites = "CREATE TABLE IF NOT EXISTS tblFavorites(" +
                "ID TEXT NOT NULL PRIMARY KEY," +
                "Type TEXT," +
                "Name TEXT," +
                "Poster TEXT," +
                "Description TEXT, "+
                "Vote REAL);";
        db.execSQL(sqlFavorites);
        closeDB(db);
    }

    public List<Entertainment> getAllFavorites() {
        SQLiteDatabase db = openDB();
        ArrayList<Entertainment> arr = new ArrayList<>();
        String sql = "select * from tblFavorites";
        Cursor csr = db.rawQuery(sql, null);

        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    String id = csr.getString(0);
                    String type = csr.getString(1);
                    String name = csr.getString(2);
                    String poster_path = csr.getString(3);
                    String description = csr.getString(4);
                    double vote = csr.getDouble(5);

                    System.out.println(type);
                    arr.add(type.equals("movie") ? new MovieItem(id, type, name, poster_path, description, vote) : new TVItem(id, type, name, poster_path, description, vote));
                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }
    public List<MovieItem> getAllMovies() {
        SQLiteDatabase db = openDB();
        ArrayList<MovieItem> arr = new ArrayList<>();
        String sql = "select * from tblFavorites";
        Cursor csr = db.rawQuery(sql, null);

        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    String id = csr.getString(0);
                    String type = csr.getString(1);
                    String name = csr.getString(2);
                    String poster_path = csr.getString(3);
                    String description = csr.getString(4);
                    double vote = csr.getDouble(5);

                    System.out.println(type);
                    arr.add(new MovieItem(id, type, name, poster_path, description, vote));
                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }

    public void insertFavorites(String id, String title, String type, String overview, String poster_path, double vote_average) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("Name", title);
        contentValues.put("Type", type);
        contentValues.put("Description", overview);
        contentValues.put("Poster", poster_path);
        contentValues.put("Vote", vote_average);
        SQLiteDatabase db = openDB();
        db.insert("tblFavorites", null, contentValues);
        closeDB(db);
    }

    public void deleteRow(String id)
    {
        SQLiteDatabase db = openDB();
        db.delete("tblFavorites",  "ID="  + id, null);
    }
}
