package com.example.bleservice.features.main.presentation

enum class DataTransferState {
    IDLE,             // Начальное состояние, ожидание передачи или приема данных
    SENDING,          // Данные находятся в процессе отправки
    SENT,             // Данные успешно отправлены,
    RECEIVING,        // Данные находятся в процессе получения
    RECEIVED
}
