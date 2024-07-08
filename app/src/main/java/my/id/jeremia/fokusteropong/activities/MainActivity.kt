package my.id.jeremia.fokusteropong.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.permissionx.guolindev.PermissionX
import android.Manifest.permission
import android.util.Log
import my.id.jeremia.fokusteropong.R

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        PermissionX.init(this)
            .permissions(permission.ACCESS_COARSE_LOCATION, permission.ACCESS_FINE_LOCATION,
                )
            .explainReasonBeforeRequest()
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(deniedList, "Permission ini dibutuhkan untuk mengakses WIFI", "OK", "Cancel")
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(deniedList, "You need to allow necessary permissions in Settings manually", "OK", "Cancel")
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    //Toast.makeText(this, "All permissions are granted", Toast.LENGTH_LONG).show()
                } else {
                    Log.e(TAG, "Some permissions are denied: $deniedList")
                    Toast.makeText(this, "These permissions are denied: $deniedList", Toast.LENGTH_LONG).show()
                }
            }


    }
}