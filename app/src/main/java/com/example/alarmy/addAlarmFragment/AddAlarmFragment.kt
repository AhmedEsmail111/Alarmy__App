package com.example.alarmy.addAlarmFragment

import android.app.Activity
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.alarmy.R
import com.example.alarmy.database.AlarmDatabase
import com.example.alarmy.databinding.FragmentAddAlarmBinding


class AddAlarmFragment : Fragment() , TimePicker.OnTimeChangedListener {
private lateinit var addAlarmViewModel: AddAlarmViewModel
    private lateinit var binding:FragmentAddAlarmBinding


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
           R.layout.fragment_add_alarm, container, false)



        // having a reference to the application context
        val application = requireNotNull(this.activity).application

        // a reference to the Alarm database Dao
        val database = AlarmDatabase.getInstance(application).alarmDatabaseDao

        // an instance of the viewModelFactory
        val viewModelFactory = AddAlarmViewModelFactory(database,application)


        // getting a reference of the viewModel
        addAlarmViewModel = ViewModelProvider(this,viewModelFactory).get(AddAlarmViewModel::class.java)
        binding.lifecycleOwner = this
        binding.addAlarmViewModel = addAlarmViewModel

        // initializing the time in which the selected time will be displayed
        addAlarmViewModel.time = binding.timeSelectedText

        // calling the showTime method to show the current time when the user first navigate to the fragment
        addAlarmViewModel.showTime(addAlarmViewModel.hour, addAlarmViewModel.minute)

        // initializing the timePicker
        addAlarmViewModel.timePicker = binding.alarmTimePicker



        // listener on the set ringtone button to make an Intent
        binding.chooseRingtone.setOnClickListener {
            val ringtoneIntent = Intent(RingtoneManager.ACTION_RINGTONE_PICKER)
            ringtoneIntent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM)
            ringtoneIntent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Tone")
            startActivityForResult(ringtoneIntent, 5)
        }

        // a listener on the back Image
       binding.backImage.setOnClickListener {view: View? ->
           view?.findNavController()?.navigate(AddAlarmFragmentDirections.actionAddAlarmFragmentToAlarmsFragment())
       }

        // a listener on the Save button to store the alarm in the database and navigate to the Alarms Fragments
        binding.saveButton.setOnClickListener { view: View? ->
            addAlarmViewModel.onSaveButtonClicked()
            view?.findNavController()?.navigate(AddAlarmFragmentDirections.actionAddAlarmFragmentToAlarmsFragment())
        }

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 5 && data != null && resultCode == Activity.RESULT_OK ){
            addAlarmViewModel.ringtone.value= data.data.toString()
        }
    }


    // when time changes ->
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onTimeChanged(timePicker: TimePicker?, minute: Int, hour: Int) {
        addAlarmViewModel.setTime()
    }
}
