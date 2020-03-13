package com.unapec.cuentasxcobrar.controller

import com.unapec.cuentasxcobrar.model.AsientoContable
import com.unapec.cuentasxcobrar.model.Transaccion
import com.unapec.cuentasxcobrar.repository.AsientoContableRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class AsientoContableController(
        @Autowired private val repository: AsientoContableRepository
) {

    @GetMapping("/asientosContables")
    fun findAll() = repository.findAll()

    @GetMapping("/asientosContables/{id}")
    @Throws(ResourceNotFoundException::class)
    fun findAsientoContableById(@PathVariable id: Long): ResponseEntity<AsientoContable> {
        val asientoContable = repository.findById(id).orElseThrow {
            ResourceNotFoundException("Asiento contable no encontrado")
        }
        return ResponseEntity.ok().body(asientoContable)
    }

    @PostMapping("/asientosContables")
    fun createAsientoContable(@Valid @RequestBody asientoContable: AsientoContable) =
            repository.save(asientoContable)

    @PutMapping("/asientosContables/{id}")
    @Throws(ResourceNotFoundException::class)
    fun updateAsientoContable(
            @PathVariable id: Long,
            @Valid @RequestBody asientoContableDetail: AsientoContable
    ): ResponseEntity<AsientoContable> {
        val asientoContable: AsientoContable = repository.findById(id).
        orElseThrow {
            ResourceNotFoundException("Asiento contable no encontrado")
        }
        asientoContable.apply {
            descripcion = asientoContableDetail.descripcion
            cuenta = asientoContable.cuenta
            monto = asientoContableDetail.monto
            estado = asientoContableDetail.estado
            tipoMovimiento = asientoContableDetail.tipoMovimiento
            cliente = asientoContableDetail.cliente
        }

        val updatedAsientoContable = repository.save(asientoContable)
        return ResponseEntity.ok(updatedAsientoContable)
    }

    @DeleteMapping("/asientosContables/{id}")
    @Throws(ResourceNotFoundException::class)
    fun deleteAsientoContable(@PathVariable(value = "id") id: Long): Map<String, Boolean> {
        val asientoContable: AsientoContable = repository.findById(id).
        orElseThrow {
            ResourceNotFoundException("Asiento contable no encontrado")
        }

        repository.delete(asientoContable)
        val response: HashMap<String, Boolean> = HashMap<String, Boolean>()
        response["deleted"] = true
        return response
    }
}