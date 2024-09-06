package br.com.itau.geradornotafiscal.model.chain;

import br.com.itau.geradornotafiscal.model.NotaFiscal;
import br.com.itau.geradornotafiscal.model.Pedido;

public interface NotaFiscalHandler {

    void setNextHandler(NotaFiscalHandler nextHandler);
    void handle(Pedido pedido, NotaFiscal notaFiscal);

}
