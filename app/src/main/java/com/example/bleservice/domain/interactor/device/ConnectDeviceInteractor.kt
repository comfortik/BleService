package com.example.bleservice.domain.interactor.device

import android.bluetooth.BluetoothDevice
import com.example.bleservice.domain.repository.DeviceRepository
import com.example.bleservice.domain.utils.ErrorListener
import com.example.bleservice.domain.utils.SuccessListener

class ConnectDeviceInteractor(
    private val deviceRepository: DeviceRepository
) {
    fun execute(
        device: BluetoothDevice,
        successListener: SuccessListener<Boolean>,
        errorListener: ErrorListener
    ) {
        deviceRepository.connectDevice(
            device,
            successListener,
            errorListener
        )
    }
}
