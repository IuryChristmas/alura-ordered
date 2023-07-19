package br.com.alurafood.ordered.dto;

import br.com.alurafood.ordered.model.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;
    private LocalDateTime dateTime;
    private Status status;
    private List<OrderItemDTO> itens = new ArrayList<>();



}
