package br.com.alura.screenmatch.service;

public interface IConverteDados { // criando uma interface que deve ser implementada para converter dados usando generics " <> "
    <T> T obterDados(String json, Class<T> classe);
}
