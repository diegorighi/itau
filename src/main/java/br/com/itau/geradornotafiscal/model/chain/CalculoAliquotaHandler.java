package br.com.itau.geradornotafiscal.model.chain;

import br.com.itau.geradornotafiscal.model.*;
import br.com.itau.geradornotafiscal.model.impl.CalculadoraAliquotaPFImpl;
import br.com.itau.geradornotafiscal.model.impl.CalculadoraAliquotaPJImpl;
import br.com.itau.geradornotafiscal.model.strategy.CalculadoraAliquotaStrategy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class CalculoAliquotaHandler implements NotaFiscalHandler{

    private NotaFiscalHandler nextHandler;

    @Override
    public void setNextHandler(NotaFiscalHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle(Pedido pedido, NotaFiscal notaFiscal) {
        Destinatario destinatario = pedido.getDestinatario();
        TipoPessoa tipoPessoa = destinatario.getTipoPessoa();
        CalculadoraAliquotaStrategy calculadoraAliquotaStrategy;

        if (tipoPessoa == TipoPessoa.FISICA) calculadoraAliquotaStrategy = new CalculadoraAliquotaPFImpl();
        else calculadoraAliquotaStrategy = new CalculadoraAliquotaPJImpl(destinatario.getRegimeTributacao());

        List<ItemNotaFiscal> itemNotaFiscalList = calculadoraAliquotaStrategy.calcularAliquota(pedido.getItens(), pedido.getValorTotalItens());
        notaFiscal.setItens(itemNotaFiscalList);

        if (nextHandler != null) {
            nextHandler.handle(pedido, notaFiscal);
        }
    }

}
