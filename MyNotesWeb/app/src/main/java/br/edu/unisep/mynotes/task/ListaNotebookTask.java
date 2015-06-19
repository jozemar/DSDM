package br.edu.unisep.mynotes.task;


import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import br.edu.unisep.mynotes.MainActivity;
import br.edu.unisep.mynotes.vo.NotebookVO;

public class ListaNotebookTask extends AsyncTask<Void, List<NotebookVO>, Void>{

    private MainActivity activity;

    public ListaNotebookTask(MainActivity a) {
        this.activity = a;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            String ws = "http://172.16.4.244:8080/MyNotesWS/ws/notebook/listar";

            // Representa a URL do serviço que será acessado pela task
            URL url = new URL(ws);

            // Estabelece uma conexão entre a task e o servidor
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // Obtém o fluxo de dados de retorno da chamada do serviço
            InputStream is = con.getInputStream();

            // Cria um objeto de leitura do fluxo de dados de retorno
            InputStreamReader reader = new InputStreamReader(is);

            Gson gs = new Gson();

            // Cria um objeto TypeToken que é um descritor do tipo de retorno
            // da chamada do serviço
            TypeToken< List<NotebookVO> > token =
                    new TypeToken< List<NotebookVO> >() {};

            List<NotebookVO> lista = gs.fromJson(reader, token.getType());

            reader.close();
            con.disconnect();

            publishProgress(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(List<NotebookVO>... values) {
        List<NotebookVO> lista = values[0];
        this.activity.atualizarLista(lista);
    }
}