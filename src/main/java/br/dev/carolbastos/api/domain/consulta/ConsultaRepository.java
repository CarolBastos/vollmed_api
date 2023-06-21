package br.dev.carolbastos.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

//    Boolean existsByPacienteIdAndDataBetween(Long idMedico, LocalDateTime data);

    Boolean existsByMedicoIdAndDataBetween(Long id, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
