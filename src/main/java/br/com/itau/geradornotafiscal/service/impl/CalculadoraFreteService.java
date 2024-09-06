package br.com.itau.geradornotafiscal.service.impl;

import br.com.itau.geradornotafiscal.model.Destinatario;
import br.com.itau.geradornotafiscal.model.Endereco;
import br.com.itau.geradornotafiscal.model.Finalidade;
import br.com.itau.geradornotafiscal.model.Regiao;
import br.com.itau.geradornotafiscal.model.factory.FreteStrategyFactory;
import br.com.itau.geradornotafiscal.model.strategy.FreteStrategy;
import org.springframework.stereotype.Service;

@Service
public class CalculadoraFreteService {

    public double calcularFrete(double valorFrete, Destinatario destinatario) {
        Regiao regiao = determinarRegiao(destinatario);
        FreteStrategy freteStrategy = FreteStrategyFactory.getFreteStrategy(regiao);
        return freteStrategy.calcularFrete(valorFrete);
    }

    private Regiao determinarRegiao(Destinatario destinatario) {
        return destinatario.getEnderecos().stream()
                .filter(
                        endereco -> endereco.getFinalidade() == Finalidade.ENTREGA ||
                                endereco.getFinalidade() == Finalidade.COBRANCA_ENTREGA)
                .map(Endereco::getRegiao)
                .findFirst()
                .orElse(null);
    }
}
