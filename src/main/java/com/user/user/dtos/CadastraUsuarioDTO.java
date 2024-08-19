package com.user.user.dtos;


import com.user.user.model.Tipo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastraUsuarioDTO(

    
    @NotBlank
    String nome,

    @NotBlank
    String login,

    @NotBlank
    @Email
    String email,

    @NotBlank
    String senha,

    @NotNull
    Tipo tipo,

    @NotBlank
    String documento

) {
    
}
