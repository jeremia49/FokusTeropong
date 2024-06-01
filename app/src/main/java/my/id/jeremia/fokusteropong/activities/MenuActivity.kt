package my.id.jeremia.fokusteropong.activities

import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.thanosfisherman.wifiutils.WifiUtils
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionErrorCode
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionSuccessListener
import my.id.jeremia.fokusteropong.BuildConfig
import my.id.jeremia.fokusteropong.R


class MenuActivity : AppCompatActivity() {
    var isTryingtoConnect = false;

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
        val statustxt = findViewById<TextView>(R.id.status)

        button1.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            Toast.makeText(this, "Dalam pengembangan", Toast.LENGTH_SHORT).show()
        }

        button3.setOnClickListener {
            this.finishAffinity();
        }

        val handler = Handler(Looper.getMainLooper())
        val r: Runnable = object : Runnable {
            override fun run() {

                if (WifiUtils.withContext(applicationContext).isWifiConnected) {
                    if (getMacId() == BuildConfig.SERVER_MACADDR) {
                        statustxt.text = "Terhubung"
                    } else {
                        statustxt.text =
                            "Tidak Terhubung\nPastikan terhubung ke Wifi yang benar"

                        try{
                            WifiUtils
                                .withContext(applicationContext)
                                .connectWith(BuildConfig.SERVER_SSID)
                                .setTimeout(10000)
                                .onConnectionResult(object : ConnectionSuccessListener {
                                    override fun success() {
                                        statustxt.text = "Berhasil terhubung ke wifi"
                                    }

                                    override fun failed(errorCode: ConnectionErrorCode) {
                                        Log.e(TAG, errorCode.toString());
                                    }
                                })
                                .start()
                        }catch(_:Exception){}

                    }
                } else {

                    if (!isTryingtoConnect) {
                        statustxt.text = "Perangkat belum terhubung ke WIFI"

                        try {
                            isTryingtoConnect = true;
                            statustxt.text = "Menghubungkan ke WIFI"

                            WifiUtils
                                .withContext(applicationContext)
                                .connectWith(BuildConfig.SERVER_SSID)
                                .setTimeout(10000)
                                .onConnectionResult(object : ConnectionSuccessListener {
                                    override fun success() {
                                        statustxt.text = "Berhasil terhubung ke wifi"
                                    }

                                    override fun failed(errorCode: ConnectionErrorCode) {
                                        statustxt.text =
                                            "Gagal terhubung ke wifi"
                                        Log.e(TAG, errorCode.toString());
                                    }
                                })
                                .start()
                        } catch (e: Exception) {
                            statustxt.text = "Gagal terhubung ke wifi"
                            Log.e(TAG, e.message.toString());
                        } finally {
                            isTryingtoConnect = false;
                        }

                    }
                }
                handler.postDelayed(this, 2000)
            }
        }

        handler.postDelayed(r, 500)


    }
}
