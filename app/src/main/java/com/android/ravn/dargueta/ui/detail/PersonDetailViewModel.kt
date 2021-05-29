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

    fun createTextRows(person: Person): List<TextRow> {

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

        val rows = arrayListOf(
            infoTitle,
            eyeColor,
            hairColor,
            skinColor,
            birthYear
        )

        val vehiclesTitle = TextRow.Title(text = resources.getString(R.string.vehicles_title))
        val vehicles = arrayListOf<TextRow>()

        person.vehicles?.forEach { vehicleName ->
            vehicles.add(TextRow.Content(text1 = vehicleName))
        }

        if (vehicles.isNullOrEmpty().not()) {
            rows.add(vehiclesTitle)
            rows.addAll(vehicles)
        }

        return rows
    }
}