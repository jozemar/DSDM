package br.edu.unisep.mynotes.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

import br.edu.unisep.mynotes.vo.NotebookVO;

/**
 * Created by roberto on 2/5/15.
 */
public class NotebookDAO {

    private MyNotesOpenHelper helper;

    public NotebookDAO(Context ctx) {
        this.helper = new MyNotesOpenHelper(ctx, "mynotes", null, 1);
    }

    public void salvar(NotebookVO notebook) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("titulo", notebook.getTitulo());
        valores.put("descricao", notebook.getDescricao());

        Date hoje = new Date();
        valores.put("dt_criacao", hoje.getTime());
        valores.put("dt_alteracao", (Integer) null);

        db.insert("notebook", null, valores);
        db.close();
    }

    public Cursor listar() {

        SQLiteDatabase db = helper.getReadableDatabase();

        String[] colunas = {"_id",
                            "titulo",
                            "descricao",
                            "dt_criacao",
                            "dt_alteracao"};

        Cursor crs = db.query("notebook", colunas, null,
                null, null, null, "dt_criacao");
        return crs;
    }

    public void atualizar(NotebookVO notebook) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("titulo", notebook.getTitulo());
        valores.put("descricao", notebook.getDescricao());

        Date hoje = new Date();
        valores.put("dt_alteracao", hoje.getTime());

        String[] where = { notebook.getId().toString() };

        db.update("notebook", valores, "_id = ?", where);

        db.close();
    }

    public NotebookVO consultar(Integer id) {
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] colunas = {"_id",
                "titulo",
                "descricao",
                "dt_criacao",
                "dt_alteracao"};

        String[] where = { id.toString() };

        Cursor crs = db.query("notebook", colunas, "_id = ?", where,
                null, null, null);

        NotebookVO notebook = new NotebookVO();
        if (crs.moveToFirst()) {
            Integer _id = crs.getInt(crs.getColumnIndex("_id"));
            notebook.setId(_id);

            String titulo = crs.getString( crs.getColumnIndex("titulo") );
            notebook.setTitulo(titulo);

            String descricao = crs.getString( crs.getColumnIndex("descricao") );
            notebook.setDescricao(descricao);

            Long dtCriacao = crs.getLong( crs.getColumnIndex("dt_criacao") );
            notebook.setDataCriacao( new Date(dtCriacao) );

            Long dtAlteracao = crs.getLong( crs.getColumnIndex("dt_alteracao") );
            notebook.setDataAlteracao( new Date(dtAlteracao) );
        }

        db.close();
        return notebook;
    }

}