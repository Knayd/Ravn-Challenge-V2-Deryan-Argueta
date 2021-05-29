package com.android.ravn.dargueta.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.android.ravn.domain.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeopleListViewModel @Inject constructor(
    private val personRepository: PersonRepository
) : ViewModel() {

    val people = personRepository.getPeople().asLiveData()

}