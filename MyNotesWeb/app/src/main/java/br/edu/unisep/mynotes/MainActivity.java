package br.edu.unisep.mynotes;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.edu.unisep.mynotes.adapter.NotebookListAdapter;
import br.edu.unisep.mynotes.task.ListaNotebookTask;
import br.edu.unisep.mynotes.vo.NotebookVO;


public class MainActivity extends ListActivity {

    private List<NotebookVO> listaNotebook;

    private NotebookListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaNotebook = new ArrayList<NotebookVO>();
        adapter = new NotebookListAdapter(this,
                R.layout.item_lista_notebook, listaNotebook);

        setListAdapter(adapter);

        ListaNotebookTask task = new ListaNotebookTask(this);
        task.execute();
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
        ListaNotebookTask task = new ListaNotebookTask(this);
        task.execute();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, NotebookDetalhesActivity.class);
        intent.putExtra("idNotebook", (int) id);

        startActivityForResult(intent, 2);
    }

    public void atualizarLista(List<NotebookVO> lista) {
        this.listaNotebook.clear();
        this.listaNotebook.addAll(lista);

        adapter.notifyDataSetChanged();
    }

}
