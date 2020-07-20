package com.example.alarmy.alarmFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.alarmy.R
import com.example.alarmy.database.AlarmDatabase
import com.example.alarmy.databinding.FragmentAlarmsBinding


class AlarmsFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentAlarmsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_alarms, container, false)

       // attaching the adapter to  the recycler view
        val adapter = AlarmsRecyclerView()
        binding.alarmsRecyclerView.adapter = adapter

        // having a reference to the application context
        val application = requireNotNull(this.activity).application

        // a reference to the Alarm database Dao
        val database = AlarmDatabase.getInstance(application).alarmDatabaseDao

        // creating an object of the viewModelFactory
        val alarmsViewModelFactory = AlarmsViewModelFactory(database,application)

        // // creating an object of the viewModel
        val alarmsViewModel = ViewModelProvider(this,alarmsViewModelFactory).get(AlarmsViewModel::class.java)

        // setting up the recyclerView adapter and its data that will observe
        val alarmsAdapter = AlarmsRecyclerView()
        binding.alarmsRecyclerView.adapter = alarmsAdapter
        alarmsViewModel.allAllarms.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        // intent to go to the addAlarmFragment
        binding.addAlarmFab.setOnClickListener {view: View ->
            view.findNavController().navigate(AlarmsFragmentDirections.actionAlarmsFragmentToAddAlarmFragment())
        }

        return binding.root
    }
    

}