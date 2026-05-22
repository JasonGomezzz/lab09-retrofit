package com.example.GomezJason.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: PostEntity)
    @Query("SELECT * FROM favorite_posts")
    fun getFavoritePosts(): Flow<List<PostEntity>>
    @Query("DELETE FROM favorite_posts WHERE id = :id")
    suspend fun deletePost(id: Int)
    @Query("SELECT EXISTS(SELECT 1 FROM favorite_posts WHERE id = :id)")
    suspend fun isFavorite(id: Int): Boolean
}