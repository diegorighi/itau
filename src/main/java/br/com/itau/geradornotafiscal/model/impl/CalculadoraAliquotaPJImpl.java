package br.com.itau.geradornotafiscal.model.impl;

import br.com.itau.geradornotafiscal.model.Item;
import br.com.itau.geradornotafiscal.model.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.model.RegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.model.strategy.CalculadoraAliquotaStrategy;
import br.com.itau.geradornotafiscal.service.CalculadoraAliquotaProduto;

import java.util.List;

public class CalculadoraAliquotaPJImpl implements CalculadoraAliquotaStrategy {

    private RegimeTributacaoPJ regimeTributacao;

    public CalculadoraAliquotaPJImpl(RegimeTributacaoPJ regimeTributacao) {
        this.regimeTributacao = regimeTributacao;
    }

    @Override
    public List<ItemNotaFiscal> calcularAliquota(List<Item> items, double valorTotalItens) {
        double aliquota;
        if (regimeTributacao == RegimeTributacaoPJ.SIMPLES_NACIONAL) {
            if (valorTotalItens < 1000) {
                aliquota = 0.03;
            } else if (valorTotalItens <= 2000) {
                aliquota = 0.07;
            } else if (valorTotalItens <= 5000) {
                aliquota = 0.13;
            } else {
                aliquota = 0.19;
            }
        } else if (regimeTributacao == RegimeTributacaoPJ.LUCRO_REAL) {
            if (valorTotalItens < 1000) {
                aliquota = 0.03;
            } else if (valorTotalItens <= 2000) {
                aliquota = 0.09;
            } else if (valorTotalItens <= 5000) {
                aliquota = 0.15;
            } else {
                aliquota = 0.20;
            }
        } else { // LUCRO_PRESUMIDO
            if (valorTotalItens < 1000) {
                aliquota = 0.03;
            } else if (valorTotalItens <= 2000) {
                aliquota = 0.09;
            } else if (valorTotalItens <= 5000) {
                aliquota = 0.16;
            } else {
                aliquota = 0.20;
            }
        }
        return new CalculadoraAliquotaProduto().calcularAliquota(items, aliquota);
    }
}
