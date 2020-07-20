package com.example.alarmy.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlarmDatabaseDao {

    @Insert
    fun insert(alarm: AlarmData)

    @Update
    fun update(alarm: AlarmData)

    @Delete
    fun delete(alarm: AlarmData)

    @Query("SELECT * FROM alarm_data_table WHERE alarmId = :key")
    fun get(key: Long): AlarmData

    @Query("SELECT * FROM alarm_data_table ORDER BY alarmId  DESC")
    fun  getAllAlarms():LiveData<List<AlarmData>>

    @Query("SELECT * FROM alarm_data_table ORDER BY alarmId DESC LIMIT 1")
    fun getCurrentAlarm(): AlarmData?
}