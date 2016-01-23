package br.com.caelum.fj59.carangos.gcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import br.com.caelum.fj59.carangos.R;
import br.com.caelum.fj59.carangos.activity.LeilaoActivity;
import br.com.caelum.fj59.carangos.activity.MainActivity;
import br.com.caelum.fj59.carangos.infra.MyLog;

/**
 * Created by android5628 on 21/01/16.
 */
public class GCMBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        MyLog.i("Chegou a mensagem do GCM");

        String mensagem = (String) intent.getExtras().getSerializable("message");
        Toast.makeText(context,"CHEGOU MENSAGEM DO GCM! " + mensagem,Toast.LENGTH_SHORT).show();


        MyLog.i("Mensagem com conteúdo:"+mensagem);
        Intent irParaLeilao = new Intent(context, LeilaoActivity.class);

        PendingIntent acaoPendente = PendingIntent.getActivity(context,0,irParaLeilao,PendingIntent.FLAG_CANCEL_CURRENT);

        irParaLeilao.putExtra("idDaNotificacao",Constantes.ID_NOTIFICACAO);

        Notification notificacao = new Notification.Builder(context)
                .setContentTitle("Um novo leilão começou!")
                .setContentText("Leilão:"+mensagem)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(acaoPendente)
                .setAutoCancel(true)
                .build();
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(Constantes.ID_NOTIFICACAO,notificacao);

    }
}
