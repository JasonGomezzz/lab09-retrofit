package com.example.GomezJason.data.repository

import com.example.GomezJason.data.local.PostDao
import com.example.GomezJason.data.local.PostEntity
import com.example.GomezJason.data.model.PostModel
import com.example.GomezJason.data.remote.PostApiService
import kotlinx.coroutines.flow.Flow

class PostRepository(
    private val apiService: PostApiService,
    private val postDao: PostDao
) {
    suspend fun getPosts(): List<PostModel> {
        return apiService.getPosts()
    }

    suspend fun getPostById(id: Int): PostModel {
        return apiService.getPostById(id)
    }

    suspend fun saveFavorite(post: PostModel) {
        val entity = PostEntity(
            id = post.id,
            userId = post.userId,
            title = post.title,
            body = post.body
        )
        postDao.insertPost(entity)
    }

    fun getFavorites(): Flow<List<PostEntity>> {
        return postDao.getFavoritePosts()
    }

    suspend fun deleteFavorite(id: Int) {
        postDao.deletePost(id)
    }

    suspend fun isFavorite(id: Int): Boolean {
        return postDao.isFavorite(id)
    }
}