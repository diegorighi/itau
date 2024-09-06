package br.com.itau.geradornotafiscal.service.impl;

import br.com.itau.geradornotafiscal.model.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.model.NotaFiscal;
import br.com.itau.geradornotafiscal.model.Pedido;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class NotaFiscalService {

    public NotaFiscal criarNotaFiscal(Pedido pedido, double valorFreteComPercentual,
                                      List<ItemNotaFiscal> itemNotaFiscalList) {
        String idNotaFiscal = UUID.randomUUID().toString();

        return NotaFiscal.builder()
                .idNotaFiscal(idNotaFiscal)
                .data(LocalDateTime.now())
                .valorTotalItens(pedido.getValorTotalItens())
                .valorFrete(valorFreteComPercentual)
                .itens(itemNotaFiscalList)
                .destinatario(pedido.getDestinatario())
                .build();
    }

}
