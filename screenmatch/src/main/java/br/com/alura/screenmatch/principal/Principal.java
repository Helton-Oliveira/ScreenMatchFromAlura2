package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitor = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=dc4c4eb4";


    public void exibeMenu() {
        System.out.println("Digite o nome da série para buasca: ");
        var nomeSerie = leitor.nextLine();
        var  json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        var dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i <= dados.totaTemporadas(); i++) {
			json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
			var dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);

			temporadas.forEach(System.out::println);
		}

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println("\n Top 5 episódios: ");
        episodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episodio> episodioList = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(e -> new Episodio(t.numero(), e))
                ).collect(Collectors.toList());

        episodioList.forEach(System.out::println);

        System.out.println("A partir de que ano você deseja ver os episódios?");
        var ano = leitor.nextInt();
        leitor.nextLine();

        LocalDate dataBusca = LocalDate.of(ano, 1, 1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");

        episodioList.stream()
                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getTemporada() +
                        " Episódio: " + e.getTitulo() +
                        " Data Lançamento: " + e.getDataLancamento().format(formatter)
                ));
    }
}
