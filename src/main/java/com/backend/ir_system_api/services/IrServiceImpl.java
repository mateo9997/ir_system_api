package com.backend.ir_system_api.services;

import com.backend.ir_system_api.entity.IrEntity;
import com.backend.ir_system_api.model.Ir;
import com.backend.ir_system_api.repository.IrRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IrServiceImpl implements IrService{


    private IrRepository irRepository;

    public IrServiceImpl(IrRepository irRepository) {
        this.irRepository = irRepository;
    }

    @Override
    public Ir SaveIr(Ir ir) {
        IrEntity irEntity = new IrEntity();
        BeanUtils.copyProperties(ir, irEntity, "foto");
        if (ir.getFoto() != null) {
            irEntity.setFoto(ir.getFoto());
        }
        irRepository.save(irEntity);
        return ir;
    }

    @Override
    public List<Ir> getAllIr(String orderBy, String order) {
        // Add sorting based on the orderBy and order parameters
        Sort sort = Sort.by(order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, orderBy);
        List<IrEntity> irEntities = irRepository.findAll(sort);

        List<Ir> irs = irEntities
                .stream()
                .map(irm -> new Ir(
                        irm.getNombre(),
                        irm.getNomNacim(),
                        irm.getCiudadNacim(),
                        irm.getFechaNacim(),
                        irm.getCiudadFall(),
                        irm.getFechaFall(),
                        irm.getNacionalidad(),
                        irm.getAlmaMater(),
                        irm.getBio(),
                        irm.getFoto() != null ? irm.getFoto().clone() : null))
                .collect(Collectors.toList());;
        return irs;
    }

    @Override
    public boolean deleteIr(String nombre) {
        IrEntity ir = irRepository.findById(nombre).get();
        irRepository.delete(ir);
        return true;
    }

    @Override
    public Ir getIrByNombre(String nombre) {
        IrEntity irEntity = irRepository.findById(nombre).get();
        Ir ir = new Ir();
        BeanUtils.copyProperties(irEntity, ir, "foto");
        if (irEntity.getFoto() != null) {
            ir.setFoto(irEntity.getFoto().clone());
        }
        return ir;
    }

    @Override
    public Ir updateIr(String nombre, Ir irDetails) {
        if (nombre == null || nombre.equals("undefined")) {
            throw new IllegalArgumentException("Invalid 'nombre' value");
        }

        Optional<IrEntity> irOptional = irRepository.findById(nombre);

        if (irOptional.isPresent()) {
            IrEntity irEntity = irOptional.get();

            // Update the fields of the existing IrEntity object
            irEntity.setNombre(irDetails.getNombre());
            irEntity.setNomNacim(irDetails.getNomNacim());
            irEntity.setCiudadNacim(irDetails.getCiudadNacim());
            irEntity.setFechaNacim(irDetails.getFechaNacim());
            irEntity.setCiudadFall(irDetails.getCiudadFall());
            irEntity.setFechaFall(irDetails.getFechaFall());
            irEntity.setNacionalidad(irDetails.getNacionalidad());
            irEntity.setAlmaMater(irDetails.getAlmaMater());
            irEntity.setBio(irDetails.getBio());

            if(irDetails.getFoto() != null) {
                irEntity.setFoto(irDetails.getFoto());
            }

            // Save the updated IrEntity object to the database
            irRepository.save(irEntity);

            // Convert the IrEntity object to an Ir object
            Ir ir = new Ir();
            BeanUtils.copyProperties(irEntity, ir, "foto");
            if (irEntity.getFoto() != null) {
                ir.setFoto(irEntity.getFoto().clone());
            }
            return ir;
        } else {
            throw new NoSuchElementException("Ir with nombre: " + nombre + " was not found.");
        }
    }



}
