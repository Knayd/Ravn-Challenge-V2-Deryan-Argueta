package com.android.ravn.dargueta.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.android.ravn.domain.interaction.person.GetPeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeopleListViewModel @Inject constructor(
    getPeopleUseCase: GetPeopleUseCase
) : ViewModel() {

    val people = getPeopleUseCase().cachedIn(viewModelScope).asLiveData()
}