package com.project.pokemon.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Setter
@Getter
public class SignupDto {
    private String email;
    private String nickname;
    private String password;
}
