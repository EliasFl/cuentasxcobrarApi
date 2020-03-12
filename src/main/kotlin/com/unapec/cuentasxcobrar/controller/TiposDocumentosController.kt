package com.unapec.cuentasxcobrar.controller

import com.unapec.cuentasxcobrar.model.TiposDocumentos
import com.unapec.cuentasxcobrar.repository.TiposDocumentosRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class TiposDocumentosController(
       @Autowired private val repository: TiposDocumentosRepository
) {

    @GetMapping("/tiposDocumentos")
    fun findAll() = repository.findAll()

    @GetMapping("/tiposDocumentos/{id}")
    @Throws(ResourceNotFoundException::class)
    fun findTipoDocumentoById(@PathVariable id: Long): ResponseEntity<TiposDocumentos> {
        val tiposDocumento = repository.findById(id).orElseThrow {
            ResourceNotFoundException("Tipo de documento no encontrado")
        }
        return ResponseEntity.ok().body(tiposDocumento)
    }

    @PostMapping("/tiposDocumentos")
    fun createTipoDocumento(@Valid @RequestBody tiposDocumento: TiposDocumentos) =
            repository.save(tiposDocumento)

    @PutMapping("/tiposDocumentos/{id}")
    @Throws(ResourceNotFoundException::class)
    fun updateTipoDocumento(
            @PathVariable id: Long,
            @Valid @RequestBody tipoDocumentoDetail: TiposDocumentos
    ): ResponseEntity<TiposDocumentos> {
        val tipoDocumento: TiposDocumentos = repository.findById(id).
                orElseThrow {
                    ResourceNotFoundException("Tipo de documento no encontrado")
                }
        tipoDocumento.descripcion = tipoDocumentoDetail.descripcion
        tipoDocumento.cuentaContable = tipoDocumentoDetail.cuentaContable
        tipoDocumento.estado = tipoDocumentoDetail.estado

        val updatedTipoDocumento = repository.save(tipoDocumento)
        return ResponseEntity.ok(updatedTipoDocumento)
    }

    @DeleteMapping("tiposDocumentos/{id}")
    @Throws(ResourceNotFoundException::class)
    fun deleteTipoDocumento(@PathVariable(value = "id") id: Long): Map<String, Boolean> {
        val tipoDocumento: TiposDocumentos = repository.findById(id).
        orElseThrow {
            ResourceNotFoundException("Tipo de documento no encontrado")
        }

        repository.delete(tipoDocumento)
        val response: HashMap<String, Boolean> = HashMap<String, Boolean>()
        response["deleted"] = true
        return response
    }
}