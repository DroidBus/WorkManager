package com.example.workmanager.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkerClass(appContext: Context, workerParams: WorkerParameters) : Worker(appContext,workerParams) {
    override fun doWork(): Result {
        Log.d("WorkerClass","It's Working")

       return Result.success()
    }
}