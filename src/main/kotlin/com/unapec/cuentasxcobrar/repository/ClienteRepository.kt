package com.unapec.cuentasxcobrar.repository

import com.unapec.cuentasxcobrar.model.Cliente
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ClienteRepository: CrudRepository<Cliente, Long> {
}