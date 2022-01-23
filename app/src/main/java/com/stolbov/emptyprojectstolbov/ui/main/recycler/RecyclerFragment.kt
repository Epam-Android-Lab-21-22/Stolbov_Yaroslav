package com.stolbov.emptyprojectstolbov.ui.main.recycler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stolbov.emptyprojectstolbov.R
import com.stolbov.emptyprojectstolbov.databinding.FragmentRecyclerBinding

class RecyclerFragment : Fragment(R.layout.fragment_recycler) {
    private var _binding: FragmentRecyclerBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RecyclerViewModel by lazy{
        ViewModelProvider(this).get(RecyclerViewModel::class.java)
    }
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.updateData()

        initRecycler()

        viewModel.songsMap.observe(viewLifecycleOwner){ value ->
            recyclerView.adapter?.notifyDataSetChanged()
        }

        return view
    }

    private fun initRecycler() {
        recyclerView = binding.recycler
        recyclerView.apply {
            layoutManager = LinearLayoutManager(view?.context)
            adapter = MainAdapter(viewModel)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}