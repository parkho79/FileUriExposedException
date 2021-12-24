package com.parkho.fileUriExposedException

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import androidx.core.content.FileProvider
import java.io.File

class MainActivity : AppCompatActivity() {
    companion object {
        // File URI provider
        const val FileUriProvider = BuildConfig.APPLICATION_ID + ".fileProvider"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_share).setOnClickListener {
            val intent = Intent().apply {
                val file = File(Environment.getExternalStorageDirectory().toString() + File.separator + "text.txt")
                // 기존 방법
                // val fileUri = Uri.fromFile(file)

                // FileProvider 사용
                val fileUri = FileProvider.getUriForFile(
                    this@MainActivity,
                    FileUriProvider,
                    file
                )

                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_STREAM, fileUri)
            }
            startActivity(intent)
        }
    }
}