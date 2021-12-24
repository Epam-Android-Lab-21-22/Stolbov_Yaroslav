package com.stolbov.emptyprojectstolbov

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import com.stolbov.emptyprojectstolbov.databinding.FragmentColorsBinding



class ColorsFragment : Fragment(R.layout.fragment_colors) {
    private lateinit var binding: FragmentColorsBinding

    companion object{
        const val KEY_VALUE = "KEY_COUNTER"
        const val KEY_COLOR = "KEY_COLOR"

        fun data(counter: Int, color: Int) = bundleOf(
            KEY_VALUE to counter,
            KEY_COLOR to color
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentColorsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val counter = requireArguments().getInt(KEY_VALUE)
        val color = requireArguments().getInt(KEY_COLOR)

        binding.root.setBackgroundColor(color)
        binding.colorText.text = counter.toString()
        binding.root.setOnClickListener {
            val args = data(counter + 1, color)
            (requireActivity() as? INewFragment)?.newFragment(args)
        }
    }
}

