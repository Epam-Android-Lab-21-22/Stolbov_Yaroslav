package com.stolbov.emptyprojectstolbov.firstScreen.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stolbov.emptyprojectstolbov.firstScreen.model.MyItem
import com.stolbov.emptyprojectstolbov.databinding.ActivityMainBinding

import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import com.stolbov.emptyprojectstolbov.data.ItemsViewModel
import com.stolbov.emptyprojectstolbov.firstScreen.IFirstInterface
import com.stolbov.emptyprojectstolbov.firstScreen.presenter.FirstPresenter
import com.stolbov.emptyprojectstolbov.secondScreen.view.SecondActivity


class FirstActivity : AppCompatActivity(), IFirstInterface.FirstViewInterface {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: FirstAdapter
    private var presenter: FirstPresenter? = null
    private val itemsViewModel by lazy {
        ViewModelProviders.of(this).get(ItemsViewModel::class.java)
    }

    private val DEFAULT_COUNT_CATS = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = FirstPresenter(this)

        val activityResultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
            getDataCats(result)
        }

        binding.list.apply {
            initRecyclerView()
        }

        myAdapter.updateItems(getItems())//TODO

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
            val countCats = intent!!.getIntExtra(ACCESS_MESSAGE, DEFAULT_COUNT_CATS)
            itemsViewModel.countCatsSecondActivity = countCats
        }
    }

    private fun RecyclerView.initRecyclerView() {
        layoutManager = LinearLayoutManager(context)
        myAdapter = FirstAdapter(itemsViewModel)
        adapter = myAdapter
    }

    private fun getItems(): List<MyItem> {
        return itemsViewModel.items//TODO
    }

    companion object {
        const val INIT_LIST_SIZE: Int = 30
        val ACCESS_MESSAGE = "ACCESS_MESSAGE"
        val CATS_COUNT = "cats_count"
    }
}