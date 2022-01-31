package com.stolbov.emptyprojectstolbov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import androidx.preference.PreferenceManager
import androidx.lifecycle.ViewModelProvider
import com.stolbov.emptyprojectstolbov.database.AppDatabase
import com.stolbov.emptyprojectstolbov.database.JustString
import com.stolbov.emptyprojectstolbov.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(this))
        ).get(MainViewModel::class.java)

        mainBinding.text.text = viewModel.lastString

        if (!isExternalStorageWritable()) {
            disableExternalButtons()
        }

        mainBinding.apply {
            buttonSaveShared.setOnClickListener {
                viewModel.savePref(mainBinding.editText.text.toString())
            }
            buttonSaveInternal.setOnClickListener {
                viewModel.saveInternal(applicationContext, mainBinding.editText.text.toString())
            }
            buttonSaveExternal.setOnClickListener {
                viewModel.saveExternal(applicationContext, mainBinding.editText.text.toString())
            }
            buttonSaveDb.setOnClickListener {
                viewModel.saveDB(applicationContext, mainBinding.editText.text.toString())
            }

            buttonLoadShared.setOnClickListener {
                mainBinding.text.text = viewModel.loadPref()
            }
            buttonLoadInternal.setOnClickListener {
                mainBinding.text.text = viewModel.loadInternal(applicationContext)
            }
            buttonLoadExternal.setOnClickListener {
                mainBinding.text.text = viewModel.loadExternal(applicationContext)
            }
            buttonLoadDb.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    mainBinding.text.text = viewModel.loadDB(applicationContext)
                }
            }
        }
    }


    private fun disableExternalButtons() {
        mainBinding.apply {
            buttonSaveExternal.isClickable = false
            buttonSaveExternal.isEnabled = false
            buttonLoadExternal.isClickable = false
            buttonLoadExternal.isEnabled = false
        }
    }

    private fun isExternalStorageWritable(): Boolean {
        val state = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == state
    }

    companion object {
        val SHARED_KEY = "KEY"
    }
}