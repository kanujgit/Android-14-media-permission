package com.example.android_14_media_permission

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var imageDataModelList = listOf<ImageDataModel>()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>


    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Initialize RecyclerView and adapter
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val imageAdapter = ImageAdapter(this)
        recyclerView.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 3)
        }


        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            permissions.entries.forEach { entry ->
                val permission = entry.key
                val isGranted = entry.value
                Log.d(TAG, "Permission: $permission Status: $isGranted")
            }

            if (permissions.values.any { it }) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    queryContentResolver(this) {
                        imageDataModelList = it
                        imageAdapter.setImages(imageDataModelList)

                        Log.d(TAG, it.toString())
                    }
                }
            }
        }

        findViewById<Button>(R.id.tv_btn).setOnClickListener {
            allowAccess()
        }
    }


    private fun allowAccess() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (Build.VERSION.SDK_INT >= 34) {
                permissionLauncher.launch(
                    arrayOf(
                        android.Manifest.permission.READ_MEDIA_IMAGES,
                        android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
                    )
                )
            }
        }
    }


}