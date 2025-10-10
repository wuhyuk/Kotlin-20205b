package com.appweek04

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CounterActivity: AppCompatActivity() {
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)

        val textViewCount = findViewById<textView>(R.id.textViewCount)
        val buttonMinus = findViewById<Btton>(R.id.buttonMinus)
        val buttonPlus = findViewById<Btton>(R.id.buttonPlus)
        val buttonReset = findViewById<Btton>(R.id.buttonReset)

        buttonMinus.setOnClickListener {
            count--
            textViewCount.text = count.toSting()
            log.d("kotlinWeek04app", "${count}")
        }
        buttonPlus.setOnClickListener {
            count++
            textViewCount.text = count.toSting()
            log.d("kotlinWeek04app", "${count}")
        }
        buttonReset.setOnClickListener {
            count = 0
            textViewCount.text = count.toSting()
            log.d("kotlinWeek04app", "${count}")
        }
    }
}