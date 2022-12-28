package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.workmanager.worker.WorkerClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val uploadWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<WorkerClass>().build()

        WorkManager.getInstance(this).enqueue(uploadWorkRequest)

        CoroutineScope(Dispatchers.IO).launch {
            //perform background operations over here
        }

    }
}