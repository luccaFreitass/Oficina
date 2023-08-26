package br.com.fiap.oficina.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.fiap.oficina.model.Pessoa;
import br.com.fiap.oficina.model.Veiculo;

public class VeiculoDao {
	
	public void salva(Veiculo v) throws Exception {
		String sql = "INSERT INTO TBL_VEICULO(placa, marca, modelo, ano, proprietario_id) "
									+ "VALUES (?, ?, ?, ?, ?)";
		
		try(Connection con = new ConnectionFactory().getConexao();
			PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"id"})) {
		
			pstmt.setString(1, v.getPlaca());
			pstmt.setString(2, v.getMarca());
			pstmt.setString(3, v.getModelo());
			pstmt.setInt(4, v.getAno());
			pstmt.setLong(5, v.getProprietario().getId());
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				v.setId(rs.getBigDecimal(1).longValue());
			}			
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void altera(Veiculo v) throws Exception {
		String sql = "update tbl_veiculo set placa=?, marca=?, modelo=?, ano=? where id=?";
		try(Connection con = new ConnectionFactory().getConexao();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, v.getPlaca());
			pstmt.setString(2, v.getMarca());
			pstmt.setString(3, v.getModelo());
			pstmt.setInt(4, v.getAno());
			pstmt.setLong(5, v.getId());
			
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}		
	}
	
	public void deleta(long id) throws Exception {
		
		String sql = "delete from tbl_veiculo where id = ?";
		
		try(Connection con = new ConnectionFactory().getConexao();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	public Veiculo recuperaPelaPlaca(String placa) throws Exception {
		
		String sql = "select v.id, v.placa, v.marca, v.modelo, v.ano, v.proprietario_id, "
				+ "p.nome from tbl_veiculo v join tbl_pessoa p "
				+ "on v.proprietario_id=p.id where v.placa = ?";
		
		Veiculo v = null;
		try (Connection con = new ConnectionFactory().getConexao();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1, placa);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				v = new Veiculo();
				v.setId(rs.getLong("id"));
				v.setPlaca(rs.getString("placa"));
				v.setMarca(rs.getString("marca"));
				v.setModelo(rs.getString("modelo"));
				v.setAno(rs.getInt("ano"));
				
				Pessoa p = new Pessoa();
				p.setId(rs.getLong("proprietario_id"));
				p.setNome(rs.getString("nome"));
				
				v.setProprietario(p);				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return v;
	}
	
	public Veiculo recupera(long id) throws Exception {
		
		String sql = "select v.placa, v.marca, v.modelo, v.ano, v.proprietario_id, "
				+ "p.nome from tbl_veiculo v join tbl_pessoa p "
				+ "on v.proprietario_id=p.id where v.id = ?";
		
		
		Veiculo v = null;
		
		try(Connection con = new ConnectionFactory().getConexao();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setLong(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				v = new Veiculo();
				v.setId(id);
				v.setPlaca(rs.getString("placa"));
				v.setMarca(rs.getString("marca"));
				v.setModelo(rs.getString("modelo"));
				v.setAno(rs.getInt("ano"));
				
				Pessoa p = new Pessoa();
				p.setId(rs.getLong("proprietario_id"));
				p.setNome(rs.getString("nome"));
				
				v.setProprietario(p);				
			}				
		}	
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return v;
	}
	

}
