package br.com.alurafood.ordered.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDTO {

    private Long id;
    private BigDecimal value;
    private String name;
    private String number;
    private String expiration;
    private String code;
    private StatusPayment status;
    private Long paymentFormId;
    private Long orderId;

}
