package com.example.alarmy.alarmFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.alarmy.database.AlarmDatabaseDao
class AlarmsViewModel(val database:AlarmDatabaseDao, application:Application): AndroidViewModel(application) {


    val allAlarms = database.getAllAlarms()

    // liveData to the id of the selected alarm
    private val _navigateToUpdateFragment = MutableLiveData<Long>()
            val navigateToUpdateFragment
       get() = _navigateToUpdateFragment

    // fun to assign the value of liveData to id of the selected alarm to pass it to the updateFragment
    fun onAnItemClicked(id:Long){
        _navigateToUpdateFragment.value = id
    }

    // fun to assign the _navigateToUpdateFragment to null
    fun onDoneNavigating(){
        _navigateToUpdateFragment.value = null
    }

}