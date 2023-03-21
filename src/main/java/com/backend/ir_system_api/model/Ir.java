package com.backend.ir_system_api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.sql.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ir {
    private String nombre;
    private String nomNacim;
    private String ciudadNacim;
    private Date fechaNacim;
    private String ciudadFall;
    private Date fechaFall;
    private String nacionalidad;
    private String almaMater;
    private String bio;
    private byte[] foto;
}
