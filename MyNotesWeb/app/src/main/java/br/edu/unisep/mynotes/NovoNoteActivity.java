package br.edu.unisep.mynotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.edu.unisep.mynotes.vo.NoteVO;
import br.edu.unisep.mynotes.vo.NotebookVO;


public class NovoNoteActivity extends Activity {

    private Integer idNotebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_note);

        Intent intent = getIntent();

        idNotebook = intent.getIntExtra("idNotebook", 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_novo_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mn_salvar) {
            salvar();
            return true;
        }

        return false;
    }

    private void salvar() {

        EditText txtTitulo = (EditText) findViewById(R.id.txtTitulo);
        EditText txtDescricao = (EditText) findViewById(R.id.txtDescricao);

        NoteVO note = new NoteVO();
        note.setTitulo(txtTitulo.getText().toString());
        note.setDescricao(txtDescricao.getText().toString());

        NotebookVO nb = new NotebookVO();
        nb.setId(idNotebook);

        note.setNotebook(nb);

        setResult(0);

        finish();
    }
}
