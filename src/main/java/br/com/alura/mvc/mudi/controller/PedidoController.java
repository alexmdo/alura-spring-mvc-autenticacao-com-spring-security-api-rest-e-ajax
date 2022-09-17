package br.com.alura.mvc.mudi.controller;

import javax.validation.Valid;

import br.com.alura.mvc.mudi.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("pedido")
public class PedidoController {
	
	private final PedidoRepository pedidoRepository;

	private final UserRepository userRepository;

	public PedidoController(PedidoRepository pedidoRepository, UserRepository userRepository) {
		this.pedidoRepository = pedidoRepository;
		this.userRepository = userRepository;
	}

	@GetMapping("formulario")
	public String formulario(RequisicaoNovoPedido requisicao) {
		return "pedido/formulario";
	}
	
	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result) {
		if(result.hasErrors()) {
			return "pedido/formulario";
		}
		
		Pedido pedido = requisicao.toPedido();
		pedido.setUser(userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
		pedidoRepository.save(pedido);
		
		return "redirect:/home";
	}
	
}
