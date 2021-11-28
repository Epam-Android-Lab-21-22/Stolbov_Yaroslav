package com.stolbov.emptyprojectstolbov

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var buttonResult: Button
    private lateinit var editHeight: EditText
    private lateinit var editWeight: EditText
    private lateinit var textResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonResult = findViewById(R.id.button_result)
        editHeight = findViewById(R.id.edit_height)
        editWeight = findViewById(R.id.edit_weight)
        textResult = findViewById(R.id.text_view_result)

        buttonResult.setOnClickListener {

            if (checkFields()) return@setOnClickListener

            textResult.text = IBMCalculator.getIBM(editHeight.text.toString().toInt(), editWeight.text.toString().toInt())

        }
    }

    private fun checkFields(): Boolean {
        if (editHeight.text.toString() == "" || editWeight.text.toString() == "") {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            return true
        } else if (editHeight.text.toString().toInt() <= 0 || editWeight.text.toString().toInt() <= 0
        ) {
            Toast.makeText(this, "Некорректное значение", Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run {
            putString("KEY", textResult.text.toString())
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textResult.text = savedInstanceState.getString("KEY")
    }
}