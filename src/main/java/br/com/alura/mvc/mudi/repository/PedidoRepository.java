package br.com.alura.mvc.mudi.repository;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Cacheable("pedidos")
	List<Pedido> findByStatus(StatusPedido status, Pageable pageable);

	List<Pedido> findByUserUsername(String username);

	List<Pedido> findByStatusAndUserUsername(StatusPedido status, String username);
	
}