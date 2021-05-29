package com.android.ravn.domain.interaction.person

import com.android.ravn.domain.repository.PersonRepository
import javax.inject.Inject

class GetPeopleUseCaseImpl @Inject constructor(
    private val personRepository: PersonRepository
) : GetPeopleUseCase {
    override fun invoke() = personRepository.getPeople()
}