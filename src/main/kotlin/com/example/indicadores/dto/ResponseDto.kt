package com.example.indicadores.dto

class ResponseDto <T> (
    val data: T?,
    val message: String?,
    val success: Boolean
){
}