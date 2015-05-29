package br.edu.unisep.mynotes.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by roberto on 2/5/15.
 */
public class MyNotesOpenHelper extends SQLiteOpenHelper{

    public MyNotesOpenHelper(Context context, String name,
                             SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.createNotebook(db);
        this.createNote(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    private void createNotebook(SQLiteDatabase db) {
        String sql = "create table notebook (" +
                " _id INTEGER PRIMARY KEY, " +
                " titulo TEXT NOT NULL, " +
                " descricao TEXT, " +
                " dt_criacao INTEGER, " +
                " dt_alteracao INTEGER)";
        db.execSQL(sql);
    }

    private void createNote(SQLiteDatabase db) {
        String sql = "create table note (" +
                " _id INTEGER PRIMARY KEY, " +
                " titulo TEXT NOT NULL, " +
                " descricao TEXT NOT NULL, " +
                " dt_criacao INTEGER, " +
                " dt_alteracao INTEGER, " +
                " id_notebook INTEGER REFERENCES notebook(_id))";
        db.execSQL(sql);
    }
}