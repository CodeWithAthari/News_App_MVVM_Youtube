package com.devatrii.dailynews2023.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devatrii.dailynews2023.Repos.MainRepository

class MainViewModelFactory(val mainRepository: MainRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(mainRepository) as T
    }
}