package com.user.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.user.user.model.Usuario;

public interface UsuarioRepository extends  JpaRepository<Usuario, Long>{

    UserDetails findByLogin(String email);    
    
}
