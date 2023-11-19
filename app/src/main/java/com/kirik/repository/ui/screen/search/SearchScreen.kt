package com.kirik.repository.ui.screen.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirik.repository.R
import com.kirik.repository.data.model.RepositoryResponse
import com.kirik.repository.ui.model.Repository


@Composable
fun SearchScreen(
    state: SearchUiState,
    viewModel: SearchViewModel
) {
    Column {
        SearchField(
            text = state.searchInput,
            hint = stringResource(id = R.string.input_repository),
            onValueChange = viewModel::changeSearchText,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        when (state) {
            is SearchUiState.NoPosts -> {
                NoItems(modifier = Modifier.fillMaxSize())
            }

            is SearchUiState.PostFounded -> {
                RepoList(repos = state.repoList,modifier = Modifier.fillMaxSize())

            }
        }
    }
}


@Composable
fun RepoList(repos: List<RepositoryResponse.Item>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(
            repos
        ) {
            RepositoryItem(it)
        }
    }
}

@Composable
fun RepositoryItem(repo: RepositoryResponse.Item, modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {

        Text(
            text = repo.name ?: "",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
        Row {
//            Image(
//                painter = painterResource(id = R.drawable.star),
//                contentDescription = stringResource(id = R.string.star)
//            )
            Text(text = repo.stargazersCount.toString())

        }
        Text(
            text = repo.description ?: "",
        )
    }

}

@Preview(showBackground = true)
@Composable
fun RepoLIstPreview(modifier: Modifier = Modifier) {
    RepositoryItem(
            RepositoryResponse.Item(
                fullName = "fullname",
                id = 1,
                name = "repository",
                description = "repository loreas ls adjhkdsahkjlds hjkafhjsak " +
                        "fjkdlsahfjkdsahkjfsdhalkjdsflhkld sakjlds" +
                        "sad lsdajkfljsad ljkfdsalkd sjkakl gjkadlf",
                watchersCount = 1,
                stargazersCount = 2
            )
        )

}

@Preview
@Composable
fun NoItems(modifier: Modifier = Modifier) {
    Text(
        textAlign = TextAlign.Center,
        text = stringResource(id = R.string.no_items),
        modifier = modifier
    )
}

@Composable
fun SearchField(
    text: String,
    hint: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        placeholder = { Text(hint) },
        modifier = modifier
    )
}

