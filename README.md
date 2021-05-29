# Ravn-Challenge-V2-Deryan-Argueta

## Setup/Running
Just clone the repo and then build it.

## Descripction
Kotlin application that consumes [Star Wars GraphQL API](https://swapi-graphql.netlify.app/) using [Apollo client](https://www.apollographql.com/docs/android/) and [Android's Paging](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) library.
The application displays a paginated list of people and shows a detail if any of those people are tapped.

The project consists of 3 modules:
- App (UI related logic)
- Data (Implementation layer of data sources)
- Domain (Abstraction layer of data sources)

It also has Firebase/Crashlytics integration and a couple of functions to check code formatting and code smells:
./gradlew ktlintCheck
./gradlew detekt

## Technologies used
  - Hilt (Dependency Injection)
  - Android Databinding
  - Kotlin Coroutines
  - LiveData
  - Apollo
  - Paging 3
  - Firebase/Crashlytics

## Demo
![demo](https://imgur.com/a/zzKLnxB.gif)