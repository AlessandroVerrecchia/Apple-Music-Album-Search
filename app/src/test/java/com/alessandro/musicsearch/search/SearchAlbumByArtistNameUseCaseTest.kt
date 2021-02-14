package com.alessandro.musicsearch.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alessandro.musicsearch.useCase.SearchAlbumByArtistNameUseCase
import com.alessandro.musicsearchapi.network.IMusicSearchService
import com.alessandro.musicsearchapi.network.model.AlbumContainer
import com.alessandro.musicsearchapi.network.model.AlbumDto
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
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import retrofit2.Response


@ExperimentalCoroutinesApi
class SearchAlbumByArtistNameUseCaseTest {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var musicSearchService: IMusicSearchService

    @InjectMocks
    private lateinit var searchAlbumByArtistNameUseCase: SearchAlbumByArtistNameUseCase

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
    fun `search albums by artist name use case test`() = runBlocking {
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

        val givenAlbumContainer = AlbumContainer(
            1, listOf(givenAlbumDto)
        )

        val givenSuccessResponse = Response.success(givenAlbumContainer)

        `when`(
            musicSearchService.searchAlbumByArtistName(
                "cool artist",
                "music",
                "album",
                "artistTerm",
                "CA"
            )
        ).thenReturn(givenSuccessResponse)

        val result = searchAlbumByArtistNameUseCase.execute("cool artist")

        Assert.assertTrue(result.message == null)
        Assert.assertTrue(result.status == Status.SUCCESS)
        Assert.assertTrue(result.data == listOf(givenAlbumDto))
    }

    @Test
    fun `search albums by artist name use case test with empty body`() = runBlocking {
        val givenAlbumContainer = AlbumContainer(
            0, listOf()
        )

        val givenSuccessResponse = Response.success(givenAlbumContainer)

        `when`(
            musicSearchService.searchAlbumByArtistName(
                "underground artist",
                "music",
                "album",
                "artistTerm",
                "CA"
            )
        ).thenReturn(givenSuccessResponse)

        val result = searchAlbumByArtistNameUseCase.execute("underground artist")

        Assert.assertTrue(result.message == null)
        Assert.assertTrue(result.status == Status.SUCCESS)
        Assert.assertEquals(result.data?.size, 0)
    }


}
