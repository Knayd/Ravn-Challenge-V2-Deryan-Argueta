package com.android.ravn.data.di

import com.android.ravn.domain.interaction.person.GetPeopleUseCase
import com.android.ravn.domain.interaction.person.GetPeopleUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class InteractionModule {

    @Binds
    abstract fun bindGetPeopleUseCase(
        getPeopleUseCaseImpl: GetPeopleUseCaseImpl
    ): GetPeopleUseCase

}