package com.flixclusive.provider.basic_dummy_provider

import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import org.junit.Test

/**
 * ## Template unit test for your providers.
 *
 * Follow the Arrange-Act-Assert pattern for each test method.
 *
 */
class BasicDummyProviderApiUnitTest {

    private val provider = BasicDummyProvider()

    /**
     * Tests the behavior of `getFilmInfo` method for a specific film ID.
     * To run suspend functions inside a test, you could use [runTest].
     *
     * Instructions:
     * - Arrange: Create an instance of MyFirstProvider with necessary dependencies.
     * - Act: Call `getFilmInfo` method with a specific film ID and film type.
     * - Assert: Verify that the returned film title is not blank.
     *
     * @see runTest
     */
    @Test
    fun `test getFilmInfo for some_film_id returns non-empty film title`()
        = runTest {
        // Arrange
        val api = BasicDummyProviderApi(OkHttpClient(), provider)

        // Act
        val result = api.getMetadata(film = api.testFilm)

        // Assert
        assert(result.title.isNotBlank())
    }
}
