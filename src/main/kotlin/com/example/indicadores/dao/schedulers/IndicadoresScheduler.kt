package com.example.indicadores.dao.schedulers

import com.example.indicadores.dao.bl.IndicadoresBl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class IndicadoresScheduler (
    @Autowired
    val indicadoresBl: IndicadoresBl
){
    @Scheduled(cron = "0 0 * * * *") // Ejecutar cada hora
    fun ejecutarMonitoreoIndicadores() {
        indicadoresBl.monitorearIndicadores()
    }
}