package com.kirik.repository.ui.screen.search

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.kirik.repository.R
import com.kirik.repository.ui.screen.LoadingScreen
import retrofit2.HttpException


@Composable
fun SearchScreen(
    state: SearchUiState,
    viewModel: SearchViewModel,
    onRepositoryClick: (Repository) -> Unit
) {
    Column {
        SearchField(
            text = "",
            hint = stringResource(id = R.string.input_search),
            onValueChange = { },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        when (state) {
            is SearchUiState.NoItems -> {
                StartSearch()
            }

            is SearchUiState.ItemsFounded -> {
//                val items = viewModel.results

                RepoList(
                    list = listOf(),
                    modifier = Modifier.fillMaxSize(),
                    emptyState = {
                        NoItems(modifier = Modifier.fillMaxSize())
                    },
                    onRepositoryClick = onRepositoryClick
                )

            }

        }
    }
}

@Composable
fun StartSearch(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.startSearch),
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxSize()

    )
}


@Composable
fun RepoList(
    list: List<Repository>,
    modifier: Modifier = Modifier,
    emptyState: @Composable LazyItemScope.() -> Unit,
    onRepositoryClick: (Repository) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(items = list, itemContent = { item ->
            Log.d("COMPOSE", "This get rendered $item")
            RepositoryItem(repo = item, onItemClick = { onRepositoryClick(item) })
        })
    }

}


@Composable
fun RepositoryItem(
    repo: Repository,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onItemClick
            )
    ) {

        Column(
            modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth()
        ) {
            Row {

                Column(
                    modifier
                        .padding(horizontal = 8.dp)
                        .align(Alignment.CenterVertically)

                ) {


                    Text(
                        text = repo.name ?: "",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = stringResource(id = R.string.star)
                        )


                    }
                }
            }
        }
    }

}

@Composable
fun LoadingItem() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Preview(showBackground = true)
@Composable
fun ErrorItemPreview() {
    ErrorItem(message = "Error text", modifier = Modifier.fillMaxWidth())
}

@Composable
fun ErrorItem(
    message: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            maxLines = 1,
            color = MaterialTheme.colorScheme.error
        )
        OutlinedButton(onClick = onClick) {
            Text(text = stringResource(id = R.string.try_again))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RepoListPreview(modifier: Modifier = Modifier) {
    RepositoryItem(
        Repository(
            name = "repository",
        ), onItemClick = {}
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

