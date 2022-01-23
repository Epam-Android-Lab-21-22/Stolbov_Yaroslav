package com.stolbov.emptyprojectstolbov.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stolbov.emptyprojectstolbov.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

    }
}