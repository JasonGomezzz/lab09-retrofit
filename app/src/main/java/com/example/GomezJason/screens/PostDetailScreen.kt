package com.example.GomezJason.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.GomezJason.viewmodel.PostViewModel

@Composable
fun PostDetailScreen(id: Int, viewModel: PostViewModel) {
    val post by viewModel.selectedPost.collectAsState()

    LaunchedEffect(id) {
        viewModel.loadPostById(id)
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (post != null) {
            OutlinedTextField(value = post!!.id.toString(), onValueChange = {}, label = { Text("ID") }, readOnly = true)
            OutlinedTextField(value = post!!.userId.toString(), onValueChange = {}, label = { Text("User ID") }, readOnly = true)
            OutlinedTextField(value = post!!.title, onValueChange = {}, label = { Text("Título") }, readOnly = true)
            OutlinedTextField(value = post!!.body, onValueChange = {}, label = { Text("Contenido") }, readOnly = true)
            Button(
                onClick = { viewModel.saveFavorite(post!!) },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Guardar en favoritos")
            }
        } else {
            Text("Cargando publicación...")
        }
    }
}