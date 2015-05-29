package br.edu.unisep.mynotes;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class NotebookDetalhesActivity extends ListActivity {

    private Cursor crsNotes;

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

    }
}
