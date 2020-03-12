package com.unapec.cuentasxcobrar.model

import org.springframework.data.annotation.CreatedDate
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
data class Transaccion(
        @Id @GeneratedValue val id: Long,
        @NotBlank @Size(max = 2) var tipoMovimiento: String,
        @ManyToOne @JoinColumn(name = "tipo_documento_id", table = "tipos_documentos") var tipoDocumento: TiposDocumentos,
        var numeroDocumento: Int,
        @Temporal(TemporalType.DATE) @CreatedDate val date: Date,
        @ManyToOne @JoinColumn(name = "cliente_id", table = "cliente") var cliente: Cliente,
        var monto: Long
)