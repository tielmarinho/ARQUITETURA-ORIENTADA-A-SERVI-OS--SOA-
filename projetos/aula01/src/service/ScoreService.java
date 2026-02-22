package service;

public class ScoreService {

    private int pontos = 0;

    public void adicionarPonto() {
        pontos++;
    }

    public int getPontos() {
        return pontos;
    }
}