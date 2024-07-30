package com.example.bleservice.domain.interactor.device

import com.example.bleservice.domain.repository.DeviceRepository

class StopScanInteractor(private val deviceRepository: DeviceRepository){
    fun execute(){
        deviceRepository.stopScan()
    }
}