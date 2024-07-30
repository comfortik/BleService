package com.example.bleservice.features.main.adapter

import android.bluetooth.BluetoothDevice
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

    inner class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val tvName: TextView = itemView.findViewById(R.id.device_name)
        private val tvNumber: TextView = itemView.findViewById(R.id.device_number)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(device: BluetoothDevice) {
            tvName.text = device.name ?: "null"

            if(device.address.equals("C4:3D:1A:1C:0A:97") || device.address.equals("98:50:2E:AB:3D:56")) Log.e("nout", "asdjasdkasgd")
            tvNumber.text = device.address
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(getItem(position))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return DeviceViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = getItem(position)
        holder.bind(device)
    }

    interface OnItemClickListener {
        fun onItemClick(device: BluetoothDevice)
    }
}
