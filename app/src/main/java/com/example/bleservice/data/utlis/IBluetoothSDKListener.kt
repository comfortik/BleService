package com.example.bleservice.data.utlis

import android.bluetooth.BluetoothDevice

interface IBluetoothSDKListener {
    fun onDiscoveryStarted()
    fun onDiscoveryStopped()
    fun onDeviceDiscovered(device: BluetoothDevice?)
    fun onDeviceConnected(device: BluetoothDevice?)
    fun onMessageReceived(device: BluetoothDevice?, message: String?)
    fun onMessageSent(device: BluetoothDevice?)
    fun onError(message: String?)
    fun onDeviceDisconnected()
}
