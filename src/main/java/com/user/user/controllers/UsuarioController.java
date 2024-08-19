package com.user.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.user.dtos.AutenticacaoDTO;
import com.user.user.dtos.CadastraUsuarioDTO;
import com.user.user.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private UsuarioService service;


    @PostMapping("/login")
    public ResponseEntity efeturaLogin(@RequestBody @Valid AutenticacaoDTO dados){
        return ResponseEntity.ok(service.efetuarLogin(dados)); 
    }

    @PostMapping("/cadastrar")
    public void cadastrar(@RequestBody @Valid CadastraUsuarioDTO dados){
        service.cadastrarUsuario(dados);
    }

    @GetMapping("teste")
    public ResponseEntity getMethodName(HttpServletRequest request) {
        return ResponseEntity.ok("PASSOU");
    }
    
}
