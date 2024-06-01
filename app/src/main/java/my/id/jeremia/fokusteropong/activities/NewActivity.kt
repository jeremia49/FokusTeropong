package my.id.jeremia.fokusteropong.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import my.id.jeremia.fokusteropong.R

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