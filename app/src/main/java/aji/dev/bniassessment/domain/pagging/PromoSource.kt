package aji.dev.bniassessment.domain.pagging

import aji.dev.bniassessment.data.network.ApiService
import aji.dev.bniassessment.domain.model.Data
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

class PromoSource(
    private val api: ApiService,
): PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val nextPage = params.key ?: 1
            val promos =
                api.getPromos()
            LoadResult.Page(
                data = promos.data,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (promos.data.size < params.loadSize) null else nextPage + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}