package com.example.bleservice.domain.interactor.device

import android.bluetooth.BluetoothDevice
import com.example.bleservice.domain.repository.DeviceRepository

class ScanDevicesInteractor(private val deviceRepository: DeviceRepository) {
    fun execute(callback: (BluetoothDevice) -> Unit) {
        deviceRepository.scanDevices(callback)
    }
}
