package com.E4.cosmos_sql.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Container(containerName = "FacturasEmitidas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class FacturasEmitidas {
    @Id
    @GeneratedValue
    private String id_Factura;
    private String Num_Serie;
    private String Folio;
    private String UUID;
    private String Base64;
    private Date Fecha_Emision;
    private Date Fecha_Timbrado;
}