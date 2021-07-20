package maniacorp.c17130804.gamechildrens

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class MIDialogo:DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{
            var builder = AlertDialog.Builder(it)
            builder.setTitle("Ganaste")
            builder.setMessage("Felicidades")

            builder.setPositiveButton("Reiniciar",
            DialogInterface.OnClickListener{ dialog, id->
                 Toast.makeText(activity,"Reiniciando",Toast.LENGTH_SHORT).show()
            })

            builder.setNegativeButton("Cancelar", DialogInterface.OnClickListener{ dialog, id->
                Toast.makeText(activity,"Saliendo",Toast.LENGTH_SHORT).show()
            })

            builder.create()
        }?: throw  IllegalStateException("Activity cannot be null")

    }


}