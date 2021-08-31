package ru.samitin.allmovies.viewmodel

import android.content.res.Resources
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.samitin.allmovies.model.AppState
import ru.samitin.allmovies.model.repository.Repository
import ru.samitin.allmovies.model.repository.RepositoryImpl

class MainViewModel @RequiresApi(Build.VERSION_CODES.N) constructor(private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
                                                                    private val repositoryImpl: Repository = RepositoryImpl()) : ViewModel() {

        fun getLifeData()=liveDataToObserve;
    fun getWeatherFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread (
            Runnable {
                Thread.sleep(1000)
                val categoryes = repositoryImpl.getMoviesFromLocalStorage()
                liveDataToObserve.postValue(AppState.Success(categoryes))
            }).start()

    }

    fun getWeatherFromRemoteSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            Thread.sleep(1000)
            liveDataToObserve.postValue(AppState.Success(repositoryImpl.getMoviesFromServer()))
        }.start()
    }

}