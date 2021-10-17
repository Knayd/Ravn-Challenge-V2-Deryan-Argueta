package com.android.ravn.dargueta.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.android.ravn.dargueta.R
import com.android.ravn.dargueta.ui.detail.DetailScreen
import com.android.ravn.dargueta.ui.list.PeopleListViewModel
import com.android.ravn.domain.model.Person
import dagger.hilt.android.AndroidEntryPoint

//TODO: Move dp values into a separate file and fix colors

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PeopleOfStarWarsApp()
        }
    }
}

@Composable
fun PeopleOfStarWarsApp() {
    PeopleOfStarWarsTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "list") {
            composable("list") {
                PeopleListScreen(
                    onPersonCLick = { navController.navigate("details") }
                )
            }
            composable("details") {
                DetailScreen()
            }
        }
    }
}

@Composable
fun PeopleListScreen(
    modifier: Modifier = Modifier,
    viewModel: PeopleListViewModel = hiltViewModel(),
    onPersonCLick: () -> Unit = {}
) {
    val people = viewModel.people.collectAsLazyPagingItems()
    Scaffold(modifier = modifier) {
        PeopleList(
            people = people,
            onPersonCLick = onPersonCLick
        )
    }
}

@Composable
fun PeopleList(
    modifier: Modifier = Modifier,
    people: LazyPagingItems<Person>,
    onPersonCLick: () -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(people) { person ->

            val species = person?.species ?: stringResource(R.string.unknown_species)
            val planet = person?.homeWorld ?: stringResource(R.string.unknown_planet)
            val description = stringResource(R.string.person_description, species, planet)

            PersonItem(
                name = person?.name ?: "",
                description = description,
                onCLick = onPersonCLick
            )
            Divider(
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.item_text_margin),
                    end = dimensionResource(R.dimen.item_text_margin)
                ),
                color = MaterialTheme.colors.primary,
                thickness = dimensionResource(R.dimen.item_divider_thickness)
            )
        }
    }
}

@Composable
fun PersonItem(
    modifier: Modifier = Modifier,
    name: String,
    description: String,
    onCLick: () -> Unit = {}
) {
    ConstraintLayout(modifier = modifier) {
        val (column, image) = createRefs()

        Column(
            modifier = Modifier
                .clickable { onCLick.invoke() }
                .constrainAs(column) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(image.start)
                    width = Dimension.preferredWrapContent
                }
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        start = dimensionResource(R.dimen.item_text_margin),
                        top = dimensionResource(R.dimen.item_text_margin),
                        end = dimensionResource(R.dimen.item_text_margin)
                    )
                    .fillMaxWidth(),
                style = MaterialTheme.typography.h6,
                text = name
            )
            Text(
                modifier = Modifier
                    .padding(
                        start = dimensionResource(R.dimen.item_text_margin),
                        bottom = dimensionResource(R.dimen.item_text_margin),
                        end = dimensionResource(R.dimen.item_text_margin)
                    )
                    .fillMaxWidth(),
                style = MaterialTheme.typography.subtitle1,
                text = description
            )
        }
        Image(
            modifier = Modifier
                .padding(end = dimensionResource(R.dimen.item_arrow_margin))
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(column.end)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
            painter = painterResource(
                id = R.drawable.ic_arrow_right
            ),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PersonItemPreview() {
    PersonItem(Modifier, "Luke Skywalker", "Unknown species from Tatooine")
}
