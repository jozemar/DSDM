package br.edu.unisep.mynotes.task;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.edu.unisep.mynotes.NovoNotebookActivity;
import br.edu.unisep.mynotes.vo.NotebookVO;

public class InclusaoNotebookTask extends AsyncTask<NotebookVO, Void, Void>{

    private NovoNotebookActivity activity;

    public InclusaoNotebookTask(NovoNotebookActivity activity) {
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(NotebookVO... notebookVOs) {

        try {
            String ws = "http://172.16.4.244:8080/MyNotesWS/ws/notebook/salvar";

            NotebookVO nb = notebookVOs[0];

            Gson gs = new Gson();
            String msgEnvio = gs.toJson(nb);

            URL url = new URL(ws);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(10000);
            con.setConnectTimeout(15000);
            con.setRequestMethod("POST");

            con.setDoInput(false);
            con.setDoOutput(true);

            con.setRequestProperty("Content-Type",
                    "application/json;charset=utf-8");
            con.setRequestProperty("X-Requested-With", "XMLHttpRequest");

            con.setFixedLengthStreamingMode(msgEnvio.getBytes().length);

            con.connect();

            OutputStream os = new BufferedOutputStream(con.getOutputStream());
            os.write( msgEnvio.getBytes() );
            os.flush();

            os.close();
            con.disconnect();

            publishProgress();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        activity.finalizar();
    }
}