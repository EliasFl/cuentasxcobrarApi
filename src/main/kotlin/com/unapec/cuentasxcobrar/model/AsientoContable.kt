package com.unapec.cuentasxcobrar.model

import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
data class AsientoContable (
        @Id @GeneratedValue val id: Long,
        var descripcion: String,
        var cliente: Long,
        var cuenta: Long,
        @NotBlank @Size(max = 2) var tipoMovimiento: String,
        @CreationTimestamp @GeneratedValue @Column(name = "fecha")
        val fechaAsiento: Date?,
        var monto: Long,
        var estado: Char
)