package br.com.caelum.fj59.carangos.app;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.fj59.carangos.infra.MyLog;
import br.com.caelum.fj59.carangos.modelo.Publicacao;
import br.com.caelum.fj59.carangos.tasks.RegistraAparelhoTask;

/**
 * Created by android5628 on 18/01/16.
 */
public class CarangosApplication extends Application {

    private static final String REGISTRADO = "registradoNoGCM";
    private static final String ID_DO_REGISTRO = "idDoRegistro";

    private SharedPreferences preferences;


    private List<AsyncTask<?,?,?>> tasks = new ArrayList<AsyncTask<?,?,?>>();
    List<Publicacao> publicacoes = new ArrayList<Publicacao>();

    @Override
    public void onCreate() {
        super.onCreate();

        preferences = getSharedPreferences("configs", Activity.MODE_PRIVATE);
        registraNoGCM();
    }

    public void registraNoGCM(){
        if(!usuarioRegistrado()){
            new RegistraAparelhoTask(this).execute();

        }
        else{
            MyLog.i("Aparelho já cadastrado! seu id é: "+preferences.getString(ID_DO_REGISTRO,null));
        }
    }

    private boolean usuarioRegistrado(){
        return preferences.getBoolean(REGISTRADO,false);
    }



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

    public void lidaComRespostaDoRegistroNoServidor(String registro) {
        if(registro!=null){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(REGISTRADO,true);
            editor.putString(ID_DO_REGISTRO,registro);
            editor.commit();
        }
    }



}
