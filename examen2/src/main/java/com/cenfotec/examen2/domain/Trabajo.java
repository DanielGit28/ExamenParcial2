package com.cenfotec.examen2.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Trabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String auditoria;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    private Long idCliente;

    public Trabajo() {
    }

    public Trabajo(Long id, String auditoria, Date fecha, Long idCliente) {
        this.setId(id);
        this.setAuditoria(auditoria);
        this.setFecha(fecha);
        this.setIdCliente(idCliente);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(String auditoria) {
        this.auditoria = auditoria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
}
