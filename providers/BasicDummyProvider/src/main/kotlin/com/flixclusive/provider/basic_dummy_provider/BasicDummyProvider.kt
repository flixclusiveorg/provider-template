package com.flixclusive.provider.basic_dummy_provider

import android.content.Context
import androidx.compose.runtime.Composable
import com.flixclusive.provider.FlixclusiveProvider
import com.flixclusive.provider.Provider
import com.flixclusive.provider.ProviderApi
import okhttp3.OkHttpClient

/**
 * ## The main class for a Flixclusive provider.
 *
 * A [Provider] acts as a middleman between your [Provider]
 * and the application. It facilitates the lifecycle of your [Provider],
 * ensuring seamless integration with the application.
 *
 * #### WARN: Every provider must be annotated with [FlixclusiveProvider].
 *
 * To create a provider, extend this class and override the necessary methods.
 *
 * @see <a href="https://flixclusiveorg.github.io/provider-docs/">Documentation</a>
 */
@FlixclusiveProvider
class BasicDummyProvider : Provider() {
    @Composable
    override fun SettingsScreen() {
        // Create a custom component for code readability
        ExampleSettingsScreen(resources = resources)
    }

    override fun getApi(
        context: Context,
        client: OkHttpClient
    ): ProviderApi {
        return BasicDummyProviderApi(
            client = client,
            provider = this
        )
    }

    override fun onUnload(context: Context?) {
        super.onUnload(context)
    }
}
