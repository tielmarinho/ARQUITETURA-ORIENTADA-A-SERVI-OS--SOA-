package ui;

import application.GameService;
import service.NumeroService;
import service.ValidacaoService;
import service.ScoreService;

import java.util.Scanner;

public class ConsoleUI {

    public static void main(String[] args) {

        NumeroService numeroService = new NumeroService();
        ValidacaoService validacaoService = new ValidacaoService();
        ScoreService scoreService = new ScoreService();

        GameService gameService = new GameService(
                numeroService,
                validacaoService,
                scoreService
        );

        Scanner scanner = new Scanner(System.in);

        boolean continuar = true;

        while (continuar) {

            System.out.println("Digite um número de 1 a 6:");
            int tentativa = scanner.nextInt();

            boolean resultado = gameService.jogar(tentativa);

            if (resultado) {
                System.out.println("Acertou!");
            } else {
                System.out.println("Errou!");
            }

            System.out.println("Pontuação atual: " + gameService.getPontuacao());

            System.out.println("Deseja continuar? (s/n)");
            continuar = scanner.next().equalsIgnoreCase("s");
        }
    }
}