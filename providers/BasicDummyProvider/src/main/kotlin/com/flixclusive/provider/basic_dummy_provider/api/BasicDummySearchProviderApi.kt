package com.flixclusive.provider.basic_dummy_provider.api

import com.flixclusive.model.media.PartialMedia
import com.flixclusive.model.media.common.PaginatedMedia
import com.flixclusive.provider.capability.SearchProviderApi
import com.flixclusive.provider.filter.FilterList

class BasicDummySearchProviderApi(
    private val partialFilms: PaginatedMedia<PartialMedia>
) : SearchProviderApi {
    override val filters: FilterList = FilterList()

    override suspend fun search(
        query: String,
        page: Int,
        filters: FilterList,
    ): PaginatedMedia<PartialMedia> {
        val normalizedTitle = query.trim()
        val filteredResults = if (normalizedTitle.isBlank()) {
            partialFilms.results
        } else {
            partialFilms.results.filter {
                it.title.contains(normalizedTitle, ignoreCase = true)
            }
        }

        return partialFilms.copy(
            page = page,
            results = filteredResults,
        )
    }
}
