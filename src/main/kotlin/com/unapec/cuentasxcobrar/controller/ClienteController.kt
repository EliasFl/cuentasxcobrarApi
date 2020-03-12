package com.unapec.cuentasxcobrar.controller

import com.unapec.cuentasxcobrar.model.Cliente
import com.unapec.cuentasxcobrar.repository.ClienteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class ClienteController(
        @Autowired private val repository: ClienteRepository
) {

    @GetMapping("/clientes")
    fun findAll() = repository.findAll()

    @GetMapping("/clientes/{id}")
    @Throws(ResourceNotFoundException::class)
    fun findClienteById(@PathVariable id: Long): ResponseEntity<Cliente> {
        val cliente = repository.findById(id).orElseThrow {
            ResourceNotFoundException("Cliente no encontrado")
        }
        return ResponseEntity.ok().body(cliente)
    }

    @PostMapping("/clientes")
    fun createCliente(@Valid @RequestBody cliente: Cliente) =
            repository.save(cliente)

    @PutMapping("/clientes/{id}")
    @Throws(ResourceNotFoundException::class)
    fun updateCliente(
            @PathVariable id: Long,
            @Valid @RequestBody clienteDetail: Cliente
    ): ResponseEntity<Cliente> {
        val cliente: Cliente = repository.findById(id).
        orElseThrow {
            ResourceNotFoundException("Cliente no encontrado")
        }
        cliente.apply {
            nombre = clienteDetail.nombre
            cedula = clienteDetail.cedula
            estado = clienteDetail.estado
            limiteCredito = clienteDetail.limiteCredito
        }

        val updatedCliente = repository.save(cliente)
        return ResponseEntity.ok(updatedCliente)
    }

    @DeleteMapping("/clientes/{id}")
    @Throws(ResourceNotFoundException::class)
    fun deleteCliente(@PathVariable(value = "id") id: Long): Map<String, Boolean> {
        val cliente: Cliente = repository.findById(id).
        orElseThrow {
            ResourceNotFoundException("Cliente no encontrado")
        }

        repository.delete(cliente)
        val response: HashMap<String, Boolean> = HashMap<String, Boolean>()
        response["deleted"] = true
        return response
    }
}