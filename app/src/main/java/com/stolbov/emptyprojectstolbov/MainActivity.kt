package com.stolbov.emptyprojectstolbov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stolbov.emptyprojectstolbov.adapter.MyAdapter
import com.stolbov.emptyprojectstolbov.data.MyItem
import com.stolbov.emptyprojectstolbov.databinding.ActivityMainBinding

import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: MyAdapter

    private val itemsViewModel by lazy {
        ViewModelProviders.of(this).get(ItemsViewModel::class.java)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activityResultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
            getDataCats(result)
        }

        binding.list.apply {
            initRecyclerView()
        }

        myAdapter.updateItems(getItems())

        binding.buttonToGridScreen.setOnClickListener {
            launchSecondActivity(activityResultLauncher)
        }
    }

    private fun launchSecondActivity(activityResultLauncher: ActivityResultLauncher<Intent>) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(CATS_COUNT, itemsViewModel.countCatsSecondActivity)
        activityResultLauncher.launch(intent)
    }

    private fun getDataCats(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            val intent = result.data
            val countCats = intent!!.getIntExtra(ACCESS_MESSAGE, 1)
            itemsViewModel.countCatsSecondActivity = countCats
        }
    }

    private fun RecyclerView.initRecyclerView() {
        layoutManager = LinearLayoutManager(context)
        myAdapter = MyAdapter(itemsViewModel)
        adapter = myAdapter
    }

    private fun getItems(): List<MyItem> {
        return itemsViewModel.items
    }

    companion object {
        const val INIT_LIST_SIZE: Int = 30
        val ACCESS_MESSAGE = "ACCESS_MESSAGE"
        val CATS_COUNT = "cats_count"
    }
}