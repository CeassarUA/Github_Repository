package com.kirik.repository.ui.screen.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kirik.repository.R
import com.kirik.repository.domain.model.Repository
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
            text = state.searchInput,
            hint = stringResource(id = R.string.input_repository),
            onValueChange = viewModel::changeSearchText,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        when (state) {
            is SearchUiState.NoPosts -> {
                StartSearch()
            }

            is SearchUiState.PostFounded -> {
                val items = viewModel.searchResult.collectAsLazyPagingItems()

                RepoList(
                    lazyPagingItems = items,
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
    lazyPagingItems: LazyPagingItems<Repository>,
    modifier: Modifier = Modifier,
    emptyState: @Composable LazyItemScope.() -> Unit,
    onRepositoryClick: (Repository) -> Unit
) {
    val listState = rememberLazyListState()
    when (lazyPagingItems.loadState.refresh) {
        is LoadState.Loading -> {
            LoadingScreen(modifier = Modifier.fillMaxWidth())
        }

        is LoadState.Error -> {
            GetError(loadState = lazyPagingItems.loadState.refresh) {
                lazyPagingItems.retry()
            }
        }

        else -> {
            LazyColumn(
                state = listState,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier.padding(horizontal = 8.dp)
            ) {
                items(
                    count = lazyPagingItems.itemCount,
                ) {
                    lazyPagingItems[it]?.let { repository ->
                        RepositoryItem(repository, {
                            onRepositoryClick(repository)
                        })
                    }
                }
                lazyPagingItems.apply {

                    when {

                        loadState.source.refresh is LoadState.NotLoading &&
                                loadState.append.endOfPaginationReached &&
                                lazyPagingItems.itemCount < 1 -> {
                            item(content = emptyState)
                        }

                        loadState.append is LoadState.Loading -> {
                            item { LoadingItem() }
                        }

                        loadState.append is LoadState.Error -> {
                            item {
                                GetError(loadState.append, ::retry)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GetError(loadState: LoadState, retry: () -> Unit) {
    val errorText = when {
        ((loadState as? LoadState.Error)?.error as? HttpException)?.code() == 403 -> {
            stringResource(id = R.string.request_limmit)
        }

        else -> {
            (loadState as? LoadState.Error)?.error?.message
                ?: stringResource(id = R.string.error)
        }
    }
    ErrorItem(
        onClick = { retry() },
        message = errorText,
        modifier = Modifier.fillMaxWidth()

    )
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
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(repo.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = repo.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .align(Alignment.CenterVertically)
                        .clip(CircleShape)
                )
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
                        Text(
                            text = repo.stars.toString(),
                            modifier = Modifier.padding(horizontal = 4.dp)

                        )

                    }
                }
            }
            Text(
                text = repo.description ?: "",
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
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
            fullName = "fullname",
            id = 1,
            name = "repository",
            description = "repository loreas text",
            watchersCount = 1,
            stars = 2,
            image = ""
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

