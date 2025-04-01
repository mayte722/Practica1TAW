package com.example.estudianteapi.service;

import com.example.estudianteapi.dto.EstudianteDTO;
import com.example.estudianteapi.model.Estudiante;
import com.example.estudianteapi.repository.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstudianteService {
    private final EstudianteRepository estudianteRepository;

    public EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = mapToEntity(estudianteDTO);
        Estudiante nuevoEstudiante = estudianteRepository.save(estudiante);
        return mapToDTO(nuevoEstudiante);
    }

    public List<EstudianteDTO> obtenerTodosEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        return estudiantes.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public EstudianteDTO obtenerEstudiantePorId(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        return mapToDTO(estudiante);
    }

    public EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        estudiante.setNombre(estudianteDTO.getNombre());
        estudiante.setApellido(estudianteDTO.getApellido());
        estudiante.setEmail(estudianteDTO.getEmail());
        estudiante.setEdad(estudianteDTO.getEdad());

        Estudiante estudianteActualizado = estudianteRepository.save(estudiante);
        return mapToDTO(estudianteActualizado);
    }

    public void eliminarEstudiante(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        estudianteRepository.delete(estudiante);
    }

    private EstudianteDTO mapToDTO(Estudiante estudiante) {
        return EstudianteDTO.builder()
                .id(estudiante.getId())
                .nombre(estudiante.getNombre())
                .apellido(estudiante.getApellido())
                .email(estudiante.getEmail())
                .edad(estudiante.getEdad())
                .build();
    }

    private Estudiante mapToEntity(EstudianteDTO estudianteDTO) {
        return Estudiante.builder()
                .id(estudianteDTO.getId())
                .nombre(estudianteDTO.getNombre())
                .apellido(estudianteDTO.getApellido())
                .email(estudianteDTO.getEmail())
                .edad(estudianteDTO.getEdad())
                .build();
    }
}