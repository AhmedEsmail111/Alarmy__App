package com.example.alarmy.updateAlarmFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.alarmy.database.AlarmData
import com.example.alarmy.database.AlarmDatabaseDao
import kotlinx.coroutines.*

class UpdateAlarmViewModel(val alarmId: Long = 0L, val database: AlarmDatabaseDao, application: Application): AndroidViewModel(application) {

    private val viewModelJob = Job()
    private  val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

     lateinit var currentAlarm : MutableLiveData<AlarmData>


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

     init{
         currentAlarm.value = initializeCurrentAlarm()
     }

    private fun initializeCurrentAlarm(): AlarmData? {
        uiScope.launch {
            withContext(Dispatchers.IO){
                currentAlarm.value = database.get(alarmId)
            }
        }
        return currentAlarm.value
    }


}