package com.E4.cosmos_sql.controller;

import com.E4.cosmos_sql.model.FacturasEmitidas;
import com.E4.cosmos_sql.repository.FacturasEmitidasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/factu")
public class FacturasController {

    private final FacturasEmitidasRepository facturasEmitidasRepository;

    @Autowired
    public FacturasController(FacturasEmitidasRepository facturasEmitidasRepository) {
        this.facturasEmitidasRepository = facturasEmitidasRepository;
    }

    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> createFactura(@RequestBody FacturasEmitidas factura) {
        return facturasEmitidasRepository.save(factura).map(savedFactura -> {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Factura creada exitosamente");
            response.put("factura", savedFactura);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        });
    }

    @GetMapping
    public Flux<Map<String, Object>> getAllFacturas() {
        return facturasEmitidasRepository.findAll().map(factura -> {
            Map<String, Object> response = new HashMap<>();
            response.put("id_Factura", factura.getId_Factura());
            response.put("Num_Serie", factura.getNum_Serie());
            response.put("name", factura.getName());
            response.put("Folio", factura.getFolio());
            response.put("UUID", factura.getUUID());
            response.put("Base64", factura.getBase64());
            response.put("Fecha_Emision", factura.getFecha_Emision());
            response.put("Fecha_Timbrado", factura.getFecha_Timbrado());
            response.put("id_Cliente", factura.getId_Cliente());
            response.put("id_Usuario", factura.getId_Usuario());
            response.put("Total", factura.getTotal());
            response.put("SubTotal", factura.getSubTotal());
            response.put("IVA", factura.getIVA());
            return response;
        });
    }

//    @GetMapping("/usuario/{id}")
//    public Flux<FacturasEmitidas> getFacturasByUsuarioId(@PathVariable String id) {
//        return facturasEmitidasRepository.findById_Usuario(id);
//    }
}