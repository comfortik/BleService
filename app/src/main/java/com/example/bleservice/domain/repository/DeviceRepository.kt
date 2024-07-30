package com.example.bleservice.domain.repository

import android.bluetooth.BluetoothDevice
import com.example.bleservice.data.remote.BLEService


interface DeviceRepository{
    fun scanDevices(callback : ((BluetoothDevice)->Unit))
    fun stopScan()
    fun connectDevice(device: BluetoothDevice)
    fun disconnectDevice(device: BluetoothDevice)
}