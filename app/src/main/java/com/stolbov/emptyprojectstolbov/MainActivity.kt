package com.stolbov.emptyprojectstolbov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var textResult: TextView
    private lateinit var textCounter: TextView
    private lateinit var buttonResult: Button
    private val stringTextCounter = "Количество твоих предсказаний: "
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textResult = findViewById(R.id.textResult)
        textCounter = findViewById(R.id.textCounter)
        buttonResult = findViewById(R.id.buttonResult)

        buttonResult.setOnClickListener{
            textResult.text = DecisionGenerator.generateDecision()
            counter++
            textCounter.text = stringTextCounter.plus(counter.toString())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run {
            putString("KEY_RESULT", textResult.text.toString())
            putInt("KEY_COUNTER", counter)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textResult.text = savedInstanceState.getString("KEY_RESULT")
        counter = savedInstanceState.getInt("KEY_COUNTER")
        textCounter.text = stringTextCounter.plus(counter.toString())
    }
}