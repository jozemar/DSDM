package br.edu.unisep.mynotes.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import br.edu.unisep.mynotes.R;

/**
 * Created by roberto on 15/5/15.
 */
public class NotebookAdapter extends CursorAdapter {

    private LayoutInflater inflater;

    public NotebookAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);

        this.inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View item = inflater.inflate(R.layout.item_lista_notebook, null);
        return item;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView lblTitulo = (TextView) view.findViewById(R.id.lblTitulo);
        TextView lblQtdeNotas = (TextView) view.findViewById(R.id.lblQtdeNotas);

        String titulo = cursor.getString( cursor.getColumnIndex("titulo") );
        lblTitulo.setText(titulo);

        lblQtdeNotas.setText("0 notas");
    }
}