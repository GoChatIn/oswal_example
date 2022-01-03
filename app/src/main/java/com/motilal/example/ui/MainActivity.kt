package com.motilal.example.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.motilal.example.R
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*val photoCheckBuilder = PeriodicWorkRequest.Builder(
            DataWorker::class.java, 15, TimeUnit.MINUTES
        )
        val request = photoCheckBuilder.build()
        WorkManager.getInstance()
            .enqueueUniquePeriodicWork("worker-trending", ExistingPeriodicWorkPolicy.KEEP, request)
*/
        val notificationRequest = PeriodicWorkRequestBuilder<DataWorker>(
            NOTIFICATION_REPEAT_INTERVAL_IN_MINUTES , TimeUnit.MINUTES,
            NOTIFICATION_FLEX_TIME_IN_MINUTES , TimeUnit.MINUTES
        )
            .addTag("NOTIFICATION_TAG")
            .build()

        WorkManager.getInstance(this).enqueue(notificationRequest)
    }
    companion object {
        private const val NOTIFICATION_REPEAT_INTERVAL_IN_MINUTES = 15L
        private const val NOTIFICATION_FLEX_TIME_IN_MINUTES = 14L
    }
}