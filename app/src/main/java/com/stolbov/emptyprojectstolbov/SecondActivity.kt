package com.stolbov.emptyprojectstolbov

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stolbov.emptyprojectstolbov.adapter.SecondAdapter
import com.stolbov.emptyprojectstolbov.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var secondAdapter: SecondAdapter
    private val itemsViewModel by lazy {
        ViewModelProviders.of(this).get(ItemsViewModel::class.java)
    }
    private val BASE_CATS_COUNT = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.extras?.getInt(MainActivity.CATS_COUNT)!! != BASE_CATS_COUNT) {
            itemsViewModel.countCatsSecondActivity =
                intent.extras?.getInt(MainActivity.CATS_COUNT)!!
        }
        binding.table.apply {
            initRecyclerView()
        }



        binding.buttonToLinearScreen.setOnClickListener {
            saveData()
        }

        binding.addCatButton.setOnClickListener {
            secondAdapter.addItemCat()
        }
    }

    private fun RecyclerView.initRecyclerView() {
        layoutManager = GridLayoutManager(context, 2)
        secondAdapter = SecondAdapter(itemsViewModel)
        adapter = secondAdapter
    }

    override fun onBackPressed() {
        saveData()
    }

    private fun saveData() {
        val intent = Intent()
        intent.putExtra(MainActivity.ACCESS_MESSAGE, itemsViewModel.countCatsSecondActivity)
        setResult(RESULT_OK, intent)
        finish()
    }
}