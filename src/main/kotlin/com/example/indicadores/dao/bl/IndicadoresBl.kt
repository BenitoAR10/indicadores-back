package com.example.indicadores.dao.bl

import com.example.indicadores.dao.IndicadoresDao
import com.example.indicadores.dao.repository.IndicadoresRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class IndicadoresBl (
    @Autowired
    val indicadoresRepository: IndicadoresRepository
) {
    private val logger: Logger = LoggerFactory.getLogger(IndicadoresBl::class.java)

    // Función para crear indicador
    fun crearIndicador(indicador: IndicadoresDao): IndicadoresDao {
        logger.info("Creando indicador: $indicador")
        val creado = indicadoresRepository.save(indicador)
        logger.info("Indicador creado con éxito: $creado")
        return creado
    }

    // Función para obtener todos los indicadores
    fun obtenerTodosIndicadores(): List<IndicadoresDao> {
        logger.info("Obteniendo todos los indicadores")
        return indicadoresRepository.findAll()
    }

    // Función para obtener indicador por id
    fun obtenerIndicadorPorId(id: Long): IndicadoresDao {
        logger.info("Obteniendo indicador con ID: $id")
        return indicadoresRepository.findById(id).orElseThrow {
            logger.warn("Indicador con ID $id no encontrado")
            NoSuchElementException("Indicador no encontrado")
        }
    }

    // Función para actualizar indicador
    fun actualizarIndicador(id: Long, indicador: IndicadoresDao): IndicadoresDao {
        logger.info("Actualizando indicador con ID: $id")
        val existeIndicador = obtenerIndicadorPorId(id)
        existeIndicador.area = indicador.area
        existeIndicador.descripcion = indicador.descripcion
        existeIndicador.riesgo = indicador.riesgo
        existeIndicador.periodicidad = indicador.periodicidad
        existeIndicador.formula = indicador.formula
        existeIndicador.unidad_medida = indicador.unidad_medida
        existeIndicador.valor = indicador.valor
        existeIndicador.limite_alerta = indicador.limite_alerta
        existeIndicador.umbral_verde = indicador.umbral_verde
        existeIndicador.umbral_amarillo_min = indicador.umbral_amarillo_min
        existeIndicador.umbral_rojo = existeIndicador.umbral_rojo
        existeIndicador.umbral_amarillo_max = existeIndicador.umbral_amarillo_max
        val actualizado = indicadoresRepository.save(existeIndicador)
        logger.info("Indicador actualizado con éxito: $actualizado")
        return actualizado
    }

    // Función para validar umbrales
    fun validarUmbrales(indicador: IndicadoresDao): String {
        logger.info("Validando umbrales para indicador: $indicador")
        val valor = indicador.valor
        val umbralVerde = indicador.umbral_verde
        val umbralAmarilloMin = indicador.umbral_amarillo_min
        val umbralAmarilloMax = indicador.umbral_amarillo_max
        val umbralRojo = indicador.umbral_rojo

        val resultado = when {
            valor <= umbralVerde -> "Verde"
            valor in (umbralAmarilloMin..umbralAmarilloMax) -> "Amarillo"
            valor > umbralRojo -> "Rojo"
            else -> "Sin categoría"
        }

        logger.info("Resultado de validación de umbrales: $resultado")
        return resultado
    }

    // funcion para monitorear los indicadores y generar notificaciones
    fun monitorearIndicadores(){
        val indicador = indicadoresRepository.findAll()
        for (indicador in indicador){
            if (indicador.valor >= indicador.limite_alerta){
                val mensaje = "El indicador '${indicador.descripcion}' ha superado el limite de alerta"
                logger.info("Se ha generaodo una notificacion para el indicador '${indicador.descripcion    }'")
            }
        }
    }
}
