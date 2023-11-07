package aji.dev.bniassessment.data

import aji.dev.bniassessment.data.network.ApiService
import aji.dev.bniassessment.domain.model.Data
import aji.dev.bniassessment.domain.model.Promos
import aji.dev.bniassessment.domain.pagging.PromoSource
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PromoRepository @Inject constructor(
    private val api : ApiService
) {
    fun getEverythingNews() : Flow<PagingData<Data>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 10),
            pagingSourceFactory = {
                PromoSource(api = api)
            }
        ).flow
    }
}