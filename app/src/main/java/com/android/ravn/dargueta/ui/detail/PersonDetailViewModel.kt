package com.android.ravn.dargueta.ui.detail

import androidx.lifecycle.ViewModel
import com.android.ravn.dargueta.R
import com.android.ravn.dargueta.ui.detail.adapter.TextRow
import com.android.ravn.data.util.ResourceManager
import com.android.ravn.domain.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonDetailViewModel @Inject constructor(
    private val resources: ResourceManager
) : ViewModel() {

    fun getTextRows(person: Person): List<TextRow> {
        val rows = arrayListOf<TextRow>()
        rows.addAll(getGeneralInfoRows(person))
        rows.addAll(getVehicleRows(person))
        return rows
    }

    private fun getGeneralInfoRows(person: Person): List<TextRow> {

        val infoTitle = TextRow.Title(text = resources.getString(R.string.general_info_title))

        val eyeColor = TextRow.Content(
            text1 = resources.getString(R.string.eye_color_label),
            text2 = person.eyeColor
        )
        val hairColor = TextRow.Content(
            text1 = resources.getString(R.string.hair_color_label),
            text2 = person.hairColor
        )
        val skinColor = TextRow.Content(
            text1 = resources.getString(R.string.skin_color_label),
            text2 = person.skinColor
        )
        val birthYear = TextRow.Content(
            text1 = resources.getString(R.string.birth_year_label),
            text2 = person.birthYear
        )

        return listOf(infoTitle, eyeColor, hairColor, skinColor, birthYear)
    }

    private fun getVehicleRows(person: Person): List<TextRow> {
        val vehicles = arrayListOf<TextRow>()

        val vehiclesTitle = TextRow.Title(text = resources.getString(R.string.vehicles_title))

        person.vehicles?.forEach { vehicleName ->
            vehicles.add(TextRow.Content(text1 = vehicleName))
        }

        // If person has vehicles, add the title at the start of the list
        return if (vehicles.isNullOrEmpty().not()) vehicles.apply {
            add(index = FIRST_INDEX, element = vehiclesTitle)
        } else listOf()
    }

    companion object {
        private const val FIRST_INDEX = 0
    }
}