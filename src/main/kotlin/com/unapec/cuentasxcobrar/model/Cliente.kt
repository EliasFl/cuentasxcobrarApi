package com.unapec.cuentasxcobrar.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Cliente (
        @Id @GeneratedValue val id: Long,
        var nombre: String,
        var cedula: Long,
        var limiteCredito: Int,
        var estado: Char
)