package br.com.medralservicosrio.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.medralservicosrio.conexao.ConnectionFactory;
import br.com.medralservicosrio.relatorios.RelatorioAdmIndividual;

/*
 * Desenvolvido por: Marcos Vinicius Cabral Annunciação Pinho
 * 
 * 
 * Classe responsável por fazer a persistência de todos os relatórios.
 * 
 * 
 */

public class RelatorioDao {

	public List<RelatorioAdmIndividual> listaRelatorioAdmIndividual(
			Integer idFunc) throws Exception {

		List<RelatorioAdmIndividual> lista = new ArrayList<RelatorioAdmIndividual>();

		// String sql
		// ="select p.produto,p.durabilidade,i.data,i.rastreabilidade,i.qntd,i.status,i.tipo_req,m.tipo_mov from produtos as p inner join individual as i on p.idproduto=i.idproduto inner join funcionarios as f on i.chapa=f.chapa inner join moviment as m on m.idproduto=p.produto;";
		// String sql =
		// "select p.produto,p.durabilidade,i.tipo_req,i.status,i.qntd,i.data from individual as i inner join produtos as p on i.idproduto=p.idproduto;";

		String sql = "select p.produto,p.durabilidade,i.qntd,i.status,i.tipo_req,i.rastreabilidade,i.data,m.tipo_mov from produtos as p inner join individual as i on p.idproduto=i.idproduto inner join funcionarios as f on i.chapa=f.chapa inner join moviment as m on m.idproduto=p.produto where f.chapa=?;";

		ConnectionFactory.ps = ConnectionFactory.getAbrirConexao()
				.prepareStatement(sql);
		((PreparedStatement) ConnectionFactory.ps).setInt(1, idFunc);

		ConnectionFactory.res = ((PreparedStatement) ConnectionFactory.ps)
				.executeQuery();

		while (((ResultSet) ConnectionFactory.res).next()) {

			RelatorioAdmIndividual relatorio = new RelatorioAdmIndividual();

			relatorio.setProduto(((ResultSet) ConnectionFactory.res)
					.getString("produto"));
			relatorio
					.setDurabilidadeProduto(((ResultSet) ConnectionFactory.res)
							.getInt("durabilidade"));
			relatorio
					.setDataEntrega((java.util.Date) ((ResultSet) ConnectionFactory.res)
							.getDate("data"));
			relatorio
					.setRastreabilidade((int) ((ResultSet) ConnectionFactory.res)
							.getDouble("rastreabilidade"));
			relatorio.setQuantidade(((ResultSet) ConnectionFactory.res)
					.getInt("qntd"));
			relatorio.setStatus(((ResultSet) ConnectionFactory.res)
					.getString("status"));
			relatorio.setTipoRequisicao(((ResultSet) ConnectionFactory.res)
					.getString("tipo_req"));
			relatorio.setMovimentacao(((ResultSet) ConnectionFactory.res)
					.getInt("tipo_mov"));

			lista.add(relatorio);

		}

		ConnectionFactory.getAbrirConexao();

		return lista;

	}

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);

		System.out.println("Codigo: ");
		Integer codigo = new Integer(sc.nextLine());

		List<RelatorioAdmIndividual> lista = new RelatorioDao()
				.listaRelatorioAdmIndividual(codigo);

		for (RelatorioAdmIndividual r : lista) {

			System.out.println("Produto: " + r.getProduto()
					+ " - Durabilidade:" + r.getDurabilidadeProduto());
			System.out
					.println("Requisição: " + r.getTipoRequisicao()
							+ " - Status: " + r.getStatus() + " - Quantidade: "
							+ r.getQuantidade() + " - Rastreabilidade: "
							+ r.getRastreabilidade() + " - Data: "
							+ r.getDataEntrega());
			System.out.println("Movimentação: " + r.getMovimentacao());

		}

	}

}
