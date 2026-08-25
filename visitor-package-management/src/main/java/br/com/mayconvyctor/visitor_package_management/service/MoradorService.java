package br.com.mayconvyctor.visitor_package_management.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.mayconvyctor.visitor_package_management.model.Morador;
import br.com.mayconvyctor.visitor_package_management.repository.MoradorRepository;
import java.util.List;

import java.util.List;
import java.util.Optional;

@Service
public class MoradorService {
    private final MoradorRepository moradorRepository;

    public MoradorService(MoradorRepository moradorRepository){
        this.moradorRepository = moradorRepository;
    }

    @Transactional(readOnly = true)
    public List<Morador> listarTodos(){
        return moradorRepository.findAll();
    }

    @Transactional
    public Morador salvar(Morador morador){
        if(morador.getId() == null){
            Optional<Morador> moradorExistente = moradorRepository.findByCpf(morador.getCpf());
            if(moradorExistente.isPresent()){
                throw new RuntimeException("Morador com CPF " + morador.getCpf() + " já existe");
            }
        }
        return moradorRepository.save(morador);
    }

    @Transactional
    public void excluirPorId(Long id){
        if(!moradorRepository.existsById(id)){
            throw new IllegalArgumentException("Morador com ID " + id + " não encontrado para exclusão.");
        }
        moradorRepository.deleteById(id);
    }
}
