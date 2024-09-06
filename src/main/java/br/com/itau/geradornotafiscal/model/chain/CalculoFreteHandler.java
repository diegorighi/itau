package br.com.itau.geradornotafiscal.model.chain;

import br.com.itau.geradornotafiscal.model.Destinatario;
import br.com.itau.geradornotafiscal.model.NotaFiscal;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.service.impl.CalculadoraFreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CalculoFreteHandler implements NotaFiscalHandler {

    @Autowired
    private CalculadoraFreteService calculadoraFreteService;

    private NotaFiscalHandler nextHandler;

    @Override
    public void setNextHandler(NotaFiscalHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle(Pedido pedido, NotaFiscal notaFiscal) {
        Destinatario destinatario = pedido.getDestinatario();
        double valorFrete = pedido.getValorFrete();
        double valorFreteComPercentual = calculadoraFreteService.calcularFrete(valorFrete, destinatario);
        notaFiscal.setValorFrete(valorFreteComPercentual);

        if (nextHandler != null) {
            nextHandler.handle(pedido, notaFiscal);
        }
    }
}
