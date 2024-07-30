package com.example.bleservice.domain.interactor.device

interface DeviceInteractors {
    fun scanDevicesInteractor(): ScanDevicesInteractor
    fun stopScanInteractor(): StopScanInteractor
    fun connectDeviceInteractor(): ConnectDeviceInteractor
    fun disconnectDeviceInteractor(): DisconnectDeviceInteractor
}