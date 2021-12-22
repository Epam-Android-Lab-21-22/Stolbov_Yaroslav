package com.stolbov.emptyprojectstolbov

import androidx.annotation.ColorRes
import androidx.annotation.StringRes

enum class ParamBackStack(val nameStack: String, @StringRes val title: Int, @ColorRes val color: Int) {
    RED("RED", R.string.title_red, R.color.red),
    GREEN("GREEN", R.string.title_green, R.color.green),
    BLUE("BLUE", R.string.title_blue, R.color.blue),
}