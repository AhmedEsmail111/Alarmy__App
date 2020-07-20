package com.example.alarmy.addAlarmFragment


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alarmy.database.AlarmDatabaseDao

class AddAlarmViewModelFactory(private val database:AlarmDatabaseDao,
                               private val application: Application):ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddAlarmViewModel::class.java)) {
            return AddAlarmViewModel(database,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}