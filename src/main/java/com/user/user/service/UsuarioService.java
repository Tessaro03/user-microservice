package com.user.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.user.user.dtos.AutenticacaoDTO;
import com.user.user.dtos.CadastraUsuarioDTO;
import com.user.user.dtos.TokenDTO;
import com.user.user.infra.security.TokenService;
import com.user.user.model.Usuario;
import com.user.user.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;


    public  TokenDTO efetuarLogin(AutenticacaoDTO dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(token);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return new TokenDTO(tokenJWT);
    }

    public void cadastrarUsuario(CadastraUsuarioDTO dto){
        var usuario = new Usuario(dto);
        repository.save(usuario);
    }

}
