package com.backend.ir_system_api.services;

import com.backend.ir_system_api.entity.IrEntity;
import com.backend.ir_system_api.model.Ir;

import java.util.List;

public interface IrService {
    Ir SaveIr(Ir ir);

    List<Ir> getAllIr();

    boolean deleteIr(String nombre);

    Ir getIrByNombre(String nombre);

    Ir updateIr(String nombre, Ir irDetails);
}
