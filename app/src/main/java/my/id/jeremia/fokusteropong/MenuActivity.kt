package my.id.jeremia.fokusteropong

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        val button1 = findViewById<Button>(R.id.bt1)
        val button2 = findViewById<Button>(R.id.bt2)
        val button3 = findViewById<Button>(R.id.bt3)

        button1.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            Toast.makeText(this, "Dalam pengembangan", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, MenuActivity::class.java)
//            startActivity(intent)
        }

        button3.setOnClickListener{
            this.finishAffinity();
        }

    }
}
