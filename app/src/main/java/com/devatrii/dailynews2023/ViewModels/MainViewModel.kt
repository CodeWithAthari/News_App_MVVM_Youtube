package com.devatrii.dailynews2023.ViewModels

import androidx.lifecycle.ViewModel
import com.devatrii.dailynews2023.Repos.MainRepository

class MainViewModel(val mainRepository: MainRepository):ViewModel() {
    var currentPage = 0
    val articleLiveData get() = mainRepository.liveData
    fun loadArticles(){
        currentPage++
        mainRepository.getArticles(currentPage.toString())
    }


}