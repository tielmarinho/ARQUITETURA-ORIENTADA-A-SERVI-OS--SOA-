package service;

import java.util.Random;

public class NumeroService {

    private Random random = new Random();

    public int gerarNumero() {
        return random.nextInt(6) + 1;
    }
}