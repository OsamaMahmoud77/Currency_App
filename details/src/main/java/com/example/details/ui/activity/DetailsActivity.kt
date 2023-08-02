package com.example.details.ui.activity

import android.os.Bundle
import com.example.details.R
import com.examples.core.base.activity.BaseActivity

class DetailsActivity : BaseActivity() {
    override var navGraphResourceId: Int =
        R.navigation.details_nav_graph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}