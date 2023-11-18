package com.kirik.repository.ui.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview(showBackground = true)
fun SearchScreen(
    state: SearchUiState,
    viewModel: SearchViewModel
) {
    Column {
        SearchField(text = state.searchInput, onValueChange = viewModel::changeSearchText)

    }

}

@Composable
fun SearchField(text: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier
    )
}