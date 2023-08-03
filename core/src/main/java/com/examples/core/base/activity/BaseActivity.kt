package com.examples.core.base.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.examples.core.R
import com.examples.core.utils.LoadingListener

abstract class BaseActivity : AppCompatActivity(), View.OnClickListener, LoadingListener {

    private lateinit var navFragment: NavHostFragment
    private lateinit var navController: NavController

    abstract var navGraphResourceId: Int
    private lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)
        setNavFragment()
    }

    override fun onClick(view: View?) {

    }

    private fun setNavFragment() {
        navFragment =
            supportFragmentManager.findFragmentById(R.id.common_nav_fragment) as NavHostFragment
        navController = navFragment.navController
        if (::bundle.isInitialized)
            navController.setGraph(navGraphResourceId, bundle)
        else
            navController.setGraph(navGraphResourceId)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (!navController.popBackStack()) {
            super.onBackPressed()
        }
    }


    override fun showLoading(show: Boolean) {
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

}