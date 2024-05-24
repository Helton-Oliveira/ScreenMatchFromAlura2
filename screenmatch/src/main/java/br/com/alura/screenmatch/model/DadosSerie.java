package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// criando um record para reconhecimento e modelagem dos dados recuperados da API (record sempre é utilizado apenas para representação de dados)
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String title, // utilizando jsonAlias para buscar o dado pelo nome e inferir em outro do qual o dev deseja usar
                         @JsonAlias ("totalSeasons") Integer totaTemporadas,
                         @JsonAlias("imdbRating") String avalicao) {
}
