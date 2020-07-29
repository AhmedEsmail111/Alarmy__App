package com.example.alarmy.alarmFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmy.database.AlarmData
import com.example.alarmy.databinding.AlarmsRecyclerViewListBinding

class AlarmsRecyclerView(val clickListener: AlarmClickListener):ListAdapter<AlarmData, AlarmsRecyclerView.AlarmsViewHolder>(AlarmsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmsViewHolder {
        return AlarmsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AlarmsViewHolder, position: Int) {
        holder.bind(getItem(position),clickListener)
    }




    class AlarmsViewHolder private constructor(val binding: AlarmsRecyclerViewListBinding):RecyclerView.ViewHolder(binding.root) {
        //  fun to do the work of binding data to show on the screen
        fun bind(
            item: AlarmData,
            clickListener: AlarmClickListener
        ) {

            binding.day.text = item.dayOfWeek
            binding.time.text = StringBuilder().append(item.hour).append(" : ").append(item.minute)
                .append(" ").append(item.amOrPm)

            binding.clickListener = clickListener
            binding.alarm = item
        }

        companion object {
            fun from(parent: ViewGroup): AlarmsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AlarmsRecyclerViewListBinding.inflate(layoutInflater,parent, false)
                return AlarmsViewHolder(binding)
            }
        }
    }

    class AlarmsDiffCallback: DiffUtil.ItemCallback<AlarmData>(){
        override fun areItemsTheSame(oldItem: AlarmData, newItem: AlarmData): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: AlarmData, newItem: AlarmData): Boolean {
            return oldItem == newItem
        }

    }
}

class AlarmClickListener(val clickListener: (alarmId: Long) -> Unit){
    fun onClick(alarm:AlarmData) = clickListener(alarm.alarmId)
}