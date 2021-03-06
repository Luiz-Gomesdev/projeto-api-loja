package br.com.gft.controllers;

import br.com.gft.dto.usuario.ConsultaUsuarioDTO;
import br.com.gft.dto.usuario.RegistroUsuarioDTO;
import br.com.gft.dto.usuario.UsuarioMapper;
import br.com.gft.entities.Usuario;
import br.com.gft.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<ConsultaUsuarioDTO> salvarUsuario(@RequestBody RegistroUsuarioDTO dto){

        Usuario usuario = usuarioService.salvarUsuario(UsuarioMapper.fromDTO(dto));

        return ResponseEntity.ok(UsuarioMapper.fromEntity(usuario));

    }
}
