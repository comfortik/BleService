package com.example.bleservice.domain.repository

import com.example.bleservice.domain.model.DataPacket
import com.example.bleservice.domain.utils.ErrorListener
import com.example.bleservice.domain.utils.SuccessListener
import com.example.bleservice.features.utlis.DataTransferState

interface DataPacketRepository {
    fun sendData(dataPacket: DataPacket, successListener: SuccessListener<DataTransferState>, errorListener: ErrorListener)
}