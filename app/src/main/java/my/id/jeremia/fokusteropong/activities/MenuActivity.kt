package my.id.jeremia.fokusteropong.activities

import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView
import androidx.lifecycle.lifecycleScope
import com.thanosfisherman.wifiutils.WifiUtils
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionErrorCode
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionSuccessListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import my.id.jeremia.fokusteropong.BuildConfig
import my.id.jeremia.fokusteropong.DataStore.getSavedDetection
import my.id.jeremia.fokusteropong.DataStore.getSavedImgNetwork
import my.id.jeremia.fokusteropong.DataStore.getSavedKain
import my.id.jeremia.fokusteropong.R
import my.id.jeremia.fokusteropong.ViewModel.InferenceViewModel
import my.id.jeremia.fokusteropong.ViewModel.MenuViewModel


@AndroidEntryPoint
class MenuActivity : AppCompatActivity() {
    var isTryingtoConnect = false;
    val viewModel: MenuViewModel by viewModels()
    var handler: Handler? = null;
    var r : Runnable? = null;

    companion object {
        val TAG = "MenuActivity"
    }

    fun getMacId(): String {
        val wifiManager = getSystemService(WIFI_SERVICE) as WifiManager
        val wifiInfo = wifiManager.connectionInfo
        if (wifiInfo.bssid == null) return ""
        return wifiInfo.bssid
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        val button1 = findViewById<Button>(R.id.bt1)
        val button2 = findViewById<Button>(R.id.bt2)
        val button3 = findViewById<Button>(R.id.bt3)
        val button5 = findViewById<Button>(R.id.bt5)
        val statustxt = findViewById<TextView>(R.id.status)

        button1.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val kain = getSavedKain(this)
            if (kain == null) {
                Toast.makeText(this, "Belum ada kain tersimpan !", Toast.LENGTH_SHORT).show()
            } else {
                val id = getSavedDetection(this)
                val imgNetwork = getSavedImgNetwork(this)

                val intent = Intent(this, InferenceActivity::class.java)
                intent.putExtra("id", id!!.toInt())
                if (imgNetwork == "") {
                    intent.putExtra("motifResource", kain.toInt())
                } else {
                    intent.putExtra("motifNetwork", imgNetwork)
                }

                startActivity(intent)
            }
        }

        button5.setOnClickListener {
            val intent = Intent(this, HistoryUtama::class.java)
            startActivity(intent)
        }

        button3.setOnClickListener {
            this.finishAffinity();
        }

        viewModel.status.observe(this) {
            statustxt.setText(it)
        }


        handler = Handler(Looper.getMainLooper())
        r =  object : Runnable {
            override fun run() {

                if (WifiUtils.withContext(applicationContext).isWifiConnected) {
                    if (getMacId() == BuildConfig.SERVER_MACADDR) {
                        statustxt.text = "Terhubung"

                        lifecycleScope.launch {
                            viewModel.getStatus()
                        }

                    } else {
                        statustxt.text =
                            "Tidak Terhubung\nPastikan terhubung ke Wifi yang benar"
                    }
                } else {

                    if (!isTryingtoConnect) {
                        statustxt.text = "Perangkat belum terhubung ke WIFI"

                        try {
                            isTryingtoConnect = true;
                            statustxt.text = "Menghubungkan ke WIFI"

                        } catch (e: Exception) {
                            statustxt.text = "Gagal terhubung ke wifi"
                            Log.e(TAG, e.message.toString());
                        } finally {
                            isTryingtoConnect = false;
                        }

                    }
                }
                handler!!.postDelayed(this, 2000)
            }
        }

        handler!!.postDelayed(r!!, 500)


    }

    override fun onPause() {
        super.onPause()
        handler!!.removeMessages(0);
        handler!!.removeCallbacksAndMessages(null);
    }

    override fun onResume() {
        super.onResume()
        handler!!.post(r!!);
    }
}
