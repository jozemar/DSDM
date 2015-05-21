package br.edu.unisep.mynotes;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import br.edu.unisep.mynotes.adapter.NotebookAdapter;
import br.edu.unisep.mynotes.model.NotebookDAO;


public class MainActivity extends ListActivity {

    private NotebookDAO dao;
    private Cursor crsNotebook;

    private NotebookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new NotebookDAO(this);
        crsNotebook = dao.listar();

        adapter = new NotebookAdapter(this, crsNotebook, 0);
        setListAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mn_novo) {
            Intent i = new Intent(this, NovoNotebookActivity.class);
            startActivityForResult(i, 1);
            return true;
        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        crsNotebook = dao.listar();

        adapter.changeCursor(crsNotebook);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, NotebookDetalhesActivity.class);
        intent.putExtra("idNotebook", (int) id);

        startActivityForResult(intent, 2);
    }
}
