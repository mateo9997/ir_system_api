package com.backend.ir_system_api.controller;

import com.backend.ir_system_api.model.Ir;
import com.backend.ir_system_api.services.IrService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class IrController {

    private final IrService irService;

    public IrController(IrService irService) {
        this.irService = irService;
    }

    //Receives ir objects from the front
    @PostMapping("/irs")
    public Ir SaveIr(@RequestPart("ir") String irJson, @RequestPart(value = "foto", required = false) MultipartFile foto) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Ir ir = objectMapper.readValue(irJson, Ir.class);

        if (foto != null) {
            ir.setFoto(foto.getBytes());
        }
        return irService.SaveIr(ir);
    }
    //Sends all the ir objects to the front
    @GetMapping("/irs")
    public List<Ir> getAllIr(
            @RequestParam(value = "orderBy", required = false, defaultValue = "nombre") String orderBy,
            @RequestParam(value = "order", required = false, defaultValue = "asc") String order
    ) {
        return irService.getAllIr(orderBy, order);
    }

    @DeleteMapping("/irs/{nombre}")
    public ResponseEntity<Map<String,Boolean>> deleteIr(@PathVariable("nombre") String nombre) {
        boolean deleted =false;
        deleted = irService.deleteIr(nombre);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return  ResponseEntity.ok(response);
    }
    @GetMapping("/irs/{nombre}")
    public ResponseEntity<Ir> getIrByNombre(@PathVariable("nombre") String nombre){
        Ir ir = null;
        ir = irService.getIrByNombre(nombre);
        return ResponseEntity.ok(ir);
    }
    // Modify the method signature to accept "irJson" as a String
    @PutMapping("/irs/{nombre}")
    public ResponseEntity<Ir> updateIr(@PathVariable("nombre") String nombre, @RequestPart("ir") String irJson, @RequestPart(value = "foto", required = false) MultipartFile foto) throws IOException {
        // Deserialize the JSON string into an Ir object
        ObjectMapper objectMapper = new ObjectMapper();
        Ir ir = objectMapper.readValue(irJson, Ir.class);

        if (foto != null) {
            ir.setFoto(foto.getBytes());
        }
        // Pass the id along with the ir object to the updateIr method of IrService
        ir = irService.updateIr(nombre, ir);
        return ResponseEntity.ok(ir);
    }



}
