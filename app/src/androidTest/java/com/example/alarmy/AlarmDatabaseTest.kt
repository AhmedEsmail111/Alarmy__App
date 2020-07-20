/*
package com.example.alarmy


import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.alarmy.database.AlarmDatabase
import com.example.alarmy.database.AlarmDatabaseDao
import com.example.alarmy.database.AlarmData
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

*/
/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 *//*


@RunWith(AndroidJUnit4::class)
class AlarmDatabaseTest {

    private lateinit var sleepDao: AlarmDatabaseDao
    private lateinit var db: AlarmDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, AlarmDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        sleepDao = db.alarmDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNight() {
        val alarm = AlarmData()
        sleepDao.insert(alarm)
        val tonight = sleepDao.getTonight(alarm.alarmId)
        assertEquals(alarm.hour, -1)
    }
}

*/
