package com.example.bleservice.domain.utils

interface SuccessListener<T> {
    fun onSuccess(`object`: T)
}