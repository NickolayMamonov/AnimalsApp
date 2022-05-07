package com.example.animalsapp.viewmodel

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalsapp.ApiInterface
import com.example.animalsapp.MainScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class RecyclerviewViewModel : ViewModel() {
    private val _dataState = MutableStateFlow<MainScreenState>(MainScreenState.Loading)
    val dataState : StateFlow<MainScreenState> = _dataState
    init{
        viewModelScope.launch {
            try {
                val apiInterface = ApiInterface.create().getAnimals("cat,dog,horse,snail",25)
                _dataState.emit(MainScreenState.Content(apiInterface))
            }catch (th: Throwable){
                _dataState.emit(MainScreenState.Error(th))
            }
        }
    }
}
