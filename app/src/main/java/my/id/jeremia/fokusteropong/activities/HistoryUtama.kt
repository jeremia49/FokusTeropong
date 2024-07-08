package my.id.jeremia.fokusteropong.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dagger.hilt.android.AndroidEntryPoint
import my.id.jeremia.fokusteropong.R
import my.id.jeremia.fokusteropong.ViewModel.HistoriUtamaViewModel
import my.id.jeremia.fokusteropong.tools.HistoriGridAdapater
import my.id.jeremia.fokusteropong.tools.HistoriUtama
import my.id.jeremia.fokusteropong.tools.HistoriUtamaGridAdapter



@AndroidEntryPoint
class HistoryUtama : AppCompatActivity() {
    val viewModel : HistoriUtamaViewModel by viewModels()

    val initData = arrayListOf<HistoriUtama>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_history_utama)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val grid = findViewById<View>(R.id.gridView) as GridView

        val customAdapter: HistoriUtamaGridAdapter = HistoriUtamaGridAdapter(initData, layoutInflater)
        grid.setAdapter(customAdapter)


        val btn = findViewById<ImageButton>(R.id.resetHistoryBtn)
        btn.setOnClickListener {
            viewModel.resetHistory()
            initData.clear()
            customAdapter.notifyDataSetChanged()
        }

        grid.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, HistoryActivity::class.java)
            intent.putExtra("idHistori", initData[position].id)
            intent.putExtra("title", initData[position].nama)

            startActivity(intent)
        })


        viewModel.histori.observe(this){
            initData.clear()
            it?.data?.forEach {
                initData.add(
                    HistoriUtama(
                        it!!.id!!,
                        it.nama!!
                    )
                )
            }

            customAdapter.notifyDataSetChanged()
        }

    }
}