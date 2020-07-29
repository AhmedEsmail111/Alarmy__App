package com.example.alarmy.database


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "alarm_data_table")
data class AlarmData(
    @PrimaryKey(autoGenerate = true)
    var alarmId: Long = 0L,

    var hour: Int?  = 0,

    var minute: Int?  =0,

    @ColumnInfo(name = "day_of_week")
    var dayOfWeek: String = "Tuesday",

    var ringtone: String? = " " ,
    @ColumnInfo(name = "am_pm")

    var amOrPm: String? = " "

 
)
