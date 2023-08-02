package com.example.currency.ui.activity

import android.os.Bundle
import com.example.currency.R
import com.examples.core.base.activity.PermissionsActivity

class MainActivity : PermissionsActivity() {

    override var permissions: Array<String> = arrayOf(
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override var navGraphResourceId: Int = R.navigation.nav_graph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissions.launch(permissions)
    }
}
