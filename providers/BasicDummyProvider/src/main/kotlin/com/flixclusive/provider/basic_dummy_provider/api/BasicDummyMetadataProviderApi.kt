package com.flixclusive.provider.basic_dummy_provider.api

import com.flixclusive.model.media.Movie
import com.flixclusive.model.media.PartialMedia
import com.flixclusive.model.media.Show
import com.flixclusive.provider.basic_dummy_provider.sampleMetadata
import com.flixclusive.provider.capability.MediaMetadataProviderApi

class BasicDummyMetadataProviderApi(
    private val providerId: String,
) : MediaMetadataProviderApi {
    override suspend fun getMovie(media: PartialMedia): Movie {
        return sampleMetadata(
            providerId = providerId,
            media = media,
        ) as Movie
    }

    override suspend fun getShow(media: PartialMedia): Show {
        return sampleMetadata(
            providerId = providerId,
            media = media,
        ) as Show
    }
}
