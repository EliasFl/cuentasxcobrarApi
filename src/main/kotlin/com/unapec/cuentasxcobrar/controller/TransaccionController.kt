package com.unapec.cuentasxcobrar.controller

import com.unapec.cuentasxcobrar.model.Cliente
import com.unapec.cuentasxcobrar.model.Transaccion
import com.unapec.cuentasxcobrar.repository.TransaccionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class TransaccionController(
        @Autowired private val repository: TransaccionRepository
) {

    @GetMapping("/transacciones")
    fun findAll() = repository.findAll()

    @GetMapping("/transacciones/{id}")
    @Throws(ResourceNotFoundException::class)
    fun findTransaccionById(@PathVariable id: Long): ResponseEntity<Transaccion> {
        val transaccion = repository.findById(id).orElseThrow {
            ResourceNotFoundException("Transaccion no encontrada")
        }
        return ResponseEntity.ok().body(transaccion)
    }

    @PostMapping("/transacciones")
    fun createTransaccion(@Valid @RequestBody transaccion: Transaccion) =
            repository.save(transaccion)

    @PutMapping("/transacciones/{id}")
    @Throws(ResourceNotFoundException::class)
    fun updateTransaccion(
            @PathVariable id: Long,
            @Valid @RequestBody transaccionDetail: Transaccion
    ): ResponseEntity<Transaccion> {
        val transaccion: Transaccion = repository.findById(id).
        orElseThrow {
            ResourceNotFoundException("Transaccion no encontrada")
        }
        transaccion.apply {
            tipoMovimiento = transaccionDetail.tipoMovimiento
            tipoDocumento = transaccionDetail.tipoDocumento
            numeroDocumento = transaccionDetail.numeroDocumento
            cliente = transaccionDetail.cliente
        }

        val updatedTransaccion = repository.save(transaccion)
        return ResponseEntity.ok(updatedTransaccion)
    }

    @DeleteMapping("/transacciones/{id}")
    @Throws(ResourceNotFoundException::class)
    fun deleteTransaccion(@PathVariable(value = "id") id: Long): Map<String, Boolean> {
        val transaccion: Transaccion = repository.findById(id).
        orElseThrow {
            ResourceNotFoundException("Transaccion no encontrada")
        }

        repository.delete(transaccion)
        val response: HashMap<String, Boolean> = HashMap<String, Boolean>()
        response["deleted"] = true
        return response
    }
}