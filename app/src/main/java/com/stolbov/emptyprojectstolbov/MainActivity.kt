package com.stolbov.emptyprojectstolbov

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.stolbov.emptyprojectstolbov.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), INewFragment {
    private var currentBackStack = ParamBackStack.RED
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)


        if (savedInstanceState == null) {
            initStack(ParamBackStack.RED)
            initStack(ParamBackStack.BLUE)
            initStack(ParamBackStack.GREEN)
            mainBinding.bottomNavigation.selectedItemId = R.id.item1
        } else {
            currentBackStack =
                getStack(mainBinding.bottomNavigation.selectedItemId) ?: ParamBackStack.RED
        }

        supportFragmentManager.addOnBackStackChangedListener {
            val badge =
                mainBinding.bottomNavigation.getOrCreateBadge(mainBinding.bottomNavigation.selectedItemId)
            val count = supportFragmentManager.backStackEntryCount
            badge.number = count - 1
            badge.isVisible = count > 1
        }

        mainBinding.bottomNavigation.setOnItemSelectedListener {
            supportFragmentManager.saveBackStack(currentBackStack.nameStack)
            getStack(it.itemId)?.also { stack ->
                supportFragmentManager.restoreBackStack(stack.nameStack)
                currentBackStack = stack
                mainBinding.toolbarText.text = getString(stack.title)
            }
            true
        }
    }

    override fun newFragment(args: Bundle, stack: ParamBackStack?) {
        val stackName =
            stack?.nameStack ?: getStack(mainBinding.bottomNavigation.selectedItemId)?.nameStack
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<ColorsFragment>(R.id.container_fragment, args = args)
            addToBackStack(stackName)
        }
    }

    private fun getStack(@IdRes itemId: Int) = when (itemId) {
        R.id.item1 -> ParamBackStack.RED
        R.id.item2 -> ParamBackStack.BLUE
        R.id.item3 -> ParamBackStack.GREEN
        else -> null
    }

    private fun initStack(stack: ParamBackStack) {
        newFragment(
            ColorsFragment.data(
                counter = 0,
                color = ContextCompat.getColor(this, stack.color)
            ), stack = stack
        )
        supportFragmentManager.saveBackStack(stack.nameStack)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) finish()
        else supportFragmentManager.popBackStack()
    }
}
