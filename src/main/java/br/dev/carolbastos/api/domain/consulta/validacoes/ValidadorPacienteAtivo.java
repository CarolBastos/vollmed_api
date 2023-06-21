package br.dev.carolbastos.api.domain.consulta.validacoes;

import br.dev.carolbastos.api.domain.ValidacaoException;
import br.dev.carolbastos.api.domain.consulta.DadosAgendamentoConsulta;
import br.dev.carolbastos.api.domain.medico.MedicoRepository;
import br.dev.carolbastos.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo  implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados){
        if(dados.idMedico() == null){
            return;
        }

        var pacienteEstaAtivo = pacienteRepository.findAtivoById(dados.idPaciente());

        if(!pacienteEstaAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluido");
        }
    }
}
