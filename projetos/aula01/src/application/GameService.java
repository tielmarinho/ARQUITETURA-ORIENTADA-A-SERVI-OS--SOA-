package application;

import service.NumeroService;
import service.ValidacaoService;
import service.ScoreService;

public class GameService {

    private NumeroService numeroService;
    private ValidacaoService validacaoService;
    private ScoreService scoreService;

    public GameService(NumeroService numeroService,
                       ValidacaoService validacaoService,
                       ScoreService scoreService) {

        this.numeroService = numeroService;
        this.validacaoService = validacaoService;
        this.scoreService = scoreService;
    }

    public boolean jogar(int tentativa) {

        int numero = numeroService.gerarNumero();

        boolean resultado = validacaoService.validar(numero, tentativa);

        if (resultado) {
            scoreService.adicionarPonto();
        }

        return resultado;
    }

    public int getPontuacao() {
        return scoreService.getPontos();
    }
}