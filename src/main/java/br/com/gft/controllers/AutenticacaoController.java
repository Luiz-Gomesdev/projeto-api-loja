package br.com.gft.controllers;

import br.com.gft.dto.auth.AutenticacaoDTO;
import br.com.gft.dto.auth.TokenDTO;
import br.com.gft.services.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth")
public class AutenticacaoController {

    @Autowired
    AutenticacaoService autenticacaoService;

    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody AutenticacaoDTO authForm){

        try {
            return ResponseEntity.ok(autenticacaoService.autenticar(authForm));
        }catch(AuthenticationException ae) {
            ae.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }



    }
}
