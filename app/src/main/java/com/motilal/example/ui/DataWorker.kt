package com.motilal.example.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.motilal.example.data.GithubRepository
import android.os.Handler
import android.os.Looper


class DataWorker @WorkerInject constructor(
    @Assisted var context: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: GithubRepository
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        repository.getSearchResults("");
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, "Toast", Toast.LENGTH_LONG).show()
        }

        Log.e("Worker","worked")
        return Result.success()
    }
}
