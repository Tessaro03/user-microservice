package com.user.user.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.user.user.dtos.CadastraUsuarioDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Email
    @Column(unique=true)
    private String email;
    
    @Column(unique=true)
    private String login;

    private String nome;

    private String senha;
    
    private String documento;
    
    private Tipo tipo;
    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (null == this.tipo) {
            return List.of(); // Sem permissões
        } else return switch (this.tipo) {
            case Cliente -> List.of(new SimpleGrantedAuthority("ROLE_CLIENTE"));
            case Loja -> List.of(new SimpleGrantedAuthority("ROLE_LOJA"));
            case Entregador -> List.of(new SimpleGrantedAuthority("ROLE_ENTREGADOR"));
            default -> List.of();
        }; // Sem permissões
            
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;

    }

    public Usuario(CadastraUsuarioDTO dto) {
        this.email = dto.email(); 
        this.login = dto.login();
        this.senha = new BCryptPasswordEncoder().encode(dto.senha());
        this.nome = dto.nome();
        this.tipo = dto.tipo();
        this.documento = dto.documento();
    }
    
}
