package br.com.gft.controllers;

import br.com.gft.services.FilialService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/filiais")
public class FilialController {

    private final FilialService filialService;

    public FilialController(FilialService filialService) {
        this.filialService = filialService;
    }

    @GetMapping
    public ResponseEntity<Page<ConsultaFilialDTO>> buscarTodasAsFiliais(@PageableDefault Pageable pageable){

        return ResponseEntity.ok(filialService.listarTodasAsFiliais(pageable).map(FilialMapper::fromEntity));


    }

    @PostMapping
    public ResponseEntity<ConsultaFilialDTO> salvarFilial(@RequestBody RegistroFilialDTO dto){

        Filial filial = filialService.salvarFilial(FilialMapper.fromDTO(dto));

        return ResponseEntity.ok(FilialMapper.fromEntity(filial));

    }

    @GetMapping("{id}") // localhost:8080/v1/filiais/2
    public ResponseEntity<ConsultaFilialDTO> buscarFilial(@PathVariable Long id){

        Filial filial = filialService.buscarFilial(id);

        return ResponseEntity.ok(FilialMapper.fromEntity(filial));


    }

    @PutMapping("{id}")
    public ResponseEntity<ConsultaFilialDTO> alterarFilial(@RequestBody RegistroFilialDTO dto,
                                                           @PathVariable Long id){

        try {

            Filial filial = filialService.atualizarFilial(FilialMapper.fromDTO(dto), id);

            return ResponseEntity.ok(FilialMapper.fromEntity(filial));
        }catch(RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ConsultaFilialDTO> excluirFilial(@PathVariable Long id){

        try {
            filialService.excluirFilial(id);

            return ResponseEntity.ok().build();
        }catch(RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }

    }
}
