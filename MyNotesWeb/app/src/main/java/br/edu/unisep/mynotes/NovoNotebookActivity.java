package br.edu.unisep.mynotes;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.edu.unisep.mynotes.vo.NotebookVO;


public class NovoNotebookActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_notebook);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_novo_notebook, menu);
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

        NotebookVO nb = new NotebookVO();
        nb.setTitulo(txtTitulo.getText().toString());
        nb.setDescricao(txtDescricao.getText().toString());


        setResult(0);

        finish();
    }

}
