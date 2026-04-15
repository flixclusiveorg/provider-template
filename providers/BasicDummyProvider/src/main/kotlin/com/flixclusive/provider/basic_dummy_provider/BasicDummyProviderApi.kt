package com.flixclusive.provider.basic_dummy_provider

import com.flixclusive.model.film.Film
import com.flixclusive.model.film.FilmMetadata
import com.flixclusive.model.film.FilmSearchItem
import com.flixclusive.model.film.Movie
import com.flixclusive.model.film.SearchResponseData
import com.flixclusive.model.film.TvShow
import com.flixclusive.model.film.common.tv.Episode
import com.flixclusive.model.film.common.tv.Season
import com.flixclusive.model.film.util.FilmType
import com.flixclusive.model.provider.ProviderCatalog
import com.flixclusive.model.provider.link.MediaLink
import com.flixclusive.model.provider.link.Stream
import com.flixclusive.model.provider.link.Subtitle
import com.flixclusive.provider.Provider
import com.flixclusive.provider.ProviderApi
import com.flixclusive.provider.filter.FilterList
import okhttp3.OkHttpClient

/**
 * An inheritance class for a [ProviderApi]. This will serve as the [Provider] api instance.
 *
 */
class BasicDummyProviderApi(
    client: OkHttpClient,
    provider: Provider
) : ProviderApi(client, provider) {
    override val baseUrl: String get() = super.baseUrl
    override val testFilm: FilmMetadata get() = super.testFilm
    override val filters: FilterList get() = super.filters
    override val catalogs: List<ProviderCatalog> get() = listOf(
        ProviderCatalog(
            name = "Dummy Catalog",
            url = "flixclusive://dummy-catalog",
            canPaginate = false,
            providerId = provider.manifest.id,
        )
    )

    private val samplePartialFilms by lazy {
        SearchResponseData(
            hasNextPage = false,
            results = listOf(
                FilmSearchItem(
                    id = "big-buck-bunny",
                    title = "Big Buck Bunny",
                    posterImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTzEygrmgAnfme0uQwjDUvIXqivYH7ipOcBV00thsqHYgma-9a5Hfcco0MslyOhe43CFUW6&s=10",
                    releaseDate = "2008",
                    providerId = provider.manifest.id,
                    filmType = FilmType.MOVIE,
                    homePage = "https://peach.blender.org/"
                ),
                FilmSearchItem(
                    id = "elephants-dream",
                    title = "Elephant's Dream",
                    posterImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7mr8ryAr2cUJA8SncL252-fVgzRDoyuaXGoBOWXTU-q-y7EHJ2TYJGEg7eOBx8kH21SvttQ&s=10",
                    releaseDate = "2006",
                    providerId = provider.manifest.id,
                    filmType = FilmType.TV_SHOW,
                    homePage = "https://orange.blender.org/"
                ),
            )
        )
    }

    override suspend fun getCatalogItems(
        catalog: ProviderCatalog,
        page: Int
    ): SearchResponseData<FilmSearchItem> {
        return samplePartialFilms
    }

    override suspend fun getMetadata(film: Film): FilmMetadata {
        when (film.id) {
            "big-buck-bunny" -> {
                return Movie(
                    id = "big-buck-bunny",
                    title = "Big Buck Bunny",
                    posterImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTzEygrmgAnfme0uQwjDUvIXqivYH7ipOcBV00thsqHYgma-9a5Hfcco0MslyOhe43CFUW6&s=10",
                    releaseDate = "2008",
                    providerId = provider.manifest.id,
                    homePage = "https://peach.blender.org/"
                )
            }
            "elephants-dream" -> {
                return TvShow(
                    id = "elephants-dream",
                    title = "Elephant's Dream",
                    posterImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7mr8ryAr2cUJA8SncL252-fVgzRDoyuaXGoBOWXTU-q-y7EHJ2TYJGEg7eOBx8kH21SvttQ&s=10",
                    releaseDate = "2006",
                    providerId = provider.manifest.id,
                    homePage = "https://orange.blender.org/",
                    totalSeasons = 1,
                    totalEpisodes = 10,
                    seasons = listOf(
                        Season(
                            number = 1,
                            episodes = List(10) {
                                Episode(
                                    id = "elephants-dream-s1e${it + 1}",
                                    title = "Episode ${it + 1}",
                                    season = 1,
                                    number = it + 1,
                                )
                            }
                        )
                    )
                )
            }
            else -> return super.getMetadata(film)
        }
    }

    override suspend fun getLinks(
        watchId: String,
        film: FilmMetadata,
        episode: Episode?,
        onLinkFound: (MediaLink) -> Unit
    ) {
        when (film.id) {
            "big-buck-bunny" -> {
                onLinkFound(
                    Stream(
                        url = "https://upload.wikimedia.org/wikipedia/commons/transcoded/c/c0/Big_Buck_Bunny_4K.webm/Big_Buck_Bunny_4K.webm.144p.mjpeg.mov",
                        name = "Big Buck Bunny Stream",
                        description = "Direct stream link for Big Buck Bunny",
                    )
                )
                onLinkFound(
                    Subtitle(
                        url = "https://commons.wikimedia.org/w/api.php?action=timedtext&title=File%3ABig_Buck_Bunny_4K.webm&lang=en&trackformat=vtt",
                        language = "Big Buck Bunny Subtitles",
                    )
                )
            }
            "elephants-dream" -> {
                onLinkFound(
                    Stream(
                        url = "https://upload.wikimedia.org/wikipedia/commons/transcoded/8/83/Elephants_Dream_%28high_quality%29.ogv/Elephants_Dream_%28high_quality%29.ogv.360p.webm",
                        name = "Elephant's Dream Stream",
                        description = "Direct stream link for Elephant's Dream",
                    )
                )
                onLinkFound(
                    Subtitle(
                        url = "https://commons.wikimedia.org/w/api.php?action=timedtext&title=File%3AElephants_Dream.ogv&lang=en&trackformat=vtt",
                        language = "Elephant's Dream Subtitles",
                    )
                )
            }
            else -> {
                super.getLinks(watchId, film, episode, onLinkFound)
            }
        }
    }

    override suspend fun search(
        title: String,
        page: Int,
        id: String?,
        imdbId: String?,
        tmdbId: Int?,
        filters: FilterList,
    ): SearchResponseData<FilmSearchItem> {
        return samplePartialFilms
    }
}