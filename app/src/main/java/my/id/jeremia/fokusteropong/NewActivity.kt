package my.id.jeremia.fokusteropong

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new)

        val button = findViewById<Button>(R.id.bt1)
        button.setOnClickListener {
            val intent = Intent(this, PilihMotifActivity::class.java)
            startActivity(intent)
        }
    }
}