package com.example.workmanager.foregroundservices.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import com.example.workmanager.R
import com.example.workmanager.databinding.ActivityForegroundServiceBinding
import com.example.workmanager.foregroundservices.service.ForegroundService

class ForegroundServiceActivity : AppCompatActivity() {
    lateinit var binding: ActivityForegroundServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityForegroundServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStartService.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Handler(Looper.getMainLooper()).post{
                    startService()
                }
            } else {
                startService(Intent(this, ForegroundService::class.java))
            }
        }

        binding.buttonStopService.setOnClickListener {
            stopService()
        }
    }


    private fun  startService(){
         val intent = Intent(this,ForegroundService::class.java)
        intent.putExtra("inputExtra","Foreground Service example in android")
        ContextCompat.startForegroundService(this,intent)
    }

    private fun stopService(){
        val intent = Intent(this, ForegroundService::class.java)
        stopService(intent)
    }
}