package my.id.jeremia.fokusteropong.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.GridView
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dagger.hilt.android.AndroidEntryPoint
import my.id.jeremia.fokusteropong.R
import my.id.jeremia.fokusteropong.ViewModel.HistoriViewModel
import my.id.jeremia.fokusteropong.tools.HistoriGridAdapater
import my.id.jeremia.fokusteropong.tools.HistoriItem
import my.id.jeremia.fokusteropong.tools.MotifGridAdapter


@AndroidEntryPoint
class HistoryActivity : AppCompatActivity() {
    val viewModel : HistoriViewModel by viewModels()

    val initData = arrayListOf<HistoriItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_history)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val title = intent.getStringExtra("title");
        findViewById<TextView>(R.id.textView).text = title;

        val id = intent.getIntExtra("idHistori",-1);

        viewModel.getAllHistory(id)

        val grid = findViewById<View>(R.id.gridView) as GridView

        val customAdapter: HistoriGridAdapater = HistoriGridAdapater(initData, layoutInflater)
        grid.setAdapter(customAdapter)

        viewModel.histori.observe(this){
            it?.data?.errorAlat?.let{_->
                findViewById<TextView>(R.id.textView2).text = "Error Alat : ${it.data.errorAlat} | Error Deteksi : ${it.data.errorDeteksi} "
            }

            initData.clear()
            it?.data?.gambar?.forEach {
                initData.add(
                    HistoriItem(
                        it!!.path!!,
                        it.timestamp!!,
                    )
                )
            }
            customAdapter.notifyDataSetChanged()
        }

    }
}