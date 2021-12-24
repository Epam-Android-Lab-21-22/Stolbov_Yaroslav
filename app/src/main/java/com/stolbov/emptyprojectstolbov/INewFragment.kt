package com.stolbov.emptyprojectstolbov

import android.os.Bundle

interface INewFragment {
    fun newFragment(args: Bundle, stack: ParamBackStack? = null)
}