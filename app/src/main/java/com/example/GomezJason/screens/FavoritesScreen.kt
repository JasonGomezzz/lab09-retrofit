package com.example.GomezJason.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.GomezJason.viewmodel.PostViewModel

@Composable
fun FavoritesScreen(viewModel: PostViewModel) {
    val favorites by viewModel.favorites.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadFavorites()
    }

    LazyColumn {
        items(favorites) { post ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "ID: ${post.id}")
                    Text(text = post.title)
                    Text(text = post.body)
                    Button(
                        onClick = { viewModel.deleteFavorite(post.id) },
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text("Eliminar favorito")
                    }
                }
            }
        }
    }
}