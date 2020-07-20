package com.example.alarmy.alarmFragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alarmy.database.AlarmDatabaseDao

class AlarmsViewModelFactory (private val database: AlarmDatabaseDao,
                               private val application: Application
): ViewModelProvider.Factory{
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlarmsViewModel::class.java)) {
                return AlarmsViewModel(database,application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
