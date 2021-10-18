package com.android.ravn.dargueta.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.ravn.dargueta.R
import com.android.ravn.dargueta.ui.detail.adapter.TextRow
import com.android.ravn.dargueta.ui.main.PeopleOfStarWarsTheme
import com.android.ravn.domain.model.Person

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    person: Person,
    viewModel: PersonDetailViewModel = hiltViewModel()
) {
    LazyColumn(modifier = modifier) {
        items(viewModel.getTextRows(person)) { row ->
            when (row) {
                is TextRow.Title -> DetailItemHeader(title = row.text)
                is TextRow.Content -> {
                    DetailItemRow(
                        text1 = row.text1,
                        text2 = row.text2
                    )
                    Divider(
                        modifier = Modifier.padding(
                            start = dimensionResource(R.dimen.item_text_margin)
                        ),
                        color = MaterialTheme.colors.primary,
                        thickness = dimensionResource(R.dimen.item_divider_thickness)
                    )
                }
            }
        }
    }
}

@Composable
fun DetailItemHeader(modifier: Modifier = Modifier, title: String) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 32.dp,
                bottom = 8.dp,
                start = dimensionResource(R.dimen.item_text_margin)
            ),
        style = MaterialTheme.typography.h6,
        text = title
    )
}

@Composable
fun DetailItemRow(modifier: Modifier = Modifier, text1: String?, text2: String? = null) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 14.dp,
                bottom = 14.dp,
                start = dimensionResource(R.dimen.item_text_margin),
                end = dimensionResource(R.dimen.item_text_margin)
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        text1?.let {
            Text(
                modifier = Modifier.weight(1f),
                text = text1,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.secondaryVariant
            )
        }
        text2?.let {
            Text(
                textAlign = TextAlign.End,
                text = text2,
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailItemHeaderPreview() {
    DetailItemHeader(title = "Header")
}

@Preview(showBackground = true)
@Composable
fun DetailItemRowPreview() {
    PeopleOfStarWarsTheme {
        DetailItemRow(text1 = "Eye Color", text2 = "Blue")
    }
}

@Preview(showBackground = true)
@Composable
fun DetailItemRowSingleTextPreview() {
    PeopleOfStarWarsTheme {
        DetailItemRow(text1 = "Snowspeeder")
    }
}