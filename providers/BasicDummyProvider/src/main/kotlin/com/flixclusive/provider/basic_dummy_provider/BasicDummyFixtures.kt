package com.flixclusive.provider.basic_dummy_provider

import com.flixclusive.model.media.MediaMetadata
import com.flixclusive.model.media.Movie
import com.flixclusive.model.media.PartialMedia
import com.flixclusive.model.media.Show
import com.flixclusive.model.media.common.MediaIdSource
import com.flixclusive.model.media.common.MediaType
import com.flixclusive.model.media.common.PaginatedMedia
import com.flixclusive.model.media.common.tv.Episode
import com.flixclusive.model.media.common.tv.Season
import java.util.Calendar

internal const val BIG_BUCK_BUNNY_ID = "big-buck-bunny"
internal const val ELEPHANTS_DREAM_ID = "elephants-dream"

internal fun samplePartialFilms(providerId: String): PaginatedMedia<PartialMedia> {
    val calendar = Calendar.getInstance()
    calendar.set(2006, Calendar.OCTOBER, 24)
    val date = calendar.time.time

    return PaginatedMedia(
        page = 1,
        hasNextPage = false,
        totalPages = 1,
        results = listOf(
            PartialMedia(
                id = BIG_BUCK_BUNNY_ID,
                title = "Big Buck Bunny",
                posterImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTzEygrmgAnfme0uQwjDUvIXqivYH7ipOcBV00thsqHYgma-9a5Hfcco0MslyOhe43CFUW6&s=10",
                releaseDate = date,
                providerId = providerId,
                type = MediaType.MOVIE,
                homePage = "https://peach.blender.org/",
                externalIds = buildMap {
                    put(MediaIdSource.IMDB, "tt1254207")
                    put(MediaIdSource.TMDB, "10378")
                }
            ),
            PartialMedia(
                id = ELEPHANTS_DREAM_ID,
                title = "Elephant's Dream",
                posterImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7mr8ryAr2cUJA8SncL252-fVgzRDoyuaXGoBOWXTU-q-y7EHJ2TYJGEg7eOBx8kH21SvttQ&s=10",
                releaseDate = date,
                providerId = providerId,
                type = MediaType.SHOW,
                homePage = "https://orange.blender.org/",
                externalIds = buildMap {
                    put(MediaIdSource.IMDB, "tt0807840")
                    put(MediaIdSource.TMDB, "9761")
                }
            ),
        ),
    )
}

internal fun sampleMetadata(providerId: String, media: PartialMedia): MediaMetadata {
    val calendar = Calendar.getInstance()
    calendar.set(2006, Calendar.OCTOBER, 24)
    val date = calendar.time.time

    return when (media.id) {
        BIG_BUCK_BUNNY_ID -> {
            Movie(
                id = BIG_BUCK_BUNNY_ID,
                title = "Big Buck Bunny",
                posterImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTzEygrmgAnfme0uQwjDUvIXqivYH7ipOcBV00thsqHYgma-9a5Hfcco0MslyOhe43CFUW6&s=10",
                releaseDate = date,
                providerId = providerId,
                homePage = "https://peach.blender.org/",
            )
        }

        ELEPHANTS_DREAM_ID -> {
            Show(
                id = ELEPHANTS_DREAM_ID,
                title = "Elephant's Dream",
                posterImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7mr8ryAr2cUJA8SncL252-fVgzRDoyuaXGoBOWXTU-q-y7EHJ2TYJGEg7eOBx8kH21SvttQ&s=10",
                releaseDate = date,
                providerId = providerId,
                homePage = "https://orange.blender.org/",
                totalSeasons = 1,
                totalEpisodes = 10,
                seasons = listOf(
                    Season(
                        id = "elephants-dream-s1",
                        title = "Season 1",
                        number = 1,
                        isReleased = true,
                        episodes = List(10) {
                            Episode(
                                id = "elephants-dream-s1e${it + 1}",
                                title = "Episode ${it + 1}",
                                season = 1,
                                number = it + 1,
                                releaseDate = date,
                                isReleased = true,
                            )
                        },
                    ),
                ),
            )
        }

        else -> {
            throw NoSuchElementException("No dummy metadata available for film id=${media.id}")
        }
    }
}
