package com.android.ravn.dargueta.ui

sealed class Screens(val route: String) {
    object List : Screens("list")

    object Details : Screens("details") {
        enum class NavArgs(val arg: String) {
            PERSON("person")
        }
    }
}