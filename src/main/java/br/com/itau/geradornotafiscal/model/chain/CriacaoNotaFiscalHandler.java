package br.com.itau.geradornotafiscal.model.chain;

import br.com.itau.geradornotafiscal.model.NotaFiscal;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.service.impl.NotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CriacaoNotaFiscalHandler implements NotaFiscalHandler {

    @Autowired
    private NotaFiscalService notaFiscalService;

    private NotaFiscalHandler nextHandler;

    @Override
    public void setNextHandler(NotaFiscalHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle(Pedido pedido, NotaFiscal notaFiscal) {
        NotaFiscal novaNotaFiscal = notaFiscalService
                .criarNotaFiscal(
                        pedido,
                        notaFiscal.getValorFrete(),
                        notaFiscal.getItens()
                );
        notaFiscal.setIdNotaFiscal(novaNotaFiscal.getIdNotaFiscal());
        notaFiscal.setData(novaNotaFiscal.getData());

        if (nextHandler != null) {
            nextHandler.handle(pedido, notaFiscal);
        }
    }
}