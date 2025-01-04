package com.flixclusive.provider.basic_dummy_webview_provider

import android.content.Context
import com.flixclusive.model.film.Film
import com.flixclusive.model.film.FilmDetails
import com.flixclusive.model.film.FilmMetadata
import com.flixclusive.model.film.FilmSearchItem
import com.flixclusive.model.film.SearchResponseData
import com.flixclusive.model.film.common.tv.Episode
import com.flixclusive.model.provider.ProviderCatalog
import com.flixclusive.model.provider.link.MediaLink
import com.flixclusive.provider.Provider
import com.flixclusive.provider.ProviderApi
import com.flixclusive.provider.ProviderWebViewApi
import com.flixclusive.provider.filter.FilterList
import com.flixclusive.provider.webview.ProviderWebView
import okhttp3.OkHttpClient

/**
 * An inheritance class for a [ProviderApi]. This will serve as the [Provider] api instance.
 *
 */
class BasicDummyWebViewProviderApi(
    client: OkHttpClient,
    context: Context,
    provider: Provider
) : ProviderWebViewApi(
    client = client,
    provider = provider,
    context = context
) {
    override val baseUrl: String get() = super.baseUrl
    override val testFilm: FilmMetadata get() = super.testFilm
    override val catalogs: List<ProviderCatalog> get() = super.catalogs
    override val filters: FilterList get() = super.filters

    override suspend fun getCatalogItems(
        catalog: ProviderCatalog,
        page: Int
    ): SearchResponseData<FilmSearchItem> = super.getCatalogItems(catalog, page)


    override suspend fun getMetadata(film: Film): FilmMetadata = super.getMetadata(film)

    override suspend fun getLinks(
        watchId: String,
        film: FilmMetadata,
        episode: Episode?,
        onLinkFound: (MediaLink) -> Unit
    ) = super.getLinks(watchId, film, episode, onLinkFound)

    override suspend fun search(
        title: String,
        page: Int,
        id: String?,
        imdbId: String?,
        tmdbId: Int?,
        filters: FilterList,
    ): SearchResponseData<FilmSearchItem> = super.search(title, page, id, imdbId, tmdbId, filters)

    /**
     *
     * The key highlight of this API.
     *
     * This should return a [ProviderWebView] instance.
     * */
    override fun getWebView(): ProviderWebView {
        return ProviderWebViewImpl(context = context)
    }
}