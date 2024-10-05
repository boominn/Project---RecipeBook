package com.example.project_mealsbook.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.project_mealsbook.model.Besin
import com.example.project_mealsbook.roomdb.BesinDatabase
import kotlinx.coroutines.launch

class BesinDetayViewModel(application: Application): AndroidViewModel(application) {

    val besinLiveData = MutableLiveData<Besin>()

    fun roomVerisiniAl(uuid: Int) {
        viewModelScope.launch {
            val dao = BesinDatabase(getApplication()).besinDao()
            val besin = dao.getBesin(uuid)
            besinLiveData.value = besin
        }
    }
}