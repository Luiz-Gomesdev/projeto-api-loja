package br.com.gft.services;

import br.com.gft.entities.*;
import br.com.gft.repositories.CompraRepository;
import br.com.gft.repositories.EstoqueRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final EstoqueService estoqueService;

    public CompraService(CompraRepository compraRepository, EstoqueService estoqueService) {
        this.compraRepository = compraRepository;
        this.estoqueService = estoqueService;
    }

    public Compra salvarCompra(Compra compra) {

        Usuario usuario = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        compra.setFilial(usuario.getFilial());

        compra.getItens()
                .stream()
                .forEach(i -> estoqueService.acrescentaQuantidadeEstoque(i.getProduto().getId(), compra.getFilial().getId(), i.getQuantidade()));


        return compraRepository.save(compra);
    }

    @Service
    public static class EstoqueService {

        private final EstoqueRepository estoqueRepository;

        public EstoqueService(EstoqueRepository estoqueRepository) {
            this.estoqueRepository = estoqueRepository;
        }

        public Integer acrescentaQuantidadeEstoque(Long produtoId, Long filialId, Integer quantidade) {

            Optional<Estoque> op_estoque = estoqueRepository.findByProduto_IdAndFilial_Id(produtoId, filialId);

            if(op_estoque.isPresent()) {

                Estoque estoque = op_estoque.get();
                estoque.setQuantidade(estoque.getQuantidade() + quantidade);

                return estoqueRepository.save(estoque).getQuantidade();
            }

            Estoque estoque = new Estoque();
            estoque.setProduto(new Produto(produtoId));
            estoque.setFilial(new Filial(filialId));
            estoque.setQuantidade(quantidade);

            return estoqueRepository.save(estoque).getQuantidade();

        }
    }
}
