package my.id.jeremia.fokusteropong.tools

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import coil.load
import my.id.jeremia.fokusteropong.BuildConfig
import my.id.jeremia.fokusteropong.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class HistoriUtama(
    val id:Int,
    val nama:String,
)
class HistoriUtamaGridAdapter (val items: ArrayList<HistoriUtama>, val layoutInflater: LayoutInflater) : BaseAdapter() {
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
        val view = layoutInflater.inflate(R.layout.histori_utama_item, parent, false)
        view.findViewById<TextView>(R.id.myText).text = items[position].nama
        return view;
    }
}