package com.example.bleservice.data.repository

import android.bluetooth.BluetoothDevice
import com.example.bleservice.data.remote.BLEService
import com.example.bleservice.domain.repository.DeviceRepository
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(private val service: BLEService) : DeviceRepository {
    override fun scanDevices(callback: (BluetoothDevice) -> Unit) {
        service.scanForDevices(callback)
    }

    override fun stopScan() {
        service.stopScanning()
    }

    override fun connectDevice(device: BluetoothDevice) {
        service.connectDevice(device)
    }

    override fun disconnectDevice(device: BluetoothDevice) {
    }
}
