package my.id.jeremia.fokusteropong

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InferenceActivity : AppCompatActivity() {
    var isStarted : Boolean = false;
    var isJeda : Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inference)

        val motifresource = intent.getIntExtra("motif", R.drawable.background)


        val imgview = findViewById<ImageView>(R.id.tampilan)
        imgview.setImageDrawable(AppCompatResources.getDrawable(this, motifresource))


        val startstopbtn = findViewById<Button>(R.id.bt1)
        val recText = findViewById<TextView>(R.id.recText)
        startstopbtn.setOnClickListener {
            isStarted = !isStarted

            if(isStarted){
                recText.visibility = View.VISIBLE
                startstopbtn.text = "Stop Rekam"
            }else{
                recText.visibility = View.INVISIBLE
                startstopbtn.text = "Mulai Rekam"
            }
        }

        val jedabtn = findViewById<Button>(R.id.bt2)
        jedabtn.setOnClickListener {
            isJeda = !isJeda
            Toast.makeText(this, "Dalam pengembangan", Toast.LENGTH_SHORT).show()


//            if(isJeda){
//                startstopbtn.text = "Stop Rekam"
//            }
        }

        val stopandsavebtn = findViewById<Button>(R.id.bt3)
        stopandsavebtn.setOnClickListener {
            this.finishAffinity();
        }

    }
}