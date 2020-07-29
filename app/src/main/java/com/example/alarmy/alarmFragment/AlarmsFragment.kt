package com.example.alarmy.alarmFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmy.R
import com.example.alarmy.database.AlarmDatabase
import com.example.alarmy.databinding.FragmentAlarmsBinding
import kotlinx.android.synthetic.main.fragment_alarms.*


class AlarmsFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentAlarmsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_alarms, container, false)

        // having a reference to the application context
        val application = requireNotNull(this.activity).application

        // a reference to the Alarm database Dao
        val database = AlarmDatabase.getInstance(application).alarmDatabaseDao

        // creating an object of the viewModelFactory
        val alarmsViewModelFactory = AlarmsViewModelFactory(database,application)

        // // creating an object of the viewModel
        val alarmsViewModel = ViewModelProvider(this,alarmsViewModelFactory).get(AlarmsViewModel::class.java)

        // setting up the recyclerView adapter and the listener to go to the UpdateAlarmsFragment
        val alarmsAdapter = AlarmsRecyclerView(AlarmClickListener {alarmId->
            alarmsViewModel.onAnItemClicked(alarmId)

        })
      //  its data that will observe
        binding.alarmsRecyclerView.adapter = alarmsAdapter
        alarmsViewModel.allAlarms.observe(viewLifecycleOwner, Observer {
            it?.let {
                alarmsAdapter.submitList(it)
                binding.alarmsRecyclerView.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)


            }
        })

        // observing the navigateToUpdateFragment liveData to navigate to UpdateAlarmFragment
        // and  pass the alarmId if it is not null
        alarmsViewModel.navigateToUpdateFragment.observe(viewLifecycleOwner, Observer {alarmId->
            alarmId?.let {
                this.findNavController().navigate(AlarmsFragmentDirections.actionAlarmsFragmentToUpdateAlarmFragment(alarmId))
                alarmsViewModel.onDoneNavigating()
            }

        })

        // intent to go to the addAlarmFragment
        binding.addAlarmFab.setOnClickListener {view: View ->
            view.findNavController().navigate(AlarmsFragmentDirections.actionAlarmsFragmentToAddAlarmFragment())
        }

        return binding.root
    }


}