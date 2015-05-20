package br.edu.unisep.mynotes.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

import br.edu.unisep.mynotes.vo.NoteVO;
import br.edu.unisep.mynotes.vo.NotebookVO;

/**
 * Created by roberto on 9/5/15.
 */
public class NoteDAO {

    private MyNotesOpenHelper helper;

    public NoteDAO(Context ctx) {
        this.helper = new MyNotesOpenHelper(ctx, "mynotes", null, 1);
    }

    public void salvar(NoteVO note) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("titulo", note.getTitulo());
        valores.put("descricao", note.getDescricao());

        Date hoje = new Date();
        valores.put("dt_criacao", hoje.getTime());
        valores.put("dt_alteracao", (Integer) null);

        valores.put("id_notebook", note.getNotebook().getId());

        db.insert("note", null, valores);
        db.close();
    }

    public Cursor listar(Integer notebook) {

        SQLiteDatabase db = helper.getReadableDatabase();

        String[] colunas = {"_id",
                "titulo",
                "descricao",
                "dt_criacao",
                "dt_alteracao",
                "id_notebook"};

        String[] where = { notebook.toString() };

        Cursor crs = db.query("note", colunas, "_id = ?",
                where, null, null, "dt_criacao");
        return crs;
    }

    public void atualizar(NoteVO note) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("titulo", note.getTitulo());
        valores.put("descricao", note.getDescricao());

        Date hoje = new Date();
        valores.put("dt_alteracao", hoje.getTime());

        String[] where = { note.getId().toString() };

        db.update("note", valores, "_id = ?", where);

        db.close();
    }

    public NoteVO consultar(Integer id) {
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] colunas = {"_id",
                "titulo",
                "descricao",
                "dt_criacao",
                "dt_alteracao"};

        String[] where = { id.toString() };

        Cursor crs = db.query("note", colunas, "_id = ?", where,
                null, null, null);

        NoteVO note = new NoteVO();
        if (crs.moveToFirst()) {
            Integer _id = crs.getInt(crs.getColumnIndex("_id"));
            note.setId(_id);

            String titulo = crs.getString(crs.getColumnIndex("titulo"));
            note.setTitulo(titulo);

            String descricao = crs.getString(crs.getColumnIndex("descricao"));
            note.setDescricao(descricao);

            Long dtCriacao = crs.getLong( crs.getColumnIndex("dt_criacao") );
            note.setDataCriacao(new Date(dtCriacao));

            Long dtAlteracao = crs.getLong(crs.getColumnIndex("dt_alteracao"));
            note.setDataAlteracao( new Date(dtAlteracao) );

            Integer idNotebook = crs.getInt( crs.getColumnIndex("id_notebook"));
            NotebookVO nb = new NotebookVO();
            nb.setId(idNotebook);

            note.setNotebook(nb);
        }

        db.close();
        return note;
    }

    public Integer obterQuantidadeNotas(Integer nb) {

        SQLiteDatabase db = helper.getReadableDatabase();

        String[] where = { nb.toString() };

        Cursor crsCount = db.rawQuery(" select count(*) from note " +
                "where id_notebook = ?", where);
        crsCount.moveToFirst();

        Integer qtde = crsCount.getInt(0);

        db.close();

        return qtde;
    }

}
