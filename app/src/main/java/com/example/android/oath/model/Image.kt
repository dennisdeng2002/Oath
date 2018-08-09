package com.example.android.oath.model

data class Image(
        val originalUrl: String,
        val originalHeight: Int,
        val originalWidth: Int,
        val resolutions: List<Resolution>
)
