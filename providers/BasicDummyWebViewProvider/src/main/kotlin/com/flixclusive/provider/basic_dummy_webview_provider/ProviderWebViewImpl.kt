package com.flixclusive.provider.basic_dummy_webview_provider

import android.content.Context
import com.flixclusive.model.film.FilmMetadata
import com.flixclusive.model.film.common.tv.Episode
import com.flixclusive.model.provider.link.MediaLink
import com.flixclusive.provider.webview.ProviderWebView

class ProviderWebViewImpl(
    context: Context
) : ProviderWebView(context = context) {
    /**
     *
     * Sets whether the WebView will be headless or not.
     *
     * Headless means the WebView will not be visible to the user.
     * Non-headless means the WebView runs on the background.
     * */
    override val isHeadless: Boolean get() = false
    override val name: String get() = "My WebView Provider"

    override suspend fun getLinks(
        watchId: String,
        film: FilmMetadata,
        episode: Episode?,
        onLinkFound: (MediaLink) -> Unit
    ) = throw NotImplementedError()
}