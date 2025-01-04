import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.challenge.data.repository.ISportRepository
import com.example.challenge.domain.model.SportModel
import com.example.challenge.ui.screens.MainScreen
import com.example.challenge.ui.viewmodel.MainViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockRepository: ISportRepository = mockk(relaxed = true)
    private val viewModel = MainViewModel(mockRepository)

    @Test
    fun verifyIfFilterButtonIsWorkingProperly() {
        val filterDescription = "Filter"
        val mockSports = listOf(
            SportModel(id = "1", name = "Soccer", isFavorite = true),
            SportModel(id = "2", name = "Badminton", isFavorite = true),
            SportModel(id = "3", name = "Volleyball", isFavorite = false),
            SportModel(id = "4", name = "Tennis", isFavorite = true),
            SportModel(id = "5", name = "Basketball", isFavorite = false)
        )

        val expectedFavoriteSize = 3
        val expectedFullSize = 5

        coEvery { mockRepository.getAllSports() } returns flowOf(mockSports)
        coEvery { mockRepository.getFavoriteSportsSections() } returns flowOf(mockSports.filter { it.isFavorite })

        composeTestRule.setContent {
            MaterialTheme {
                MainScreen(navController = mockk(relaxed = true), viewModel = viewModel)
            }
        }
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithContentDescription(filterDescription).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(filterDescription).performClick()
        composeTestRule.waitForIdle()

        assert(viewModel.isFilterActive.value)
        val favoriteItems = viewModel.filteredData.value
        assert(favoriteItems.size == expectedFavoriteSize)


        composeTestRule.onNodeWithContentDescription(filterDescription).performClick()
        composeTestRule.waitForIdle()

        assert(!viewModel.isFilterActive.value)
        val allItems = viewModel.filteredData.value
        assert(allItems.size == expectedFullSize)
    }
}
