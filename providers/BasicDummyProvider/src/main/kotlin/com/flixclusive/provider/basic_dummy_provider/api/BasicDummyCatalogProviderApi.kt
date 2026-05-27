package com.flixclusive.provider.basic_dummy_provider.api

import com.flixclusive.model.media.PartialMedia
import com.flixclusive.model.media.common.PaginatedMedia
import com.flixclusive.model.provider.Catalog
import com.flixclusive.provider.capability.CatalogProviderApi

class BasicDummyCatalogProviderApi(
    private val providerId: String,
    private val partialFilms: PaginatedMedia<PartialMedia>,
) : CatalogProviderApi {
    private val catalogs: List<Catalog> = listOf(
        Catalog(
            name = "Dummy Catalog",
            url = "flixclusive://dummy-catalog",
            canPaginate = false,
            providerId = providerId,
        ),
    )

    override suspend fun getCatalogs(): List<Catalog> {
        return catalogs
    }

    override suspend fun getCatalogItems(
        catalog: Catalog,
        page: Int,
    ): PaginatedMedia<PartialMedia> {
        return partialFilms.copy(page = page)
    }
}
