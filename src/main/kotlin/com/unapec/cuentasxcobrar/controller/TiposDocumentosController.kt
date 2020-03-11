package com.unapec.cuentasxcobrar.controller

import com.unapec.cuentasxcobrar.model.TiposDocumentos
import com.unapec.cuentasxcobrar.repository.TiposDocumentosRepository
import org.springframework.beans.factory.annotation.Autowired
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
    fun findTipoDocumentoById(@PathVariable id: Long) = repository.findById(id)

    @PostMapping("/tiposDocumentos")
    fun createTipoDocumento(@Valid @RequestBody tiposDocumento: TiposDocumentos) =
            repository.save(tiposDocumento)
}