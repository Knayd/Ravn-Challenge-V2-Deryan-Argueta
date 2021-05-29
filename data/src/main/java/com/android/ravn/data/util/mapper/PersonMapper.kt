package com.android.ravn.data.util.mapper

import PeopleListQuery
import com.android.ravn.data.util.mapper.base.DomainMapper
import com.android.ravn.domain.model.Person

class PersonMapper(
    private val person: PeopleListQuery.Person
) : DomainMapper<Person> {

    override fun toDomainModel() = Person(
        id = person.id,
        name = person.name,
        species = person.species?.name,
        hairColor = person.hairColor,
        eyeColor = person.eyeColor,
        birthYear = person.birthYear,
        skinColor = person.skinColor,
        homeWorld = person.homeworld?.name,
        vehicles = person.vehicleConnection?.vehicles?.mapNotNull { it?.name },
    )
}
