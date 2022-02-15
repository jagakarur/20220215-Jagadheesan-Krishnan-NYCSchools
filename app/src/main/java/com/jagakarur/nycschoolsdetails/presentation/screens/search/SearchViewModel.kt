package com.jagakarur.nycschoolsdetails.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jagakarur.nycschoolsdetails.domain.model.School
import com.jagakarur.nycschoolsdetails.domain.use_cases.UseCases

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedSchools= MutableStateFlow<PagingData<School>>(PagingData.empty())
    val searchedSchools= _searchedSchools

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    //cache the data in view model for search operation
    fun searchHeroes(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.searchSchoolsUseCase(query = query).cachedIn(viewModelScope).collect {
                _searchedSchools.value = it
            }
        }
    }

}