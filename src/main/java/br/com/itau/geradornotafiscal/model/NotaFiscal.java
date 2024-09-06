package br.com.itau.geradornotafiscal.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Vector;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Component
@Scope("prototype")
public class NotaFiscal {
    @JsonProperty("id_nota_fiscal")
    private String idNotaFiscal;

    @JsonProperty("data")
    private LocalDateTime data;

    @JsonProperty("valor_total_itens")
    private double valorTotalItens;

    @JsonProperty("valor_frete")
    private double valorFrete;

    @JsonProperty("itens")
    private List<ItemNotaFiscal> itens = new Vector<>();

    @JsonProperty("destinatario")
    private Destinatario destinatario;

}