package com.jagakarur.nycschoolsdetails.presentation.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jagakarur.nycschoolsdetails.domain.model.School
import com.jagakarur.nycschoolsdetails.domain.model.SchoolScore
import com.jagakarur.nycschoolsdetails.domain.use_cases.UseCases
import com.jagakarur.nycschoolsdetails.util.Constants.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

//    private val _selectedSchool: MutableStateFlow<School?> = MutableStateFlow(null)
//    val selectedSchool: StateFlow<School?> = _selectedSchool

    private val _selectedSchoolScore: MutableStateFlow<List<SchoolScore>?> = MutableStateFlow(null)
    val selectedSchoolScore: StateFlow<List<SchoolScore>?> = _selectedSchoolScore

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val dbn = savedStateHandle.get<String>(DETAILS_ARGUMENT_KEY)
            _selectedSchoolScore.value = dbn?.let {
                useCases.getSelectedSchoolScoreUseCase(dbn = dbn)
            }
            // _selectedSchool.value = schoolName?.let{ Log.d("School: ",it)}
        }
    }

}

