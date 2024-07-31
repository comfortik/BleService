package com.example.bleservice.domain.utils

import com.example.bleservice.domain.model.Error

interface ErrorListener {
    fun onError(error: Error)
}