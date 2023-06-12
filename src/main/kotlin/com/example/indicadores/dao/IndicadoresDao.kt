package com.example.indicadores.dao

import javax.persistence.*

@Entity
@Table(name = "indicadores")
class IndicadoresDao (
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
    var umbral_rojo: Double,
    var umbral_amarillo_max: Double,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id_indicador: Long = 0
){
    constructor(): this ("","","","","","", 0.0,0.0,0.0,0.0,0.0,0.0){

    }
}