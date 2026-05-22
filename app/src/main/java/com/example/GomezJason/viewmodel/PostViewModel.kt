package com.example.GomezJason.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.GomezJason.data.local.PostEntity
import com.example.GomezJason.data.model.PostModel
import com.example.GomezJason.data.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel(private val repository: PostRepository) : ViewModel() {

    private val _posts = MutableStateFlow<List<PostModel>>(emptyList())
    val posts: StateFlow<List<PostModel>> = _posts

    private val _selectedPost = MutableStateFlow<PostModel?>(null)
    val selectedPost: StateFlow<PostModel?> = _selectedPost

    private val _favorites = MutableStateFlow<List<PostEntity>>(emptyList())
    val favorites: StateFlow<List<PostEntity>> = _favorites

    fun loadPosts() {
        viewModelScope.launch { _posts.value = repository.getPosts() }
    }

    fun loadPostById(id: Int) {
        viewModelScope.launch { _selectedPost.value = repository.getPostById(id) }
    }

    fun saveFavorite(post: PostModel) {
        viewModelScope.launch { repository.saveFavorite(post) }
    }

    fun loadFavorites() {
        viewModelScope.launch {
            repository.getFavorites().collect { _favorites.value = it }
        }
    }

    fun deleteFavorite(id: Int) {
        viewModelScope.launch { repository.deleteFavorite(id) }
    }
}