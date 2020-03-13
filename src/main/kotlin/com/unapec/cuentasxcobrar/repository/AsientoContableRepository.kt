package com.unapec.cuentasxcobrar.repository

import com.unapec.cuentasxcobrar.model.AsientoContable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AsientoContableRepository: CrudRepository<AsientoContable, Long> {
}