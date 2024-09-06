package br.com.itau.geradornotafiscal.model.strategy;

import br.com.itau.geradornotafiscal.model.Item;
import br.com.itau.geradornotafiscal.model.ItemNotaFiscal;

import java.util.List;

public interface CalculadoraAliquotaStrategy {

    List<ItemNotaFiscal> calcularAliquota(List<Item> items, double valorTotalItens);


}
