package com.unapec.cuentasxcobrar.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
data class Transaccion(
        @Id @GeneratedValue val id: Long,
        @NotBlank @Size(max = 2) var tipoMovimiento: String,
        var tipoDocumento: Long,
        var numeroDocumento: Int,
        @CreationTimestamp @GeneratedValue @Column(name = "fecha")
        val fecha: Date?,
        var cliente: Long,
        var monto: Long
)