package br.com.fiap.oficina.model;

import br.com.fiap.oficina.dao.VeiculoDao;

public class app2 {

	public static void main(String[] args) {
		
		VeiculoDao dao = new VeiculoDao();
		try {
			Veiculo carro = dao.recupera(1);
			System.out.println(carro.getPlaca());
			System.out.println(carro.getProprietario().getNome());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
