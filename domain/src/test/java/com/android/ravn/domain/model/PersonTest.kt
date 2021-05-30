package com.android.ravn.domain.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PersonTest {

    private lateinit var person: Person

    @Before
    fun setUp() {
        person = Person(
            ID,
            NAME,
            BIRTH_YEAR,
            SKIN_COLOR,
            HAIR_COLOR,
            EYE_COLOR,
            SPECIES,
            HOME_WORLD,
            listOf(VEHICLE_A, VEHICLE_B)
        )
    }

    @Test
    fun `initialization test`() {
        assertEquals(ID, person.id)
        assertEquals(NAME, person.name)
        assertEquals(BIRTH_YEAR, person.birthYear)
        assertEquals(SKIN_COLOR, person.skinColor)
        assertEquals(HAIR_COLOR, person.hairColor)
        assertEquals(EYE_COLOR, person.eyeColor)
        assertEquals(SPECIES, person.species)
        assertEquals(HOME_WORLD, person.homeWorld)
        assertEquals(listOf(VEHICLE_A, VEHICLE_B), person.vehicles)
    }

    companion object {
        private const val ID = "Id"
        private const val NAME = "Name"
        private const val BIRTH_YEAR = "Birth year"
        private const val SKIN_COLOR = "Skin color"
        private const val HAIR_COLOR = "Hair color"
        private const val EYE_COLOR = "Eye color"
        private const val SPECIES = "Species"
        private const val HOME_WORLD = "Home world"
        private const val VEHICLE_A = "Vehicle A"
        private const val VEHICLE_B = "Vehicle B"
    }
}