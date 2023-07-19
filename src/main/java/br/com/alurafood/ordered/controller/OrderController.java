package br.com.alurafood.ordered.controller;

import br.com.alurafood.ordered.dto.OrderDTO;
import br.com.alurafood.ordered.dto.StatusDTO;
import br.com.alurafood.ordered.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ordered")
public class OrderController {

        @Autowired
        private OrderService service;

        @GetMapping()
        public List<OrderDTO> findAll() {
            return service.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<OrderDTO> findById(@PathVariable @NotNull Long id) {
            OrderDTO dto = service.findById(id);

            return  ResponseEntity.ok(dto);
        }

        @GetMapping("/port")
        public String returnPort(@Value("${local.server.port}") String port){
            return String.format("Requisição respondida pela instância executando na porta %s", port);
        }

        @PostMapping()
        public ResponseEntity<OrderDTO> makeOrder(@RequestBody @Valid OrderDTO dto, UriComponentsBuilder uriBuilder) {
            OrderDTO orderDTO = service.createOrder(dto);

            URI uri = uriBuilder.path("/ordered/{id}").buildAndExpand(orderDTO.getId()).toUri();

            return ResponseEntity.created(uri).body(orderDTO);

        }

        @PutMapping("/{id}/status")
        public ResponseEntity<OrderDTO> updateStatus(@PathVariable Long id, @RequestBody StatusDTO status){
           OrderDTO dto = service.updateStatus(id, status);

            return ResponseEntity.ok(dto);
        }


        @PutMapping("/{id}/paid")
        public ResponseEntity<Void> approvePayment(@PathVariable @NotNull Long id) {
            service.approvePaymentOrder(id);

            return ResponseEntity.ok().build();

        }
}
