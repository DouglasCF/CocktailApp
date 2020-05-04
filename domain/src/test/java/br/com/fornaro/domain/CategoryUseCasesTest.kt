package br.com.fornaro.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.fornaro.domain.models.response.CategoriesResponse
import br.com.fornaro.domain.models.response.CategoryResponse
import br.com.fornaro.domain.repositories.CategoryRepository
import br.com.fornaro.domain.usecases.CategoryUseCases
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CategoryUseCasesTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var useCases: CategoryUseCases

    @RelaxedMockK
    private lateinit var repository: CategoryRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)

        useCases = CategoryUseCases(
            repository
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `should load categories successfully`() = runBlockingTest {
        val categoriesResponse = provideCategoriesResponse()
        coEvery { repository.loadCategories() } returns categoriesResponse
        val categories = useCases.loadCategories()
        assert(categories[0].name == categoriesResponse.drinks[0].strCategory)
        assert(categories[1].name == categoriesResponse.drinks[1].strCategory)
        assert(categories[2].name == categoriesResponse.drinks[2].strCategory)
    }
}

fun provideCategoriesResponse() =
    CategoriesResponse(
        drinks = listOf(
            CategoryResponse("cat1"),
            CategoryResponse("cat2"),
            CategoryResponse("cat3")
        )
    )