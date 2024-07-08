package my.id.jeremia.fokusteropong.activities

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.EditText
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import my.id.jeremia.fokusteropong.BuildConfig
import my.id.jeremia.fokusteropong.R
import my.id.jeremia.fokusteropong.ViewModel.PilihMenuViewModel
import my.id.jeremia.fokusteropong.tools.MotifGridAdapter
import my.id.jeremia.fokusteropong.tools.MotifItem


@AndroidEntryPoint
class PilihMotifActivity : AppCompatActivity() {

    val viewModel: PilihMenuViewModel by viewModels()

    val original = arrayListOf(
        MotifItem(
            imgResource = R.drawable.motif_aurum,
            nama = "Aurum",
        ),
        MotifItem(
            imgResource = R.drawable.motif_melayu_ikan_dan_kapal,
            nama = "Melayu Ikan dan Kapal",
        ),
        MotifItem(
            imgResource = R.drawable.motif_sirangkak,
            nama = "Sirangkak",
        ),
        MotifItem(
            imgResource = R.drawable.motif_hcl,
            nama = "HCL",
        ),
        MotifItem(
            imgResource = R.drawable.motif_purnama,
            nama = "Purnama",
        ),
        MotifItem(
            imgResource = R.drawable.motif_rumah_panggung,
            nama = "Rumah Panggung",
        ),
        MotifItem(
            imgResource = R.drawable.motif_chemistry,
            nama = "Chemistry",
        ),
        MotifItem(
            imgResource = R.drawable.motif_pucuk_rebung,
            nama = "Pucuk Rebung",
        ),
    )

    val dataset = original.clone() as ArrayList<MotifItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pilih_motif)

        val grid = findViewById<View>(R.id.gridView) as GridView // init GridView

        // Create an object of CustomAdapter and set Adapter to GirdView
        val customAdapter: MotifGridAdapter = MotifGridAdapter(dataset, layoutInflater)
        grid.setAdapter(customAdapter)

        // implement setOnItemClickListener event on GridView
        grid.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            namaPekerjaan{id->
                val intent = Intent(this, InferenceActivity::class.java)
                val i = dataset[position];
                if(i.imgResource != null){
                    intent.putExtra("motifResource", i.imgResource)
                }else{
                    intent.putExtra("motifNetwork",i.imgNetwork)
                }
                intent.putExtra("id",id)
                startActivity(intent)
            }

        })

        grid.setOnItemLongClickListener ( OnItemLongClickListener{ parent, view, position, id ->
            if(dataset[position].id == null) return@OnItemLongClickListener false
            viewModel.deleteKain(dataset[position].id!!)
            Toast.makeText(this, "Motif dihapus", Toast.LENGTH_SHORT).show()
            return@OnItemLongClickListener true;
        })

        viewModel.kain.observe(this){
            val z = original.clone() as ArrayList<MotifItem>
            it?.data?.forEach {
                z.add(MotifItem(
                     id = it!!.id!!,
                     imgNetwork = "http://"+BuildConfig.SERVER_IPADDR+":"+BuildConfig.SERVER_PORT+"/public/"+it!!.path,
                     nama = it!!.nama!!,
                 ))
            }
            dataset.clear()
            dataset.addAll(z);
            customAdapter.notifyDataSetChanged();
//            dataset.clear()
//            dataset.addAll(z);
//            customAdapter.notifyDataSetChanged()
        }


    }


    private fun namaPekerjaan(onOk: (Int) -> Unit) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Masukkan Nama Pekerjaan")

        // Set up the input
        val viewInflated: View = LayoutInflater.from(this).inflate(my.id.jeremia.fokusteropong.R.layout.dialoginput, null)
        val input = viewInflated.findViewById<EditText>(R.id.edit_text_input)
        builder.setView(viewInflated)

        // Set up the buttons
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            val namapekerjaan = input.text.toString().trim { it <= ' ' }
            runBlocking {
                val result = viewModel.addNewHistory(namapekerjaan)
                val id = result.data!!.toInt()
                onOk(id);
            }


        })
        builder.setNegativeButton("Cancel",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }
}