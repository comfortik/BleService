package com.example.bleservice.data.repository

import android.os.Handler
import android.os.Looper
import com.example.bleservice.data.remote.BLEService
import com.example.bleservice.domain.model.DataPacket
import com.example.bleservice.domain.repository.DataPacketRepository
import com.example.bleservice.domain.utils.ErrorListener
import com.example.bleservice.domain.utils.SuccessListener
import com.example.bleservice.features.utlis.DataTransferState
import javax.inject.Inject

class DataPacketRepositoryImpl @Inject constructor(
    private val service: BLEService
) : DataPacketRepository {

    companion object {
        private const val BLOCK_SIZE = 160
        private const val INTERVAL_MS = 60
    }

    private val handler = Handler(Looper.getMainLooper())

    override fun sendData(dataPacket: DataPacket, successListener: SuccessListener<DataTransferState>, errorListener: ErrorListener) {
        val data = dataPacket.data
        val totalBlocks = (data.size + BLOCK_SIZE - 1) / BLOCK_SIZE

        var currentIndex = 0

        fun sendNextBlock() {
            if (currentIndex < totalBlocks) {
                val start = currentIndex * BLOCK_SIZE
                val end = (start + BLOCK_SIZE).coerceAtMost(data.size)
                val block = data.copyOfRange(start, end)
                service.writeData(block,successListener, errorListener )
                currentIndex++

                handler.postDelayed(::sendNextBlock, INTERVAL_MS.toLong())
            }
        }

        sendNextBlock()
    }

}
