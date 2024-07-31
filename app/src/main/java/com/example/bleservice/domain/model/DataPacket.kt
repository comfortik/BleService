package com.example.bleservice.domain.model

data class DataPacket(
    val data: ByteArray,
    val timestamp: Long = System.currentTimeMillis()
)

