package br.com.caelum.fj59.carangos.app;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.fj59.carangos.modelo.Publicacao;

/**
 * Created by android5628 on 18/01/16.
 */
public class CarangosApplication extends Application {

    private List<AsyncTask<?,?,?>> tasks = new ArrayList<AsyncTask<?,?,?>>();
    List<Publicacao> publicacoes = new ArrayList<Publicacao>();

    public List<Publicacao> getPublicacoes(){
        return publicacoes;
    }

    public void registra(AsyncTask<?,?,?> task){
        Log.i("TASK_MANAGER","Registra task");
        tasks.add(task);
    }

    public void desregistra(AsyncTask<?,?,?> task){
        Log.i("TASK_MANAGER","Desregistra task");
        tasks.remove(task);
    }

    public void cancela(){
        Log.i("TASK_MANAGER","Cancela task");
        for(AsyncTask task:tasks){
            task.cancel(false);
        }
    }

}
