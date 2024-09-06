package br.com.itau.geradornotafiscal.model.factory;

import br.com.itau.geradornotafiscal.model.Regiao;

import br.com.itau.geradornotafiscal.model.strategy.FreteStrategy;
import br.com.itau.geradornotafiscal.model.impl.FreteCentroOesteStrategy;
import br.com.itau.geradornotafiscal.model.impl.FreteNorteStrategy;
import br.com.itau.geradornotafiscal.model.impl.FreteNordesteStrategy;
import br.com.itau.geradornotafiscal.model.impl.FreteSudesteStrategy;
import br.com.itau.geradornotafiscal.model.impl.FreteSulStrategy;

public class FreteStrategyFactory {

    public static FreteStrategy getFreteStrategy(Regiao regiao) {
        switch (regiao) {
            case NORTE:
                return new FreteNorteStrategy();
            case NORDESTE:
                return new FreteNordesteStrategy();
            case CENTRO_OESTE:
                return new FreteCentroOesteStrategy();
            case SUDESTE:
                return new FreteSudesteStrategy();
            case SUL:
                return new FreteSulStrategy();
            default:
                throw new IllegalArgumentException("Região desconhecida: " + regiao);
        }
    }

}
