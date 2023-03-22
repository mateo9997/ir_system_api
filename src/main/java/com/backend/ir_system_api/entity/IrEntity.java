package com.backend.ir_system_api.entity;

import javax.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
@Table(name = "Mujer")
public class IrEntity {

    @Id
    private String nombre;
    @Column(name = "nomNacim")
    private String nomNacim;
    @Column(name = "ciudadNacim")
    private String ciudadNacim;
    @Column(name = "fechaNacim")
    private Date fechaNacim;
    @Column(name = "ciudadFall")
    private String ciudadFall;
    @Column(name = "fechaFall")
    private Date fechaFall;
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @Column(name = "almaMater")
    private String almaMater;
    @Column(name = "bio", length = 5000)
    private String bio;
    @Lob
    @Column(name = "foto", columnDefinition = "mediumblob")
    private byte[] foto;

}