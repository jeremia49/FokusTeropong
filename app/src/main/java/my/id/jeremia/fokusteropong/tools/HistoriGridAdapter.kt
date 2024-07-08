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


data class HistoriItem(
    val imgNetwork: String? = null,
    val timestamp :Long,
)
class HistoriGridAdapater(val items: ArrayList<HistoriItem>, val layoutInflater: LayoutInflater) : BaseAdapter() {
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
        val view = layoutInflater.inflate(R.layout.histori_grid_item, parent, false)
        if(items[position].imgNetwork != null){
            view.findViewById<ImageView>(R.id.myImageView).load("http://"+BuildConfig.SERVER_IPADDR+":"+BuildConfig.SERVER_PORT+"/public/"+items[position].imgNetwork);
        }
        val ts = items[position].timestamp

        val date: Date = Date(ts * 1000)
        val sdf: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
        val formattedDateTime: String = sdf.format(date)

        view.findViewById<TextView>(R.id.myImageViewText).text = formattedDateTime
        return view;
    }
}