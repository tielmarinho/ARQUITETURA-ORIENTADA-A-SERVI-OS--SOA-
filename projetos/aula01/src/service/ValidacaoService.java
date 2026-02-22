package service;

public class ValidacaoService {

    public boolean validar(int numero, int tentativa) {
        return numero == tentativa;
    }
}