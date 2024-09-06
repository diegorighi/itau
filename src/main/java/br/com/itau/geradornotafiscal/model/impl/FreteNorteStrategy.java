package br.com.itau.geradornotafiscal.model.impl;

import br.com.itau.geradornotafiscal.model.strategy.FreteStrategy;

public class FreteNorteStrategy implements FreteStrategy {

    @Override
    public double calcularFrete(double valorFrete) {
        return valorFrete * 1.08;
    }

}
