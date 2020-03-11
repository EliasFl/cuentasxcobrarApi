package com.unapec.cuentasxcobrar.repository

import com.unapec.cuentasxcobrar.model.TiposDocumentos
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TiposDocumentosRepository: CrudRepository<TiposDocumentos, Long> {
}