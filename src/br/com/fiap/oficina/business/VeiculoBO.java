package br.com.fiap.oficina.business;

import br.com.fiap.oficina.dao.VeiculoDao;
import br.com.fiap.oficina.model.Veiculo;

public class VeiculoBO {

	public void cadastraVeiculo(Veiculo carro) throws Exception{
		
		//RN 1 - não é permitido cadatrar 2 veiculos com a mesma placa
		VeiculoDao dao = new VeiculoDao();
		Veiculo v = dao.recuperaPelaPlaca(carro.getPlaca());
		if (v == null) {
			dao.salva(carro);
		}
		else {
			throw new Exception ("Ja existe um veiculo com a mesma placa");
		}
		
		
	}
	
}
