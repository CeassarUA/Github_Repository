package com.kirik.repository.ui.screen.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kirik.repository.R

import com.kirik.repository.domain.model.Repository
import com.kirik.repository.ui.screen.LoadingScreen
import com.kirik.repository.ui.screen.search.SearchUiState

@Composable
fun DetailsRoute(
    viewModel: DetailsViewModel,
    modifier: Modifier
) {
    val uiState: DetailsUiState by viewModel.uiState.collectAsStateWithLifecycle()
    when (uiState) {
        is DetailsUiState.RepositoryState -> {
            DetailsScreen(
                repository = (uiState as DetailsUiState.RepositoryState).repository,
                modifier = modifier
            )
        }

        is DetailsUiState.LoadingState -> {
            LoadingScreen(modifier)
        }

    }
}

@Composable
fun DetailsScreen(
    repository: Repository,
    modifier: Modifier
) {


    Column(
        modifier
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(repository.image)
                .crossfade(true)
                .build(),
            contentDescription = repository.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(164.dp)
                .align(Alignment.CenterHorizontally)
                .clip(CircleShape)
        )


        Row {
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = stringResource(id = R.string.star)
            )
            Text(
                text = repository.stars.toString(),
                modifier = Modifier.padding(horizontal = 4.dp)

            )

        }

        Text(
            text = repository.name ?: "",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = repository.fullName ?: "",
            fontSize = 18.sp,
            modifier = Modifier.fillMaxWidth()
        )


        Text(
            text = repository.description ?: "",
            maxLines = 3,
            modifier = Modifier.fillMaxWidth(),
        )
    }

}

@Preview
@Composable
fun DetailsPreview() {
    DetailsScreen(
        repository = Repository(
            fullName = "fullname",
            id = 1,
            name = "repository",
            description = "repository loreas text",
            watchersCount = 1,
            stars = 2,
            image = ""
        ), Modifier
    )
}