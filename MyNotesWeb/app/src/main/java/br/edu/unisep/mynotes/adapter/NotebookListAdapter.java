package br.edu.unisep.mynotes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import br.edu.unisep.mynotes.R;
import br.edu.unisep.mynotes.vo.NotebookVO;


public class NotebookListAdapter extends ArrayAdapter<NotebookVO>{

    private LayoutInflater inflater;
    private List<NotebookVO> listaNotebooks;

    public NotebookListAdapter(Context context, int resource, List<NotebookVO> objects) {
        super(context, resource, objects);

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaNotebooks = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.item_lista_notebook, null);

        TextView lblTitulo = (TextView) view.findViewById(R.id.lblTitulo);
        TextView lblQtdeNotas = (TextView) view.findViewById(R.id.lblQtdeNotas);

        NotebookVO nb = listaNotebooks.get(position);

        lblTitulo.setText(nb.getTitulo());

        lblQtdeNotas.setText("0 nota(s)");

        return view;
    }
}
