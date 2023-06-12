package com.example.indicadores.dto

data class IndicadoresDto(
    var id_indicador: Long,
    var area: String,
    var descripcion: String,
    var riesgo: String,
    var periodicidad: String,
    var formula: String,
    var unidad_medida: String,
    var valor: Double,
    var limite_alerta: Double,
    var umbral_verde: Double,
    var umbral_amarillo_min: Double,
    var umbral_amarillo_max: Double,
    var umbral_rojo: Double
)