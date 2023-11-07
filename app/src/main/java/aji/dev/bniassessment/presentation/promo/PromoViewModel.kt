package aji.dev.bniassessment.presentation.promo

import aji.dev.bniassessment.data.PromoRepository
import aji.dev.bniassessment.domain.MainRepo
import aji.dev.bniassessment.domain.model.Data
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PromoViewModel @Inject constructor(
    private val repository: PromoRepository,
    private val repo: MainRepo
) : ViewModel() {
    private var _promo = mutableStateOf<Flow<PagingData<Data>>>(emptyFlow())
    val promo: State<Flow<PagingData<Data>>> = _promo
    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()


    init {
        getEverything()
    }

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
    private fun getEverything() {
        viewModelScope.launch {
            repository.getEverythingNews()
                .also {
                    _promo.value = it
                }.cachedIn(viewModelScope)
        }
    }

}

data class MainScreenState(
    val details:String = "Start scanning to get details"
)