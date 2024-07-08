package my.id.jeremia.fokusteropong.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import my.id.jeremia.fokusteropong.R
import my.id.jeremia.fokusteropong.ViewModel.InferenceViewModel
import my.id.jeremia.fokusteropong.ViewModel.NewActivityViewModel
import java.io.ByteArrayOutputStream
import java.io.File


@AndroidEntryPoint
class NewActivity : AppCompatActivity() {
    val viewModel: NewActivityViewModel by viewModels()

    var namaMotif : String = "";
    var pickedImage : Uri? = null;

    fun encodeImageToBase64(uri: Uri): String {
        val bm = BitmapFactory.decodeFile(uri.path)
        val baos = ByteArrayOutputStream()
        bm.compress(
            Bitmap.CompressFormat.JPEG,
            100,
            baos
        ) // Compress bitmap into ByteArrayOutputStream
        val byteArrayImage = baos.toByteArray() // Convert ByteArrayOutputStream to byte array
        return Base64.encodeToString(
            byteArrayImage,
            Base64.DEFAULT
        ) // Encode byte array to Base64 string
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                val fileUri = data?.data!!
                pickedImage = fileUri

                val base64image = encodeImageToBase64(pickedImage!!);

                runBlocking {
                    viewModel.addKain(namaMotif,base64image);
                }
                Toast.makeText(this, "Image Uploaded", Toast.LENGTH_SHORT).show()
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }

    private fun namaMotifInputDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Masukkan Nama Motif")

        // Set up the input
        val viewInflated: View = LayoutInflater.from(this).inflate(my.id.jeremia.fokusteropong.R.layout.dialoginput, null)
        val input = viewInflated.findViewById<EditText>(R.id.edit_text_input)
        builder.setView(viewInflated)

        // Set up the buttons
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            namaMotif = input.text.toString().trim { it <= ' ' }
            ImagePicker.with(this)
                .compress(1024)
                .maxResultSize(1080, 1080)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }



        })
        builder.setNegativeButton("Cancel",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new)


        val button = findViewById<Button>(R.id.bt1)
        button.setOnClickListener {
            val intent = Intent(this, PilihMotifActivity::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<Button>(R.id.bt2)
        button2.setOnClickListener {
            namaMotifInputDialog()

        }
    }
}