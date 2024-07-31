package com.example.bleservice.data.repository

import com.example.bleservice.domain.interactor.dataPacket.DataPacketInteractors
import com.example.bleservice.domain.interactor.dataPacket.SendDataPacketInteractor
import javax.inject.Inject

class DataPacketInteractorsImpl @Inject constructor(
    private val sendDataPacketInteractor: SendDataPacketInteractor
) : DataPacketInteractors {
    override fun sendDataPacketInteractor() = sendDataPacketInteractor
}
