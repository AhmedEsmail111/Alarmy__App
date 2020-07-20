package com.example.alarmy.alarmFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmy.R
import com.example.alarmy.database.AlarmData

class AlarmsRecyclerView:RecyclerView.Adapter<AlarmsRecyclerView.AlarmsViewHolder>() {

    var data = listOf<AlarmData>()

    set(value) {
        field = value
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.alarms_recycler_view_list,parent,false)
        return  AlarmsViewHolder(view)
    }

    override fun getItemCount() = data.size


    override fun onBindViewHolder(holder: AlarmsViewHolder, position: Int) {
        val item = data[position]
        holder.day.text = item.dayOfWeek
        holder.time.text = StringBuilder().append(item.hour).append(" : ").append(item.minute)
            .append(" ").append(item.amOrPm)
    }
    class AlarmsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val time: TextView = itemView.findViewById(R.id.time)
        val day:TextView = itemView.findViewById(R.id.day)
    }

}