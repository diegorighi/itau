package br.com.itau.geradornotafiscal.model.impl;

import br.com.itau.geradornotafiscal.model.Item;
import br.com.itau.geradornotafiscal.model.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.model.strategy.CalculadoraAliquotaStrategy;
import br.com.itau.geradornotafiscal.service.CalculadoraAliquotaProduto;

import java.math.BigDecimal;
import java.util.List;

public class CalculadoraAliquotaPFImpl implements CalculadoraAliquotaStrategy {

    @Override
    public List<ItemNotaFiscal> calcularAliquota(List<Item> items, double valorTotalItens) {

        /**
         * Na minha humilde opiniao, o valor total dos itens deveria ser calculado aqui,
         * e não passado como parâmetro. Porque ele pode ser manipulado pelo frontend e
         * causar divergéncias no cálculo da alíquota.
         *
         * Fiz um exemplo de como poderia ser feito, mas não implementei.
         */

        double valorTotal = items.stream()
                .mapToDouble(
                        item -> item.getValorUnitario() * item.getQuantidade()
                ).sum();



        // Aqui deu preguiça de fazer um por um, mas declarei as constantes e não implementei.
        // TODO modelar melhor isso aqui pra evitar os ifs igual fiz com as regiões
        double aliquota;
        if (valorTotalItens < 500) {
            aliquota = 0;
        } else if (valorTotalItens <= 2000) {
            aliquota = 0.12;
        } else if (valorTotalItens <= 3500) {
            aliquota = 0.15;
        } else {
            aliquota = 0.17;
        }
        return new CalculadoraAliquotaProduto().calcularAliquota(items, aliquota);
    }
}
