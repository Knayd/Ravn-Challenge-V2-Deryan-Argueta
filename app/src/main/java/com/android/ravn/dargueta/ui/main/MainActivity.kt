package com.android.ravn.dargueta.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.android.ravn.dargueta.R
import com.android.ravn.dargueta.ui.list.PeopleListViewModel
import com.android.ravn.domain.model.Person
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PeopleListScreen()
        }
//        setContentView(R.layout.activity_main)
//
//        (supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment)
//            .let { navHost ->
//                val navController = navHost.navController
//                val config = AppBarConfiguration(navController.graph)
//                findViewById<Toolbar>(R.id.toolbar_main)
//                    .setupWithNavController(navController, config)
//            }
    }
}

@Composable
fun PeopleListScreen(
    modifier: Modifier = Modifier,
    viewModel: PeopleListViewModel = viewModel()
) {
    val people = viewModel.people.collectAsLazyPagingItems()
    Scaffold(modifier = modifier) {
        PeopleList(people = people)
    }
}

@Composable
fun PeopleList(modifier: Modifier = Modifier, people: LazyPagingItems<Person>) {
    LazyColumn(modifier = modifier) {
        items(people) { person ->

            val species = person?.species ?: stringResource(R.string.unknown_species)
            val planet = person?.homeWorld ?: stringResource(R.string.unknown_planet)
            val description = stringResource(R.string.person_description, species, planet)

            PersonItem(name = person?.name ?: "", description = description)
        }
    }
}

@Composable
fun PersonItem(modifier: Modifier = Modifier, name: String, description: String) {
    ConstraintLayout(modifier = modifier) {
        val (column, image) = createRefs()

        Column(
            modifier = Modifier.constrainAs(column) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
                end.linkTo(image.start)
                width = Dimension.preferredWrapContent
            }
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.h6,
                text = name
            )
            Text(
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.subtitle1,
                text = description
            )
        }
        Image(
            modifier = Modifier
                .padding(end = 24.dp)
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
