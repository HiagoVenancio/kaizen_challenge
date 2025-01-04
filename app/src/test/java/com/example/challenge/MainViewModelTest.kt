package com.example.challenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.challenge.data.repository.ISportRepository
import com.example.challenge.domain.model.SportModel
import com.example.challenge.ui.viewmodel.MainViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: ISportRepository
    private lateinit var viewModel: MainViewModel

    private val mockSports = listOf(
        SportModel(id = "1", name = "Soccer", isFavorite = true),
        SportModel(id = "2", name = "Basketball", isFavorite = false),
        SportModel(id = "3", name = "BaseBall", isFavorite = false)
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        repository = mockk(relaxed = true)
        viewModel = MainViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get all sports when filter is inactive`() = runTest {
        val expectedSize = 3
        coEvery { repository.getAllSports() } returns flowOf(mockSports)

        viewModel.filteredData.take(1).collect { result ->
            assertEquals(mockSports, result)
            assertEquals(result.size, expectedSize)
        }
    }

    @Test
    fun `get only favorite sports when filter is active`() = runTest {
        val expectedSize = 1
        val favoriteSports = mockSports.filter { it.isFavorite }
        coEvery { repository.getFavoriteSportsSections() } returns flowOf(favoriteSports)

        viewModel.toggleFilter()

        viewModel.filteredData.take(1).collect { result ->
            assertEquals(favoriteSports, result)
            assertEquals(result.size, expectedSize)
        }
    }
}