package com.E4.cosmos_sql.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Container(containerName = "Clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clientes {
    @Id
    @GeneratedValue
    private String id_Cliente;
    private String Nombre_RazonSocial;
    private String RFC;
    private String Codigo_Postal;
    private String UsoCFDI;
    private String RegimenFiscal;
}