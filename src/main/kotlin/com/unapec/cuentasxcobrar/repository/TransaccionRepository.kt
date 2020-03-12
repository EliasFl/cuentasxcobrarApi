package com.unapec.cuentasxcobrar.repository

import com.unapec.cuentasxcobrar.model.Transaccion
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TransaccionRepository: CrudRepository<Transaccion, Long> {
}