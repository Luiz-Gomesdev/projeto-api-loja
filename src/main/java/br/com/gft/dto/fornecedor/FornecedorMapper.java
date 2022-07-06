package br.com.gft.dto.fornecedor;

import br.com.gft.dto.endereco.EnderecoMapper;
import br.com.gft.entities.Fornecedor;

public class FornecedorMapper {

    public static Fornecedor fromDTO(RegistroFornecedorDTO dto) {
        return new Fornecedor(null, dto.getCnpj(), dto.getNome(), dto.getTelefone(), dto.getEmail(),
                EnderecoMapper.fromDTO(dto.getEndereco()));
    }

    public static ConsultaFornecedorDTO fromEntity(Fornecedor fornecedor) {
        return new ConsultaFornecedorDTO(fornecedor.getId(), fornecedor.getCnpj(), fornecedor.getNome(),
                fornecedor.getTelefone(), fornecedor.getEmail(), EnderecoMapper.fromEntity(fornecedor.getEndereco()));
    }
}
