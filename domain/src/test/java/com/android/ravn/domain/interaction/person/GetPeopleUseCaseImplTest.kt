package com.android.ravn.domain.interaction.person

import com.android.ravn.domain.repository.PersonRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class GetPeopleUseCaseImplTest {

    private lateinit var useCase: GetPeopleUseCaseImpl
    private lateinit var closable: AutoCloseable

    @Mock
    private lateinit var repository: PersonRepository

    @Before
    fun setUp() {
        closable = MockitoAnnotations.openMocks(this)
        useCase = GetPeopleUseCaseImpl(repository)
    }

    @Test
    fun `when use case is invoked, repository should be called`() = runBlocking<Unit> {
        useCase()
        verify(repository).getPeople()
    }

    @After
    fun tearDown() {
        closable.close()
    }
}