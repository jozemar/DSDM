package br.edu.unisep.mynotes;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import br.edu.unisep.mynotes.adapter.NoteAdapter;
import br.edu.unisep.mynotes.model.NoteDAO;


public class NotebookDetalhesActivity extends ListActivity {

    private Cursor crsNotes;
    private NoteDAO dao;
    private NoteAdapter adapter;

    private Integer idNotebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook_detalhes);

        //TODO obter aqui o código do notebook!

        dao = new NoteDAO(this);
        crsNotes = dao.listar(idNotebook);
        adapter = new NoteAdapter(this, crsNotes, 0);

        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notebook_detalhes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mn_novo) {
            Intent i = new Intent(this, NovoNoteActivity.class);

            //TODO passar aqui como parâmetro o id do notebook

            startActivityForResult(i, 1);
            return true;
        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        crsNotes = dao.listar(idNotebook);

        adapter.changeCursor(crsNotes);
        adapter.notifyDataSetChanged();
    }
}
