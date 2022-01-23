package com.stolbov.emptyprojectstolbov.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.stolbov.emptyprojectstolbov.databinding.ActivitySplashBinding
import com.stolbov.emptyprojectstolbov.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var splashBinding: ActivitySplashBinding
    private val viewModel: SplashViewModel by lazy {
        ViewModelProvider(this).get(SplashViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        viewModel.currentProgress.observe(this) { value ->
            if (value == 100) {
                finishSplash()
                return@observe
            }
            splashBinding.progressBar.progress = value
        }

        viewModel.imitationOfLoad()
    }

    private fun finishSplash() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


}