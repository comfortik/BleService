package com.example.bleservice.data.repository

import com.example.bleservice.domain.interactor.device.ConnectDeviceInteractor
import com.example.bleservice.domain.interactor.device.DeviceInteractors
import com.example.bleservice.domain.interactor.device.ScanDevicesInteractor
import com.example.bleservice.domain.interactor.device.StopScanInteractor
import javax.inject.Inject

class DeviceInteractorsImpl @Inject constructor(
    private val scanDevicesInteractor: ScanDevicesInteractor,
    private val connectDeviceInteractor: ConnectDeviceInteractor,
    private val stopScanInteractor: StopScanInteractor
) : DeviceInteractors {
    override fun scanDevicesInteractor() = scanDevicesInteractor
    override fun stopScanInteractor() = stopScanInteractor
    override fun connectDeviceInteractor() = connectDeviceInteractor
}