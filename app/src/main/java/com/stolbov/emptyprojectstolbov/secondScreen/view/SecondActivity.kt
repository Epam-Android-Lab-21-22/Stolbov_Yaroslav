package com.stolbov.emptyprojectstolbov.secondScreen.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stolbov.emptyprojectstolbov.R
import com.stolbov.emptyprojectstolbov.data.ItemsViewModel
import com.stolbov.emptyprojectstolbov.databinding.ActivitySecondBinding
import com.stolbov.emptyprojectstolbov.firstScreen.view.FirstActivity
import com.stolbov.emptyprojectstolbov.secondScreen.ISecondInterface
import com.stolbov.emptyprojectstolbov.secondScreen.presenter.SecondPresenter

class SecondActivity : AppCompatActivity(), ISecondInterface.SecondViewInterface {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var secondAdapter: SecondAdapter
    private var presenter: SecondPresenter? = null
    private val itemsViewModel by lazy {
        ViewModelProviders.of(this).get(ItemsViewModel::class.java)
    }
    private val BASE_CATS_COUNT = 1
    private val GRID_COUNT_ITEMS = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.extras?.getInt(FirstActivity.CATS_COUNT)!! != BASE_CATS_COUNT) {
            itemsViewModel.countCatsSecondActivity =
                intent.extras?.getInt(FirstActivity.CATS_COUNT)!!
        }

        presenter = SecondPresenter(this)

        binding.table.apply {
            initRecyclerView()
        }

        binding.buttonToLinearScreen.setOnClickListener {
            saveData()
        }

        binding.addCatButton.setOnClickListener {
            presenter?.addCat(itemsViewModel)
        }
    }

    private fun RecyclerView.initRecyclerView() {
        layoutManager = GridLayoutManager(context, GRID_COUNT_ITEMS)
        secondAdapter = SecondAdapter(itemsViewModel)
        adapter = secondAdapter
    }

    override fun onBackPressed() {
        saveData()
    }

    private fun saveData() {
        val intent = Intent()
        intent.putExtra(FirstActivity.ACCESS_MESSAGE, itemsViewModel.countCatsSecondActivity)
        setResult(RESULT_OK, intent)
        finish()
    }



    override fun showNewCat() {
        binding.table.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        secondAdapter.cats++
        secondAdapter.notifyItemInserted(secondAdapter.cats + 1)
    }

    override fun showWaitState() {
        binding.table.setBackgroundColor(ContextCompat.getColor(this, R.color.grey))
    }
}