package com.jagakarur.nycschoolsdetails.data.paging_source

import androidx.paging.PagingSource
import com.jagakarur.nycschoolsdetails.data.remote.FakeNycSchoolApi
import com.jagakarur.nycschoolsdetails.data.remote.NycSchoolApi
import com.jagakarur.nycschoolsdetails.domain.model.School
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class SearchSchoolsSourceTest {
    private lateinit var nycSchoolApi: NycSchoolApi
    private lateinit var schools: List<School>

    @Before
    fun setup() {
        nycSchoolApi = FakeNycSchoolApi()
        schools = listOf(
            School(
                dbn = "x1",
                schoolName = "School1",
                schoolEmail = "Test@testemail.com",
                graduationRate = "0.6"
            ),
            School(
                dbn = "x2",
                schoolName = "School2",
                schoolEmail = "Test2@testemail.com",
                graduationRate = "0.9"
            ),
            School(
                dbn = "x3",
                schoolName = "School3",
                schoolEmail = "Test3@testemail.com",
                graduationRate = "0.2"
            )
        )
    }

    @Test
    fun `Search api with existing school name, expect single school result, With Query string format`() =
        runTest {
            val schoolsSource = SearchSchoolsSource(nycSchoolApi = nycSchoolApi, "School1")
            assertEquals<PagingSource.LoadResult<Int, School>>(
                expected = PagingSource.LoadResult.Page(
                    data = listOf(schools.first()),
                    prevKey = null,
                    nextKey = null
                ),
                actual = schoolsSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )

        }


    @Test
    fun `Search api with existing school name, expect multiple school result, With Query string format`() =
        runTest {
            val schoolsSource = SearchSchoolsSource(nycSchoolApi = nycSchoolApi, "School")
            assertEquals<PagingSource.LoadResult<Int, School>>(
                expected = PagingSource.LoadResult.Page(
                    data = schools,
                    prevKey = null,
                    nextKey = null
                ),
                actual = schoolsSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `Search api with existing school name, expect empty school result, With Query string format`() =
        runTest {
            val schoolsSource = SearchSchoolsSource(nycSchoolApi = nycSchoolApi, "")
            val loadResult = schoolsSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )
            val result = nycSchoolApi.searchSchools("")
            assertTrue { result.isEmpty() }
            assertTrue { loadResult is PagingSource.LoadResult.Page }
        }

    @Test
    fun `Search api with non-existing school name, expect empty school result, With Query string format`() =
        runTest {
            val schoolsSource = SearchSchoolsSource(nycSchoolApi = nycSchoolApi, "Unknown")
            val loadResult = schoolsSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )
            val result = nycSchoolApi.searchSchools("Unknown")
            assertTrue { result.isEmpty() }
            assertTrue { loadResult is PagingSource.LoadResult.Page }
        }
}