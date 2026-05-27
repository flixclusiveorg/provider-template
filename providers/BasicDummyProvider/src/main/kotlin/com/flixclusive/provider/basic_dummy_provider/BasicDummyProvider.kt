package com.flixclusive.provider.basic_dummy_provider

import android.content.Context
import androidx.compose.runtime.Composable
import com.flixclusive.provider.FlixclusiveProvider
import com.flixclusive.provider.ProviderPlugin
import com.flixclusive.provider.basic_dummy_provider.api.BasicDummyCatalogProviderApi
import com.flixclusive.provider.basic_dummy_provider.api.BasicDummyMediaLinkProviderApi
import com.flixclusive.provider.basic_dummy_provider.api.BasicDummyMetadataProviderApi
import com.flixclusive.provider.basic_dummy_provider.api.BasicDummySearchProviderApi
import com.flixclusive.provider.capability.CatalogProviderApi
import com.flixclusive.provider.capability.MediaLinkProviderApi
import com.flixclusive.provider.capability.MediaMetadataProviderApi
import com.flixclusive.provider.capability.SearchProviderApi

/**
 * ## The main class for a Flixclusive provider.
 *
 * A [ProviderPlugin] acts as a middleman between your provider
 * and the application. It facilitates the lifecycle of your provider,
 * ensuring seamless integration with the application.
 *
 * #### WARN: Every provider must be annotated with [FlixclusiveProvider].
 *
 * To create a provider, extend this class and override the necessary methods.
 *
 * @see <a href="https://flixclusiveorg.github.io/provider-docs/">Documentation</a>
 */
@FlixclusiveProvider
class BasicDummyProvider : ProviderPlugin() {
    private val sampleFilms by lazy {
        samplePartialFilms(providerId = manifest.id)
    }

    private val catalogApi by lazy {
        BasicDummyCatalogProviderApi(
            providerId = manifest.id,
            partialFilms = sampleFilms,
        )
    }

    private val searchApi by lazy {
        BasicDummySearchProviderApi(
            partialFilms = sampleFilms,
        )
    }

    private val metadataApi by lazy {
        BasicDummyMetadataProviderApi(
            providerId = manifest.id,
        )
    }

    private val mediaLinkApi by lazy {
        BasicDummyMediaLinkProviderApi()
    }

    @Composable
    override fun SettingsScreen() {
        // Create a custom component for code readability
        ExampleSettingsScreen()
    }

    override suspend fun getCatalogApi(context: Context): CatalogProviderApi = catalogApi

    override suspend fun getSearchApi(context: Context): SearchProviderApi = searchApi

    override suspend fun getMetadataApi(context: Context): MediaMetadataProviderApi = metadataApi

    override suspend fun getMediaLinkApi(context: Context): MediaLinkProviderApi = mediaLinkApi

    override suspend fun onUnload(context: Context) {
        super.onUnload(context)
    }
}
