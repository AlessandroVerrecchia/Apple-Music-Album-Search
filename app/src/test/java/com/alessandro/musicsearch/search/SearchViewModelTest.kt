package com.alessandro.musicsearch.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alessandro.musicsearch.useCase.ISearchAlbumByArtistNameUseCase
import com.alessandro.musicsearchapi.network.model.AlbumDto
import com.alessandro.musicsearchapi.network.utils.Result
import com.alessandro.musicsearchapi.network.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var searchAlbumByArtistNameUseCase: ISearchAlbumByArtistNameUseCase

    @InjectMocks
    private lateinit var searchViewModel: SearchViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test with valid result`() = runBlocking {
        // given
        val givenAlbumDto = AlbumDto(
            "42069",
            "google.com/images/nicol%20bolas%20mtg",
            "MTG Rocks",
            "1993-08-11",
            "Nerd/Rock",
            "6.90",
            "free to use",
            "Multiverse"
        )

        // when
        Mockito.`when`(searchAlbumByArtistNameUseCase.execute("cool artist"))
            .thenReturn(Result(Status.SUCCESS, listOf(givenAlbumDto), null))
        searchViewModel.searchAlbums("cool artist")

        // then
        Assert.assertEquals(1, searchViewModel.albumsLiveData.value?.size)
        Assert.assertEquals("42069", searchViewModel.albumsLiveData.value?.first()?.id)
    }

    @Test
    fun `test with empty result`() = runBlocking {
        // when
        Mockito.`when`(searchAlbumByArtistNameUseCase.execute("cool artist"))
            .thenReturn(Result(Status.SUCCESS, listOf(), null))
        searchViewModel.searchAlbums("cool artist")

        // then
        Assert.assertEquals(0, searchViewModel.albumsLiveData.value?.size)
    }

    @Test
    fun `test with invalid result`() = runBlocking {
        // when
        Mockito.`when`(searchAlbumByArtistNameUseCase.execute("insert into albums null"))
            .thenReturn(Result(Status.ERROR, null, "you crashed apple database"))

        searchViewModel.searchAlbums("insert into albums null")

        // then
        Assert.assertEquals("you crashed apple database", searchViewModel.errorLiveData.value)
    }

}