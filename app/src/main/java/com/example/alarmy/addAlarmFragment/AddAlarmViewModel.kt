package com.example.alarmy.addAlarmFragment


import android.app.Application
import android.os.Build
import android.widget.TextView
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.alarmy.database.AlarmData
import com.example.alarmy.database.AlarmDatabaseDao
import kotlinx.coroutines.*
import java.util.*


class AddAlarmViewModel(val database: AlarmDatabaseDao,application: Application): AndroidViewModel(application) {
    // timePicker
     lateinit var timePicker: TimePicker
    // text to display the selected time
     var time: TextView? = null
   // an instance of the Calender class
  private  var calendar = Calendar.getInstance()
    // minute and hour from the current calender to show off when the user opens the fragment
    val minute = calendar.get(Calendar.MINUTE)
    val hour = calendar.get(Calendar.HOUR)

   //liveData  variable to store the selected ringtone
     val ringtone = MutableLiveData<String>()

    //Two liveData  variables to store the selected hour and minute
   private val  timePickerHour   = MutableLiveData<Int>()
   private val  timePickerMinute = MutableLiveData<Int>()

// time format variable (PM/AM)
    private var format = MutableLiveData<String>()

    // LiveData Variable for holding the current alarm
   private val currentAlarm = MutableLiveData<AlarmData?>()

    // LiveData for holding all the alarms
    val allAlarm = database.getAllAlarms()


  private val viewModelJob = Job()
   private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        initializeTheCurrentAlarm()
    }

    private fun initializeTheCurrentAlarm() {
        uiScope.launch {
            currentAlarm.value = getCurrentAlarmFromDatabase()
        }
    }

    private suspend fun getCurrentAlarmFromDatabase(): AlarmData? {
       return withContext(Dispatchers.IO){
           var alarm = database.getCurrentAlarm()
           if (alarm?.hour == 0 && alarm.minute == 0 ) {
               alarm = null
           }
           alarm
           }
       }



    fun onSaveButtonClicked(){
        uiScope.launch {
            val alarm = AlarmData()
            alarm.hour = timePickerHour.value
            alarm.minute = timePickerMinute.value
            alarm.ringtone = ringtone.value
            alarm.amOrPm = format.value
            insert(alarm)
            currentAlarm.value = getCurrentAlarmFromDatabase()
        }
    }

    private suspend fun insert(alarm: AlarmData) {
        withContext(Dispatchers.IO){
            database.insert(alarm)
        }
    }


    // a fun to change and display the selected time
@RequiresApi(Build.VERSION_CODES.M)
    fun setTime() {
     timePickerHour.value = timePicker.getHour()
     timePickerMinute.value = timePicker.getMinute()
        showTime( timePickerHour.value,  timePickerMinute.value)
    }

     fun showTime(hour: Int?, min: Int?) {
        var hour2 = hour
         if (hour2 != null) {
             when {
                 hour2 == 0 -> {
                     hour2+= 12
                     format.value = "AM"
                 }
                 hour2 == 12 -> {
                     format.value = "PM"
                 }
                 hour2 > 12 -> {
                     hour2 -= 12
                     format.value = "PM"
                 }
                 else -> {
                     format.value = "AM"
                 }
             }
         }

        time?.text = StringBuilder().append(hour).append(" : ").append(min)
            .append(" ").append(format.value)
    }

}