package com.example.android_14_media_permission

import android.net.Uri

data class ImageDataModel(
    var uri: Uri,
    val imageId: Long,
    val name: String,
    val title: String,
    val path: String,
    val size: Long,
    val modified: Long,
    val dateAdded: Long,
)
