package com.unapec.cuentasxcobrar.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
data class TiposDocumentos (
        @Id @GeneratedValue val id: Long,
        var descripcion: String,
        var cuentaContable: Int,
        var estado: Char
)