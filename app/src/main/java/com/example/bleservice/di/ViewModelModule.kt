package com.example.bleservice.di

import com.example.bleservice.data.repository.DataPacketInteractorsImpl
import com.example.bleservice.data.repository.DeviceInteractorsImpl
import com.example.bleservice.domain.interactor.dataPacket.DataPacketInteractors
import com.example.bleservice.domain.interactor.dataPacket.ReceiveDataPacketInteractor
import com.example.bleservice.domain.interactor.dataPacket.SendDataPacketInteractor
import com.example.bleservice.domain.interactor.device.ConnectDeviceInteractor
import com.example.bleservice.domain.interactor.device.DeviceInteractors
import com.example.bleservice.domain.interactor.device.DisconnectDeviceInteractor
import com.example.bleservice.domain.interactor.device.ScanDevicesInteractor
import com.example.bleservice.domain.interactor.device.StopScanInteractor
import com.example.bleservice.domain.repository.DataPacketRepository
import com.example.bleservice.domain.repository.DeviceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideDataPacketInteractors(
        dataPacketRepository: DataPacketRepository
    ): DataPacketInteractors {
        return DataPacketInteractorsImpl(
            sendDataPacketInteractor = SendDataPacketInteractor(dataPacketRepository),
            receiveDataPacketInteractor = ReceiveDataPacketInteractor(dataPacketRepository)
        )
    }

    @Provides
    @ViewModelScoped
    fun provideDeviceInteractors(
        deviceRepository: DeviceRepository
    ): DeviceInteractors {
        return DeviceInteractorsImpl(
            scanDevicesInteractor = ScanDevicesInteractor(deviceRepository),
            connectDeviceInteractor = ConnectDeviceInteractor(deviceRepository),
            disconnectDeviceInteractor = DisconnectDeviceInteractor(deviceRepository),
            stopScanInteractor = StopScanInteractor(deviceRepository)
        )
    }
}
