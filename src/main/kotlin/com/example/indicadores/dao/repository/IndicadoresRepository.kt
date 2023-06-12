package com.example.indicadores.dao.repository

import com.example.indicadores.dao.IndicadoresDao
import org.springframework.data.jpa.repository.JpaRepository

interface IndicadoresRepository : JpaRepository<IndicadoresDao, Long> {

}