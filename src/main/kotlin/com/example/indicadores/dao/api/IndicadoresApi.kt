package com.example.indicadores.dao.api

import com.example.indicadores.dao.IndicadoresDao
import com.example.indicadores.dao.bl.IndicadoresBl
import com.example.indicadores.dto.ResponseDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/indicador")
class IndicadoresApi (
    @Autowired
    private val indicadoresBl: IndicadoresBl
){
    private val logger: Logger = LoggerFactory.getLogger(IndicadoresApi::class.java)

    // Endpoint para crear un indicador
    @PostMapping("/crear")
    fun crearIndicador(@RequestBody indicador: IndicadoresDao): ResponseDto<Any> {
        logger.info("Creando indicador: $indicador")
        val crearIndicador = indicadoresBl.crearIndicador(indicador)
        logger.info("Indicador creado con éxito: $crearIndicador")
        return ResponseDto(crearIndicador, "Indicador creado con éxito!", true)
    }

    // Endpoint para obtener todos los indicadores
    @GetMapping()
    fun obtenerTodosIndicadores(): ResponseDto<List<IndicadoresDao>> {
        logger.info("Obteniendo todos los indicadores")
        val indicadoresList = indicadoresBl.obtenerTodosIndicadores()
        logger.info("Indicadores obtenidos: $indicadoresList")
        return ResponseDto(indicadoresList, "OK", true)
    }

    // Endpoint para obtener indicador por id
    @GetMapping("/{id}")
    fun obtenerIndicadorId(@PathVariable id: Long): ResponseDto<Any> {
        logger.info("Obteniendo indicador con ID: $id")
        val indicador = indicadoresBl.obtenerIndicadorPorId(id)
        logger.info("Indicador obtenido: $indicador")
        return ResponseDto(indicador, "OK", true)
    }

    // Endpoint para actualizar indicador
    @PutMapping("/{id}")
    fun actualizarIndicador(@PathVariable id: Long, @RequestBody indicador: IndicadoresDao): ResponseEntity<IndicadoresDao> {
        logger.info("Actualizando indicador con ID: $id")
        val actualizarIndicador = indicadoresBl.actualizarIndicador(id, indicador)
        logger.info("Indicador actualizado con éxito: $actualizarIndicador")
        return ResponseEntity.ok(actualizarIndicador)
    }

    // Endpoint para validar umbrales
    @PostMapping("/{id}/validar-umbrales")
    fun validarUmbrales(@PathVariable id: Long): ResponseEntity<String> {
        logger.info("Validando umbrales para indicador con ID: $id")
        val validarUmbrales = indicadoresBl.obtenerIndicadorPorId(id)

        if (validarUmbrales != null) {
            val resultado = indicadoresBl.validarUmbrales(validarUmbrales)
            logger.info("Resultado de validación de umbrales: $resultado")
            return ResponseEntity.ok(resultado)
        } else {
            logger.warn("Indicador con ID $id no encontrado")
            return ResponseEntity.notFound().build()
        }
    }
    // Endpoint para monitorear umbrales
    @PostMapping("/monitoreo")
    fun iniciarMonitoreoIndicadores() {
        indicadoresBl.monitorearIndicadores()
    }
}
