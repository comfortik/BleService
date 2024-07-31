package com.example.bleservice.domain.repository

import android.bluetooth.BluetoothDevice
import com.example.bleservice.data.remote.BLEService
import com.example.bleservice.domain.utils.ErrorListener
import com.example.bleservice.domain.utils.SuccessListener


interface DeviceRepository{
    fun scanDevices(callback : ((BluetoothDevice)->Unit))
    fun stopScan()
    fun connectDevice(device: BluetoothDevice, successListener: SuccessListener<Boolean>, errorListener: ErrorListener)
}