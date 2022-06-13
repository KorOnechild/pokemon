package com.project.pokemon.model.repository;

import com.project.pokemon.model.entity.Pokemon;

import java.util.List;
import java.util.Optional;

public interface PokemonRepository {
    List<Pokemon> findAllByOrderByID();

    List<Pokemon>findById(Long id);
}
