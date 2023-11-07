package aji.dev.bniassessment.presentation.qris

import aji.dev.bniassessment.domain.MainRepo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QrisViewModel @Inject constructor(
    private val repo: MainRepo
):ViewModel() {

    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()


    fun startScanning(){
        viewModelScope.launch {
            repo.startScanning().collect{
                if (!it.isNullOrBlank()){
                    _state.value = state.value.copy(
                        details = it
                    )
                }
            }
        }
    }
}

data class MainScreenState(
    val details:String = "Start scanning to get details"
)