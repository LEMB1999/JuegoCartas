package maniacorp.c17130804.gamechildrens



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.GridView

import java.lang.Thread.sleep


class MainActivity : AppCompatActivity() {

    var gridView : GridView? = null
    var arrImage = mutableListOf<Int>()
    var arrRandom = mutableListOf<Int>()
    var arrIndices = arrayListOf<Int>()
    var arrRandomImage = arrayListOf<Int>()
    var turnos = 0
    var aux_arr  = arrayListOf<Int>()
    var adapter : BaseAdapter ?= null
    var thread : Thread? = null
    var anterior : Int = -1
    var numCard :Int = 0
    var temporizador : Temporizador? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //iniciar temporizador
        temporizador = Temporizador(1,10,this)


        //agregar los numeros del rango del 0 al 11
        arrIndices.addAll(0..11)


        //inicializar arreglo random de imagenes
        arrRandomImage.addAll(listOf(
            R.drawable.carta_1,
            R.drawable.carta_2,
            R.drawable.carta_3,
            R.drawable.carta_4,
            R.drawable.carta_5,
            R.drawable.carta_6,
        ))



        //inicializar el arreglo con 0
        for (x in 0 .. 11)
             arrRandom.add(0)

        var contador = 0
        var veces = 0
        while(arrIndices.size > 1 ){

            var indice =   arrIndices.get(
                (0 until arrIndices.size).random()
            )

            if(contador<2){
                arrRandom[indice] = arrRandomImage[veces]
                contador++;
                arrIndices.remove(indice)
            }
            else{
                contador = 0
                veces++
            }

        }
        //acomodar la ultima carta
        arrRandom[arrIndices[0]] = arrRandomImage[veces]

        gridView = findViewById(R.id.gv)

        //almacenar imagenes en el arreglo
        arrImage.addAll(listOf(
            R.drawable.card_back,
            R.drawable.card_back,
            R.drawable.card_back,
            R.drawable.card_back,
            R.drawable.card_back,
            R.drawable.card_back,
            R.drawable.card_back,
            R.drawable.card_back,
            R.drawable.card_back,
            R.drawable.card_back,
            R.drawable.card_back,
            R.drawable.card_back,
        ))


        adapter = MainAdapter(this,arrImage)
        gridView?.adapter = adapter


        thread = Thread(Runnable {

            sleep(1500)
            //voltear las cartas
            for( x in 0 until 2)
                arrImage[aux_arr[x]] = R.drawable.card_back
            aux_arr.clear()

            this@MainActivity.runOnUiThread(java.lang.Runnable {
                gridView?.isEnabled = true
                adapter!!.notifyDataSetChanged()
            })
        })

        gridView?.setOnItemClickListener(AdapterView.OnItemClickListener{ parent, view, position, id ->
                   if(position!=anterior) {

                       anterior = position
                       aux_arr.add(position)
                       adapter?.notifyDataSetChanged()

                       arrImage[position] = arrRandom[position]

                       //si las imagenes son iguales
                       if (turnos == 1) {
                           gridView?.isEnabled = false
                           turnos = 0
                           if (arrImage[aux_arr[0]] != arrImage[aux_arr[1]]) {
                               thread?.start()
                           } else {
                               gridView?.isEnabled = true
                               aux_arr.clear()
                               numCard+=2
                               if(numCard==12){

                                        var dialogo =  MIDialogo()
                                        dialogo.show(supportFragmentManager,"dialogo")
                                  }
                               }
                       } else {
                           turnos++
                       }
                   }
        })

        Thread(temporizador).start()
    }
}




