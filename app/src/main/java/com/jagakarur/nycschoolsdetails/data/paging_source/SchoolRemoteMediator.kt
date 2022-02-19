package com.jagakarur.nycschoolsdetails.data.paging_source

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.jagakarur.nycschoolsdetails.data.local.SchoolDatabase
import com.jagakarur.nycschoolsdetails.data.remote.NycSchoolApi
import com.jagakarur.nycschoolsdetails.domain.model.School
import com.jagakarur.nycschoolsdetails.domain.model.SchoolRemoteKey
import com.jagakarur.nycschoolsdetails.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.delay
import java.io.InvalidObjectException
import javax.inject.Inject

/**
 * Make sure to have the same sort from DB as it is from the backend side, otherwise items get mixed up and prevKey
 * and nextKey are no longer valid (the scroll might get stuck or the load might loop one of the pages)
 */
@ExperimentalPagingApi
class SchoolRemoteMediator @Inject constructor(
    private val nycSchoolApi: NycSchoolApi,
    private val schoolDatabase: SchoolDatabase
) : RemoteMediator<Int, School>() {
    private val initialPage: Int = 1
    private val schoolDao = schoolDatabase.schoolDao()
    private val schoolRemoteKeyDao = schoolDatabase.schoolRemoteKeyDAO()

    override suspend fun initialize(): InitializeAction {
        // Require that remote REFRESH is launched on initial load and succeeds before launching
        // remote PREPEND / APPEND.
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, School>
    ): MediatorResult {
        return try {
            delay(1000)

            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: initialPage
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(true)
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                        ?: throw InvalidObjectException("Result is empty")
                    remoteKeys.nextPage ?: return MediatorResult.Success(true)
                }
            }
            val offset = (if (page != 0) (page * 100) else 0)

            val response = nycSchoolApi.getAllSchools(offset = offset, limit = ITEMS_PER_PAGE)
                .sortedBy { it.schoolName }
            val endOfPaginationReached = response.size < state.config.pageSize
            if (response.isNotEmpty()) {
                schoolDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        schoolDao.deleteAllSchools()
                        schoolRemoteKeyDao.deleteAllRemoteKeys()
                    }
                    Log.v("response.size", response.size.toString())
                    val prevKey = if (page == initialPage) null else page - 1
                    val nextKey = if (endOfPaginationReached) null else page + 1
                    Log.v("page ", page.toString())
                    Log.v("nextPage ", nextKey.toString())
                    val keys = response.map { school ->
                        SchoolRemoteKey(
                            dbn = school.dbn,
                            prevPage = prevKey,
                            nextPage = nextKey
                        )
                    }
                    schoolRemoteKeyDao.addAllRemoteKeys(schoolRemoteKey = keys)
                    schoolDao.addSchools(schools = response)
                }

            }
            Log.v("response.size ", response.size.toString())
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, School>
    ): SchoolRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.dbn?.let { dbn ->
                schoolDatabase.withTransaction { schoolRemoteKeyDao.remoteKeysByDbn(dbn = dbn) }
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, School>
    ): SchoolRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { school ->
                schoolDatabase.withTransaction { schoolRemoteKeyDao.remoteKeysByDbn(dbn = school.dbn) }
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, School>
    ): SchoolRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { school ->
                schoolDatabase.withTransaction { schoolRemoteKeyDao.remoteKeysByDbn(dbn = school.dbn) }
            }
    }


}