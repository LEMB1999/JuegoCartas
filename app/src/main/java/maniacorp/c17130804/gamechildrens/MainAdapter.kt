package maniacorp.c17130804.gamechildrens

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

class MainAdapter(val context : Context, val arrImage: MutableList<Int>) : BaseAdapter() {

    private  var inflater : LayoutInflater? = null
    private  var view : View? = null

    override fun getCount(): Int {
         return arrImage.size
    }

    override fun getItem(position: Int): Any {
        return  arrImage.get(position)
    }

    override fun getItemId(position: Int): Long {
         return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {


        if(inflater == null)
           inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        view = inflater!!.inflate(R.layout.card,parent,false)

        var imageView = view!!.findViewById<ImageView>(R.id.imgv)

        //usar glide para cargar las imagenes

        Glide.with(context).load(arrImage[position]).into(imageView);

        //imageView.setImageResource(arrImage[position])

        return  view!!;
    }

}
