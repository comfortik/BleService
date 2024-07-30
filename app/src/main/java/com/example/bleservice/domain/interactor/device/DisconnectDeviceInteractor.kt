package com.example.bleservice.domain.interactor.device

import android.bluetooth.BluetoothDevice
import com.example.bleservice.domain.repository.DeviceRepository

class DisconnectDeviceInteractor(private val deviceRepository: DeviceRepository) {
    fun execute(device: BluetoothDevice){
        deviceRepository.disconnectDevice(device)
    }
}