package com.example.bleservice.features.main.adapter

import android.bluetooth.BluetoothDevice
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bleservice.R
import com.example.bleservice.features.utlis.DeviceDiffCallback


class DeviceAdapter(private val listener: OnItemClickListener) : ListAdapter<BluetoothDevice, DeviceAdapter.DeviceViewHolder>(DeviceDiffCallback()) {

    private var selectedPosition: Int = RecyclerView.NO_POSITION

    inner class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val tvName: TextView = itemView.findViewById(R.id.device_name)
        private val tvNumber: TextView = itemView.findViewById(R.id.device_number)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(device: BluetoothDevice, isSelected: Boolean) {
            tvName.text = device.name ?: "null"
            tvNumber.text = device.address
            itemView.setBackgroundColor(if (isSelected) Color.parseColor("#96A8C3") else Color.WHITE) // Измените цвет по вашему усмотрению
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(getItem(position))
                notifyItemChanged(selectedPosition)
                selectedPosition = position
                notifyItemChanged(selectedPosition)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return DeviceViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = getItem(position)
        holder.bind(device, position == selectedPosition)
    }


    fun clearItems() {
        submitList(emptyList())
    }

    interface OnItemClickListener {
        fun onItemClick(device: BluetoothDevice)
    }
}
