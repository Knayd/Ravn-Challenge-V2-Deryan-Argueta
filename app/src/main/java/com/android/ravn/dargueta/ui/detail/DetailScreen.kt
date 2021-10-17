package com.android.ravn.dargueta.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.ravn.dargueta.ui.main.PeopleOfStarWarsTheme
import com.android.ravn.domain.model.Person

@Composable
fun DetailScreen(modifier: Modifier = Modifier, person: Person) {
    Text(text = person.toString())
}

@Composable
fun DetailItemHeader(modifier: Modifier = Modifier, title: String) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 32.dp,
                bottom = 8.dp
            ),
        style = MaterialTheme.typography.h6,
        text = title
    )
}

@Preview(showBackground = true)
@Composable
fun DetailItemHeaderPreview() {
    DetailItemHeader(title = "Header")
}

@Composable
fun DetailItemRow(modifier: Modifier = Modifier, text1: String, text2: String? = null) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 14.dp,
                bottom = 14.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = text1,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.secondaryVariant
        )
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