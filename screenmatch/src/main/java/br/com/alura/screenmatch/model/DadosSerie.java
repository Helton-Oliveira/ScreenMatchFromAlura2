package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String title, // utilizando jsonAlias para buscar o dado pelo nome e inferir em outro do qual o dev deseja usar
                         @JsonAlias ("totalSeasons") Integer totaTemporadas,
                         @JsonAlias("imdbRating") String avalicao) {
}
