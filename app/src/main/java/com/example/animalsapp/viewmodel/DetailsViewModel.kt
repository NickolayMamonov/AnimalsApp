package com.example.animalsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalsapp.ApiInterface
import com.example.animalsapp.DetailsScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class DetailsViewModel : ViewModel() {
    private val _dataState = MutableStateFlow<DetailsScreenState>(DetailsScreenState.Loading)
    val dataState : StateFlow<DetailsScreenState> = _dataState

    fun getDetails(id: String?){
        viewModelScope.launch {
            try {
                val apiInterface = ApiInterface.create().getFactsDetails(id)
                _dataState.emit(DetailsScreenState.Content(apiInterface))
            }catch (th: Throwable){
                _dataState.emit(DetailsScreenState.Error(th))
            }
        }
    }

}