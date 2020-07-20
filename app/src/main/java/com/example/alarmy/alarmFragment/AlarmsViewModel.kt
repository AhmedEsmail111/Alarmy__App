package com.example.alarmy.alarmFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.alarmy.database.AlarmDatabaseDao
class AlarmsViewModel(val database:AlarmDatabaseDao, application:Application): AndroidViewModel(application) {


    val allAllarms = database.getAllAlarms()

}