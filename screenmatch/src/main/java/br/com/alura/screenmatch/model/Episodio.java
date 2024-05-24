package br.com.alura.screenmatch.model;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Episodio {
    private Integer temporada;
    private Integer numeroEpisodio;
    private String titulo;
    private Double avaliacao;
    private LocalDate dataLancamento;

    public Episodio(Integer numero, DadosEpisodio e) {
        this.temporada = numero;
        this.numeroEpisodio = e.numeroEpisodio();
        this.titulo = e.titulo();

        try {
            this.avaliacao = Double.valueOf(e.avaliacao());
        } catch (NumberFormatException ex) {
                this.avaliacao = 0.0;
        }

        try {
            this.dataLancamento = LocalDate.parse(e.dataLancamento());
        } catch (DateTimeException ex) {
            this.dataLancamento = null;
        }

    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    @Override
    public String toString() {
        return "temporada: " + this.temporada +
                ", numeroEpisodio: " + this.numeroEpisodio +
                ", titulo: '" + this.titulo + '\'' +
                ", avaliacao: " + this.avaliacao +
                ", dataLancamento: " + this.dataLancamento ;
    }
}
