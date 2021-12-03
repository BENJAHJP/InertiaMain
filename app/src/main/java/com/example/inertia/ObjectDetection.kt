package com.example.inertia

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.inertia.ml.MobilenetV110224Quant
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

class ObjectDetection : AppCompatActivity() {
    private lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_upload)

        val fileName = "label.txt"
        val inputString = application.assets.open(fileName).bufferedReader().use{ it.readText() }
        val townList = inputString.split("\n")

        val textView : TextView = findViewById(R.id.recognitionText1)
        val uploadButton: FloatingActionButton = findViewById(R.id.imageButton1)
        val detectButton: MaterialButton = findViewById(R.id.detectButton1)

        detectButton.setOnClickListener {

            val resized: Bitmap = Bitmap.createScaledBitmap(bitmap,224,224,true)
            val model = MobilenetV110224Quant.newInstance(this)

            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1,224,224,3), DataType.UINT8)
            val tensorBuffer = TensorImage.fromBitmap(resized)
            val byteBuffer = tensorBuffer.buffer

            inputFeature0.loadBuffer(byteBuffer)

            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            val max = getMax(outputFeature0.floatArray)

            textView.text = townList[max]

            model.close()
        }

        uploadButton.setOnClickListener {
            checkPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE,100)
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"

            startActivityForResult(intent, 100)
        }
    }

    private fun getMax(floatArray: FloatArray): Int {
        var index = 0
        var min = 0.0f

        for (i in 0..1000) {
            if (floatArray[i] > min) {
                index = i
                min = floatArray[i]
            }
        }
        return index
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK){
            val imageView: ImageView = findViewById(R.id.uploadImage1)
            imageView.setImageURI(data?.data)
            val uri: Uri? = data?.data

            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
        }
    }

    private fun checkPermission(permission: String, requestCode: Int){
        if(ContextCompat.checkSelfPermission(this,permission) == PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(permission),requestCode)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 100 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"Files Access Permission Granted", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Permission Denied", Toast.LENGTH_LONG).show()
        }
    }
}