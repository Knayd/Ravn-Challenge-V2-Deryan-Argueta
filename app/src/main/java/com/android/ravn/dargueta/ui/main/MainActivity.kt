package com.android.ravn.dargueta.ui.main

import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.android.ravn.dargueta.R
import com.android.ravn.dargueta.ui.detail.DetailScreen
import com.android.ravn.dargueta.ui.list.PeopleListViewModel
import com.android.ravn.dargueta.ui.navparams.PersonParamType
import com.android.ravn.domain.model.Person
import com.google.gson.Gson
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
        var title by remember { mutableStateOf("") }
        var canPop by remember { mutableStateOf(false) }

        navController.addOnDestinationChangedListener { controller, _, _ ->
            canPop = controller.previousBackStackEntry != null
        }

        Scaffold(
            topBar = {
                PeopleOfStarWarsTopAppBar(
                    navigationIconVisible = canPop,
                    onNavigationIconClick = { navController.popBackStack() },
                    title = title
                )
            },
        ) {
            PeopleOfStarWarsNavHost(
                navController = navController,
                onSetTitle = { title = it }
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PeopleOfStarWarsTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onNavigationIconClick: () -> Unit,
    navigationIconVisible: Boolean
) {
    TopAppBar(modifier = modifier) {
        Row {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                AnimatedVisibility(visible = navigationIconVisible) {
                    IconButton(onClick = onNavigationIconClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 16.dp),
                    style = MaterialTheme.typography.h6,
                    text = title
                )
            }
        }
    }
}

@Composable
fun PeopleOfStarWarsNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onSetTitle: (title: String) -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            PeopleListScreen(
                onSetTitle = onSetTitle,
                onPersonCLick = { person ->
                    val personParam = Uri.encode(Gson().toJson(person))
                    navController.navigate("details/$personParam")
                }
            )
        }
        composable(route = "details/{person}", arguments = listOf(
            navArgument("person") {
                type = PersonParamType()
            }
        )) { stackEntry ->
            stackEntry.arguments?.getParcelable<Person>("person")?.let { person ->
                DetailScreen(
                    onSetTitle = onSetTitle,
                    person = person
                )
            }
        }
    }
}

@Composable
fun PeopleListScreen(
    modifier: Modifier = Modifier,
    viewModel: PeopleListViewModel = hiltViewModel(),
    onPersonCLick: (person: Person) -> Unit = {},
    onSetTitle: (title: String) -> Unit = {}
) {
    onSetTitle(stringResource(id = R.string.app_name))
    val people = viewModel.people.collectAsLazyPagingItems()
    PeopleList(
        modifier = modifier,
        people = people,
        onPersonCLick = onPersonCLick
    )
}

@Composable
fun PeopleList(
    modifier: Modifier = Modifier,
    people: LazyPagingItems<Person>,
    onPersonCLick: (person: Person) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(people) { person ->
            person?.let {
                PersonItem(
                    person = person,
                    onCLick = onPersonCLick
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

@Composable
fun PersonItem(
    modifier: Modifier = Modifier,
    person: Person,
    onCLick: (person: Person) -> Unit = {}
) {
    ConstraintLayout(
        modifier = modifier.clickable { onCLick(person) }
    ) {
        val (column, image) = createRefs()

        val species = person.species ?: stringResource(R.string.unknown_species)
        val planet = person.homeWorld ?: stringResource(R.string.unknown_planet)
        val description = stringResource(R.string.person_description, species, planet)

        Column(
            modifier = Modifier
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
                text = person.name ?: ""
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
    PersonItem(
        Modifier, Person(
            null, "Luke Skywalker", null, null, null, null, "Human", "Tatooine", null
        )
    )
}
