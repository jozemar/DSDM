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
import br.edu.unisep.mynotes.vo.NoteVO;
import br.edu.unisep.mynotes.vo.NotebookVO;


public class NoteListAdapter extends ArrayAdapter<NoteVO>{

    private LayoutInflater inflater;
    private List<NoteVO> listaNotes;

    public NoteListAdapter(Context context, int resource, List<NoteVO> objects) {
        super(context, resource, objects);

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaNotes = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.item_lista_notes, null);

        TextView lblTitulo = (TextView) view.findViewById(R.id.lblTitulo);

        NoteVO note = listaNotes.get(position);

        lblTitulo.setText(note.getTitulo());

        return view;
    }
}
