package com.unapec.cuentasxcobrar.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
data class TiposDocumentos (
        @Id @GeneratedValue val id: Long,
        val descripcion: String,
        val cuentaContable: Int,
        val estado: Char
)