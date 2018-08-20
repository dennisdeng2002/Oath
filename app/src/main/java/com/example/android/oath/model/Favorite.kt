package com.example.android.oath.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Favorite(
        @PrimaryKey val id: String,
        val title: String,
        val summary: String
)
