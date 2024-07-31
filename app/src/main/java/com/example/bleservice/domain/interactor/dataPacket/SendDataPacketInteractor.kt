package com.example.bleservice.domain.interactor.dataPacket

import com.example.bleservice.domain.model.DataPacket
import com.example.bleservice.domain.repository.DataPacketRepository
import com.example.bleservice.domain.utils.ErrorListener
import com.example.bleservice.domain.utils.SuccessListener
import com.example.bleservice.features.utlis.DataTransferState

class SendDataPacketInteractor(private val dataPacketRepository: DataPacketRepository) {
    fun execute(dataPacket: DataPacket, successListener: SuccessListener<DataTransferState>, errorListener: ErrorListener){
        dataPacketRepository.sendData(dataPacket, successListener, errorListener)
    }
}