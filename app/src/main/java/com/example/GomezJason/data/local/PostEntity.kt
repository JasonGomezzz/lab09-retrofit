package com.example.GomezJason.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "favorite_posts")
data class PostEntity(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)