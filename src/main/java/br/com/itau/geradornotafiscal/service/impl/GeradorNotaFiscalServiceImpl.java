package br.com.itau.geradornotafiscal.service.impl;

import br.com.itau.geradornotafiscal.model.NotaFiscal;
import br.com.itau.geradornotafiscal.model.Pedido;
import br.com.itau.geradornotafiscal.model.chain.NotaFiscalHandler;
import br.com.itau.geradornotafiscal.service.GeradorNotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class GeradorNotaFiscalServiceImpl implements GeradorNotaFiscalService {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private NotaFiscalHandler calculoAliquotaHandler;

    @Autowired
    private NotaFiscalHandler calculoFreteHandler;

    @Autowired
    private NotaFiscalHandler criacaoNotaFiscalHandler;

    @Override
    public NotaFiscal gerarNotaFiscal(Pedido pedido) {

        NotaFiscal notaFiscal = context.getBean(NotaFiscal.class);

        // Usei Chain of Responsibility e configurei a ordem de chamada da cadeia
        calculoAliquotaHandler.setNextHandler(calculoFreteHandler);
        calculoFreteHandler.setNextHandler(criacaoNotaFiscalHandler);

        // Executo a cadeia de responsabilidade chamando o primeiro handler
        calculoAliquotaHandler.handle(pedido, notaFiscal);

        // Vocês não prestam...
        new EstoqueService().enviarNotaFiscalParaBaixaEstoque(notaFiscal);
        new RegistroService().registrarNotaFiscal(notaFiscal);
        new EntregaService().agendarEntrega(notaFiscal);
        new FinanceiroService().enviarNotaFiscalParaContasReceber(notaFiscal);

        // Atualiza a nota fiscal com os dados do pedido e calcula o valor total
        atualizarNotaFiscal(notaFiscal, pedido);

        return notaFiscal;
    }

    private void atualizarNotaFiscal(NotaFiscal notaFiscal, Pedido pedido) {
        notaFiscal.setValorTotalItens(
            notaFiscal.getItens().stream()
                .mapToDouble(item -> item.getValorUnitario() * item.getQuantidade())
                .sum());

        notaFiscal.setDestinatario(pedido.getDestinatario());
    }
}