package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.review.ReviewInfo
import com.example.myapplication.ReviewManger
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.myapplication.R
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.tasks.OnCompleteListener
import android.widget.Toast
import com.google.android.play.core.tasks.Task

class MainActivity : AppCompatActivity() {
    private val reviewInfo: ReviewInfo? = null
    private var manager: ReviewManger? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener { view: View? -> startReviewFlow() }
    }

    fun activateReviewinfo() {
        manager = ReviewManagerFactory.create(this) as ReviewManger
        val managerInfoTask = manager!!.requestReviewFlow()
        val request: Task<Any?>? = null
        request!!.addOnCompleteListener { task: Task<Any?> ->
            if (task.isSuccessful) {
                // We can get the ReviewInfo object
                val reviewInfo = task.result as ReviewInfo
            } else {
                // There was some problem, log or handle the error code.
                Toast.makeText(this, "no seen", Toast.LENGTH_LONG).show()
            }
        }
    }


    fun startReviewFlow() {
        if (reviewInfo != null) {
            val flow = manager!!.launchReviewFlow<Any>(this, reviewInfo)
            flow.addOnCompleteListener { task: Task<Void?>? ->
                Toast.makeText(this, "Rating done", Toast.LENGTH_SHORT).show()
            }
        }
    }
}