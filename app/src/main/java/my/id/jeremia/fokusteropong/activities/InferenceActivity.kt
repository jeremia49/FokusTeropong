package my.id.jeremia.fokusteropong.activities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import com.github.dhaval2404.imagepicker.ImagePicker
import com.thanosfisherman.wifiutils.WifiUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import my.id.jeremia.fokusteropong.BuildConfig
import my.id.jeremia.fokusteropong.DataStore.saveKain
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

    var handler2:Handler? = null;
    var runnable2:Runnable? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        enableEdgeToEdge()
        setContentView(R.layout.activity_inference)

        motifresource = intent.getIntExtra("motifResource",-1);

        val containerwarningtextpopup = findViewById<View>(R.id.containerwarningtextpopup)
        val warningtextpopup = findViewById<TextView>(R.id.warningtextpopup)
        val btnlanjutkanpopup = findViewById<Button>(R.id.btnlanjutkanpopup)

        imgview = findViewById<ImageView>(R.id.tampilan)
        if(motifresource == -1){
            val motiflink = intent.getStringExtra("motifNetwork");
            imgview.load(motiflink);
        }else{
            imgview.setImageDrawable(AppCompatResources.getDrawable(this, motifresource))
        }

        val iddetection = intent.getIntExtra("id",-1);
        runBlocking {
            viewModel.setHistoryID(iddetection)
        }

        val startstopbtn = findViewById<Button>(R.id.bt1)
        val recText = findViewById<ImageView>(R.id.recText)
        startstopbtn.setOnClickListener {
            isStarted = !isStarted

            if (isStarted) {
                recText.visibility = View.VISIBLE
                startstopbtn.text = "Stop Rekam"
                lifecycleScope.launch{
                    viewModel.start()
                }
                handler2!!.post(runnable2!!)
            } else {
                recText.visibility = View.INVISIBLE
                startstopbtn.text = "Mulai Rekam"
                lifecycleScope.launch{
                    viewModel.stop()
                }

                handler2!!.removeMessages(0);
                handler2!!.removeCallbacksAndMessages(null);

                if(motifresource == -1){
                    val motiflink = intent.getStringExtra("motifNetwork");
                    imgview.load(motiflink);
                }else{
                    imgview.setImageDrawable(AppCompatResources.getDrawable(this@InferenceActivity, motifresource))
                }
                imgview.scaleType = ImageView.ScaleType.FIT_XY


            }
        }

        btnlanjutkanpopup.setOnClickListener {
            if(btnlanjutkanpopup.text == "LANJUTKAN PENGERJAAN"){
                isJeda = false;
                containerwarningtextpopup.visibility = View.INVISIBLE
                warningtextpopup.text = ""
                btnlanjutkanpopup.text = ""
            }else{
                containerwarningtextpopup.visibility = View.INVISIBLE
                lifecycleScope.launch{
                    viewModel.start()
                }
            }
        }

        val jedabtn = findViewById<Button>(R.id.bt2)
        jedabtn.setOnClickListener {
            isJeda = !isJeda

            if(isJeda){
                containerwarningtextpopup.visibility = View.VISIBLE
                warningtextpopup.text = "PENGERJAAN SEDANG DIJEDA!!!"
                btnlanjutkanpopup.text = "LANJUTKAN PENGERJAAN"
                lifecycleScope.launch{
                    viewModel.stop()
                }
            }else{
                containerwarningtextpopup.visibility = View.INVISIBLE
                warningtextpopup.text = ""
                btnlanjutkanpopup.text = ""
            }

//            if(isJeda){
//                startstopbtn.text = "Stop Rekam"
//            }
        }

        val stopandsavebtn = findViewById<Button>(R.id.bt3)
        stopandsavebtn.setOnClickListener {
            if(motifresource == -1){
                val motiflink = intent.getStringExtra("motifNetwork");
                saveKain(this, "", iddetection.toString(),motiflink!! )
            }else{
                saveKain(this, motifresource.toString(), iddetection.toString(),"" )
            }

            val intent = Intent(this, MenuActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        statusText = findViewById(R.id.status)


        viewModel.status.observe(this){
            statusText.setText(it)
            if(it == "UNKNOWN") return@observe
            if(it == "Alat Ready" || it == "Gambar Benar!"){
//                containerwarningtextpopup.visibility = View.INVISIBLE
            }else{
                containerwarningtextpopup.visibility = View.VISIBLE
                if(it == "Gambar Salah!" && !isJeda && isStarted){
                    warningtextpopup.text = "MOTIF TIDAK SESUAI!!!"
                }else{
                    warningtextpopup.text = "TEROPONG TIDAK SESUAI!!!"
                }
                lifecycleScope.launch{
                    viewModel.stop()
                }
                btnlanjutkanpopup.text = "LANJUTKAN"
            }
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

        handler2 = Handler(Looper.getMainLooper())
        runnable2 = object : Runnable {
            override fun run() {
                if(isStarted){
                    imgview.setDrawingCacheEnabled(false);
                    imgview.setWillNotCacheDrawing(true);
                    imgview.load(BASEURL+"/image?time="+ System.currentTimeMillis()){
                        crossfade(true)
                    }
                    imgview.scaleType = ImageView.ScaleType.FIT_CENTER
                }else{
                    if(motifresource == -1){
                        val motiflink = intent.getStringExtra("motifNetwork");
                        imgview.load(motiflink);
                    }else{
                        imgview.setImageDrawable(AppCompatResources.getDrawable(this@InferenceActivity, motifresource))
                    }
                    imgview.scaleType = ImageView.ScaleType.FIT_XY
                }
                handler2!!.postDelayed(this, 2000)
            }
        }



    }


    suspend fun getCurrentImage() {

    }




}