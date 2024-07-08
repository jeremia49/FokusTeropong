package my.id.jeremia.fokusteropong.tools

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import coil.load
import my.id.jeremia.fokusteropong.R
import my.id.jeremia.fokusteropong.network.apis.result.ResultKain


data class MotifItem(
    val id: Int? = null,
   val imgNetwork: String? = null,
   val imgResource: Int? = null,
   val nama :String,
)
class MotifGridAdapter(val items: ArrayList<MotifItem>, val layoutInflater: LayoutInflater) : BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any? {
        return null;
    }

    override fun getItemId(position: Int): Long {
       return 0;
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = layoutInflater.inflate(R.layout.motif_grid_item, parent, false)
        if(items[position].imgNetwork != null){
            view.findViewById<ImageView>(R.id.myImageView).load(items[position].imgNetwork);
        }else{
            view.findViewById<ImageView>(R.id.myImageView).setImageResource(items[position].imgResource!!);
        }
        view.findViewById<TextView>(R.id.myImageViewText).text = items[position].nama
        return view;
    }
}