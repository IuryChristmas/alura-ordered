package br.com.alurafood.ordered.service;

import br.com.alurafood.ordered.dto.OrderDTO;
import br.com.alurafood.ordered.dto.StatusDTO;
import br.com.alurafood.ordered.model.Order;
import br.com.alurafood.ordered.model.Status;
import br.com.alurafood.ordered.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private final ModelMapper modelMapper;


    public List<OrderDTO> findAll() {
        return repository.findAll().stream()
                .map(p -> modelMapper.map(p, OrderDTO.class))
                .collect(Collectors.toList());
    }

    public OrderDTO findById(Long id) {
        Order pedido = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(pedido, OrderDTO.class);
    }

    public OrderDTO createOrder(OrderDTO dto) {
        Order order = modelMapper.map(dto, Order.class);

        order.setDateTime(LocalDateTime.now());
        order.setStatus(Status.REALIZADO);
        order.getItens().forEach(item -> item.setOrder(order));
        repository.save(order);

        return modelMapper.map(order, OrderDTO.class);
    }

    public OrderDTO updateStatus(Long id, StatusDTO dto) {

        Order order = repository.findByIdWithItens(id);

        if (order == null) {
            throw new EntityNotFoundException();
        }

        order.setStatus(dto.getStatus());
        repository.updateStatus(dto.getStatus(), order);
        return modelMapper.map(order, OrderDTO.class);
    }

    public void approvePaymentOrder(Long id) {

        Order order = repository.findByIdWithItens(id);

        if (order == null) {
            throw new EntityNotFoundException();
        }

        order.setStatus(Status.PAGO);
        repository.updateStatus(Status.PAGO, order);
    }
}
