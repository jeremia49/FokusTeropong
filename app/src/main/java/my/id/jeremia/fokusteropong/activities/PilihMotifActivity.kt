package my.id.jeremia.fokusteropong.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import my.id.jeremia.fokusteropong.R

class PilihMotifActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pilih_motif)

        val rl1 = findViewById<View>(R.id.rl1)
        rl1.setOnClickListener {
            val intent = Intent(this, InferenceActivity::class.java)
            intent.putExtra("motif", R.drawable.motif_aurum)
            startActivity(intent)
        }


        val rl2= findViewById<View>(R.id.rl2)
        rl2.setOnClickListener {
            val intent = Intent(this, InferenceActivity::class.java)
            intent.putExtra("motif", R.drawable.motif_melayu_ikan_dan_kapal)
            startActivity(intent)
        }

        val rl3 = findViewById<View>(R.id.rl3)
        rl3.setOnClickListener {
            val intent = Intent(this, InferenceActivity::class.java)
            intent.putExtra("motif", R.drawable.motif_sirangkak)
            startActivity(intent)
        }


        val rl4= findViewById<View>(R.id.rl4)
        rl4.setOnClickListener {
            val intent = Intent(this, InferenceActivity::class.java)
            intent.putExtra("motif", R.drawable.motif_hcl)
            startActivity(intent)
        }

        val rl5 = findViewById<View>(R.id.rl5)
        rl5.setOnClickListener {
            val intent = Intent(this, InferenceActivity::class.java)
            intent.putExtra("motif", R.drawable.motif_purnama)
            startActivity(intent)
        }


        val rl6= findViewById<View>(R.id.rl6)
        rl6.setOnClickListener {
            val intent = Intent(this, InferenceActivity::class.java)
            intent.putExtra("motif", R.drawable.motif_rumah_panggung)
            startActivity(intent)
        }


        val rl7 = findViewById<View>(R.id.rl7)
        rl7.setOnClickListener {
            val intent = Intent(this, InferenceActivity::class.java)
            intent.putExtra("motif", R.drawable.motif_chemistry)
            startActivity(intent)
        }


        val rl8= findViewById<View>(R.id.rl8)
        rl8.setOnClickListener {
            val intent = Intent(this, InferenceActivity::class.java)
            intent.putExtra("motif", R.drawable.motif_pucuk_rebung)
            startActivity(intent)
        }


    }
}