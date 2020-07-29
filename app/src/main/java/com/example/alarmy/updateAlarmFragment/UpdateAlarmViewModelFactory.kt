package com.example.alarmy.updateAlarmFragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alarmy.database.AlarmDatabaseDao

class UpdateAlarmViewModelFactory (val alarmId: Long, private val database: AlarmDatabaseDao,
                              private val application: Application
): ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdateAlarmViewModel::class.java)) {
            return UpdateAlarmViewModel(alarmId, database, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
