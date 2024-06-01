package my.id.jeremia.fokusteropong.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import com.thanosfisherman.wifiutils.WifiUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import my.id.jeremia.fokusteropong.BuildConfig
import my.id.jeremia.fokusteropong.R
import my.id.jeremia.fokusteropong.ViewModel.InferenceViewModel
import my.id.jeremia.fokusteropong.di.qualifier.BaseUrl
import javax.inject.Inject

@AndroidEntryPoint
class InferenceActivity : AppCompatActivity() {
    var isStarted: Boolean = false;
    var isJeda: Boolean = false;
    val viewModel:InferenceViewModel by viewModels()

    lateinit var statusText : TextView;
    var motifresource : Int=-1;
    lateinit var imgview:ImageView;

    @BaseUrl
    @Inject
    lateinit var BASEURL : String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inference)

        motifresource = intent.getIntExtra("motif", R.drawable.background)

        imgview = findViewById<ImageView>(R.id.tampilan)
        imgview.setImageDrawable(AppCompatResources.getDrawable(this, motifresource))

        val startstopbtn = findViewById<Button>(R.id.bt1)
        val recText = findViewById<TextView>(R.id.recText)
        startstopbtn.setOnClickListener {
            isStarted = !isStarted

            if (isStarted) {
                recText.visibility = View.VISIBLE
                startstopbtn.text = "Stop Rekam"
            } else {
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
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        statusText = findViewById(R.id.status)

        viewModel.status.observe(this){
            statusText.setText(it)
        }

        val handler = Handler(Looper.getMainLooper())
        val r: Runnable = object : Runnable {
            override fun run() {
                lifecycleScope.launch{
                    viewModel.getStatus()
                }
                handler.postDelayed(this, 1000)
            }
        }
        handler.postDelayed(r, 500)

        val handler2 = Handler(Looper.getMainLooper())
        val r2: Runnable = object : Runnable {
            override fun run() {
                if(isStarted){
                    imgview.setDrawingCacheEnabled(false);
                    imgview.setWillNotCacheDrawing(true);
                    imgview.load(BASEURL+"/image?"+ (0..1_000_000).random()){
                        crossfade(true)
                    }
                    imgview.scaleType = ImageView.ScaleType.FIT_CENTER
                }else{
                    imgview.setImageDrawable(AppCompatResources.getDrawable(applicationContext, motifresource))
                    imgview.scaleType = ImageView.ScaleType.FIT_XY
                }
                handler2.postDelayed(this, 1000)
            }
        }
        handler2.postDelayed(r2, 500)



    }


    suspend fun getCurrentImage() {

    }




}