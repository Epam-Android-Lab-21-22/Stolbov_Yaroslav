package com.stolbov.emptyprojectstolbov

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged


class MainActivity : AppCompatActivity() {
    private lateinit var textViewCounterButtonClick: TextView
    private lateinit var textViewCounterSymbols: TextView
    private lateinit var buttonOK: Button
    private lateinit var editText: EditText
    private var countClicks = 0
    private var countSymbols = 0
    private lateinit var counterClickString: String
    private lateinit var counterSymbolsString: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initStringResources()

        intiViews()

        buttonOK.setOnClickListener {
            countClicks++
            textViewCounterButtonClick.text = counterClickString.plus(" ").plus(countClicks)
            editText.setText("")
        }

        editText.doAfterTextChanged {
            if (it != null) {
                textViewCounterSymbols.text = counterSymbolsString.plus(" ").plus(it.length)
            } else {
                textViewCounterSymbols.text = counterSymbolsString.plus(" ").plus(0)
            }
        }
    }

    private fun initStringResources() {
        counterClickString = resources.getString(R.string.text_wish_count)
        counterSymbolsString = resources.getString(R.string.text_count_symbols)
    }


    private fun intiViews() {
        textViewCounterButtonClick = findViewById(R.id.text_view_button_clicks)
        textViewCounterButtonClick.text = counterClickString.plus(" ").plus(countClicks)
        textViewCounterSymbols = findViewById(R.id.text_view_symbols_count)
        textViewCounterSymbols.text = counterSymbolsString.plus(" ").plus(countSymbols)
        buttonOK = findViewById(R.id.button_wish)
        editText = findViewById(R.id.edit_text)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run {
            putString("EDIT_TEXT", editText.text.toString())
            putInt("KEY_COUNTER_SYMBOLS", countSymbols)
            putInt("KEY_COUNTER_CLICKS", countClicks)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        editText.setText(savedInstanceState.getString("EDIT_TEXT"))
        countSymbols = savedInstanceState.getInt("KEY_COUNTER_SYMBOLS")
        textViewCounterSymbols.text = counterSymbolsString.plus(" ").plus(countSymbols.toString())
        countClicks = savedInstanceState.getInt("KEY_COUNTER_CLICKS")
        textViewCounterButtonClick.text = counterClickString.plus(" ").plus(countClicks.toString())
    }

}