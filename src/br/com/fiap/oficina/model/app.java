package br.com.fiap.oficina.model;

import br.com.fiap.oficina.business.VeiculoBO;

public class app {

	public static void main(String[] args) {
		Pessoa p = new Pessoa();
		p.setId(1);
		
		Veiculo v = new Veiculo();
		v.setPlaca("PQP-6969");
		v.setMarca("BMW");
		v.setModelo("X1");
		v.setAno(2023);
		v.setProprietario(p);
		
		VeiculoBO bo = new VeiculoBO();
		try {
			bo.cadastraVeiculo(v);
			System.out.println("Gravou com sucesso!!" + v.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		

	}

}
