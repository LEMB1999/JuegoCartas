package maniacorp.c17130804.gamechildrens


import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager

import java.lang.Thread.sleep

class Temporizador(var minutos:Long , var segundos:Long,var contexto:Activity):Runnable {

    var dialog:MIDialogo?=null

    override fun run() {
        segundos+= minutos*60
        var tiempo = contexto.findViewById<TextView>(R.id.tvTiempo)
        while( segundos >= 0){
             sleep(1000)
            contexto.runOnUiThread(java.lang.Runnable {
                 if(segundos == 0L ){
                     mostrarDialogo()
                 }else if(segundos>0)
                       tiempo.setText("Tiempo Restante:"+segundos)

            })
            segundos-=1
        }


    }


    fun mostrarDialogo(){
        var builder  = AlertDialog.Builder(contexto)
        builder.setMessage("Perdiste 😥").setTitle("Game Over")
        builder.setPositiveButton("Reiniciar",DialogInterface.OnClickListener{ dialog, id->

        })
        builder.setNegativeButton("Salir",DialogInterface.OnClickListener{
            dialog,id->

        })
        builder.create().show()
    }

}