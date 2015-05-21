package br.edu.unisep.mynotes;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import br.edu.unisep.mynotes.adapter.NoteAdapter;
import br.edu.unisep.mynotes.model.NoteDAO;
import br.edu.unisep.mynotes.model.NotebookDAO;
import br.edu.unisep.mynotes.vo.NotebookVO;


public class NotebookDetalhesActivity extends ListActivity {

    private Cursor crsNotes;
    private NoteDAO dao;
    private NoteAdapter adapter;

    private Integer idNotebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook_detalhes);

        // Obtém o objeto Intent que foi utilizado para a inicialização
        // desta activity
        Intent intent = getIntent();

        // Recupera o id do notebook que foi passado como parâmetro a partir
        // da tela anterior
        idNotebook = intent.getIntExtra("idNotebook", 0);

        NotebookDAO nbDAO = new NotebookDAO(this);

        // Obtém os dados de detalhe do notebook selecionado na tela anterior
        NotebookVO nb = nbDAO.consultar(idNotebook);

        TextView lblTitulo = (TextView) findViewById(R.id.lblTitulo);
        lblTitulo.setText(nb.getTitulo());

        TextView lblDescricao = (TextView) findViewById(R.id.lblDescricao);
        lblDescricao.setText(nb.getDescricao());

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
            i.putExtra("idNotebook", idNotebook);

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
