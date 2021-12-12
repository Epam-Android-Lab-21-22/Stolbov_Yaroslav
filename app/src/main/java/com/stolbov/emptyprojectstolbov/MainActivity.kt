package com.stolbov.emptyprojectstolbov

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.stolbov.emptyprojectstolbov.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NetworkLiveData.init(application)

        binding.buttonNextActivity.setOnClickListener {
            startActivity(Intent(this, ActionActivity::class.java))
        }

        NetworkLiveData.observe(this, {
            observeAction(it, binding)
        })
    }

    private fun observeAction(
        it: Boolean,
        binding: ActivityMainBinding
    ) {
        if (it) {
            changeButtonClickable(binding, true)
        } else {
            changeButtonClickable(binding, false)
            Snackbar.make(binding.root, getString(R.string.snackbar_text), Snackbar.LENGTH_LONG)
                .show()
        }
    }

    private fun changeButtonClickable(binding: ActivityMainBinding, hasNetwork: Boolean) {
        binding.buttonNextActivity.isClickable = hasNetwork
        binding.buttonNextActivity.isEnabled = hasNetwork
    }


}