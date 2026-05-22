package com.example.GomezJason.data.remote

import com.example.GomezJason.data.model.PostModel
import retrofit2.http.GET
import retrofit2.http.Path
interface PostApiService {
    @GET("posts")
    suspend fun getPosts(): List<PostModel>
    @GET("posts/{id}")
    suspend fun getPostById(
        @Path("id") id: Int
    ): PostModel
}