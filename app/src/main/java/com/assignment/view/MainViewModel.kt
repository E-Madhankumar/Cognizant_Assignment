package com.assignment.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.model.Facts
import com.assignment.model.RowsItem
import com.assignment.network.Response
import com.assignment.network.Status
import com.assignment.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(val repo: Repository): ViewModel()  {

    private var posters:MutableList<Facts> = mutableListOf()

    private val posterLiveData = MutableLiveData<MutableList<RowsItem>>()
    private val errorLiveData = MutableLiveData<String>()
    private val loadingLiveData = MutableLiveData<Boolean>()

    fun getPosterObservable() = posterLiveData
    fun getErrorObserable() = errorLiveData

    fun getFacts() {

        viewModelScope.launch(Dispatchers.IO) {

            val response = repo.getFacts()
            when(response.status){
                Status.SUCCESS -> {
                    loadingLiveData.postValue(false)
                    response.result?.let{
                        posterLiveData.postValue(it?.rows as MutableList<RowsItem>?)
                    }
                }
                Status.ERROR -> {
                    errorLiveData.postValue("Couldn't able to reach server")
                }
                Status.LOADING -> {
                    loadingLiveData.postValue(true)
                }

            }
        }
    }
}