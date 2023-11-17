package com.esa.reqresuser.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.esa.reqresuser.data.network.response.DataUser
import com.esa.reqresuser.repository.UserRepository

class MainViewModel(private val repository: UserRepository): ViewModel() {
    fun getUsers(): LiveData<PagingData<DataUser>> {
        return repository.getUsers().cachedIn(viewModelScope)
    }
}