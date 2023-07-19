package br.com.alurafood.ordered.amqp;

import br.com.alurafood.ordered.dto.PaymentDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {

    @RabbitListener(queues = "payments.details-order")
    public void receiveMessage(PaymentDTO pagamentoDto) {
        String message = """
                Dados do pagamento: %s
                Número do pedido: %s
                Valor R$: %s
                Status: %s
                """.formatted(pagamentoDto.getId(),
                pagamentoDto.getOrderId(),
                pagamentoDto.getValue(),
                pagamentoDto.getStatus());

        System.out.println("I got the message: " + message);
    }
}
