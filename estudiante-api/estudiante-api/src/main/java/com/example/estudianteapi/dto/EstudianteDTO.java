package com.example.estudianteapi.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstudianteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private Integer edad;
}