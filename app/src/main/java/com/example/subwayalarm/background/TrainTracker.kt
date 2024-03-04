package com.example.subwayalarm.background

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.subwayalarm.ui.theme.subway.TrainViewModel


class TrainTracker(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) { // 코루틴 워커도 가능하다고 함.
    // 하고자 하는 작업을 여기서 정의한다
    override fun doWork(): Result {
        return try {

            Log.d(ContentValues.TAG, "I'm hard worker, but i will sleep")
            Log.d(ContentValues.TAG, "Who woke up. I'm tired.")
            Result.success() // return statement

        } catch (e: Exception) {
            Log.d(ContentValues.TAG, "Worker Exception $e")
            Result.failure() // return statement
        }
    }

}
