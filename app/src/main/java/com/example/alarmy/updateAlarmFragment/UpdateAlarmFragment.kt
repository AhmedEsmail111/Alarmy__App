package com.example.alarmy.updateAlarmFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.alarmy.R
import com.example.alarmy.database.AlarmDatabase
import com.example.alarmy.databinding.FragmentUpdateAlarmBinding
import java.lang.StringBuilder

class UpdateAlarmFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentUpdateAlarmBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_update_alarm,container,false)

        // having a reference to the application context
        val application = requireNotNull(this.activity).application
        // getting the alarmId argument we add when navigating to this fragment
        val arguments = UpdateAlarmFragmentArgs.fromBundle(requireArguments())
        // a reference to the Alarm database Dao
        val database = AlarmDatabase.getInstance(application).alarmDatabaseDao
        // an instance of the viewModelFactory class
        val updateAlarmViewModelFactory = UpdateAlarmViewModelFactory(arguments.alarmId,database,application)
        // get a reference of the viewModel and wire it up with layout and data binding
        val updateAlarmViewModel = ViewModelProvider(this,updateAlarmViewModelFactory).get(UpdateAlarmViewModel::class.java)
        binding.updateViewModel = updateAlarmViewModel
        binding.lifecycleOwner = this

       updateAlarmViewModel.currentAlarm.value?.let {
           binding.ringtoneText.text = it.ringtone
           binding.timeSelectedText.text = StringBuilder().append(it.hour).append(" : ").append(it.minute)
               .append(" ").append(it.amOrPm)
       }

        return binding.root
    }
}