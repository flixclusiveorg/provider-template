package com.flixclusive.provider.basic_dummy_provider.api

import com.flixclusive.model.media.MediaMetadata
import com.flixclusive.model.media.common.tv.Episode
import com.flixclusive.model.provider.link.MediaLink
import com.flixclusive.model.provider.link.Stream
import com.flixclusive.model.provider.link.Subtitle
import com.flixclusive.provider.basic_dummy_provider.BIG_BUCK_BUNNY_ID
import com.flixclusive.provider.basic_dummy_provider.ELEPHANTS_DREAM_ID
import com.flixclusive.provider.capability.MediaLinkProviderApi
import com.flixclusive.provider.capability.MediaLinkType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BasicDummyMediaLinkProviderApi : MediaLinkProviderApi {
    override val supportedLinkTypes: Set<MediaLinkType> = setOf(
        MediaLinkType.STREAMS,
        MediaLinkType.SUBTITLES,
    )
    
    override suspend fun getLinks(
        media: MediaMetadata,
        episode: Episode?,
        onLinkFound: (MediaLink) -> Unit
    ) {
        when (media.id) {
            BIG_BUCK_BUNNY_ID -> {
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
                        language = "en",
                    )
                )
            }

            ELEPHANTS_DREAM_ID -> {
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
                        language = "en",
                    )
                )
            }
        }
    }
}