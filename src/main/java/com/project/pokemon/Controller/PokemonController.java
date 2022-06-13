package com.project.pokemon.Controller;

import com.project.pokemon.Service.PokemonService;
import com.project.pokemon.model.entity.Pokemon;
import com.project.pokemon.model.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PokemonController {

    private final PokemonRepository pokemonRepository;
    private final PokemonService pokemonService;

    //메인페이지 로드
    @Controller
    class MainViewController {

        @GetMapping("/")
        public String main(Model model, @PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
            if(Optional.ofNullable(userDetails).isPresent()){
                // nickname 값에 닉네임을 할당해줍니다, 게스트는 guest로 할당
                model.addAttribute("nickname",userDetails.getNickname());
            }
            else {
                model.addAttribute("nickname","guest");
            }
            return "index";
        }
    }

    //디테일페이지 로드
    @Controller
    class DetailViewController {

        @GetMapping("/detail/{pokemonId}")
        public String detail(Model model, @PathVariable Long pokemonIdid, @AuthenticationPrincipal UserDetailsImpl userDetails) {
            if(Optional.ofNullable(userDetails).isPresent()){
                model.addAttribute("nickname",userDetails.getNickname());
                model.addAttribute("pokemon", pokemonRepository.findById(pokemonIdid).orElseThrow(() -> new IllegalArgumentException("존재하지않는 포켓몬입니다")));
            }
            else {
                model.addAttribute("nickname","guest");
                model.addAttribute("pokemon", pokemonRepository.findById(pokemonIdid).orElseThrow(() -> new IllegalArgumentException("존재하지않는 포켓몬입니다")));
            }
            return "detail";
        }
    }

    @GetMapping("/")
    public List<Pokemon> readPokemon() {
        return pokemonRepository.findAllByOrderByID();
    }
}
