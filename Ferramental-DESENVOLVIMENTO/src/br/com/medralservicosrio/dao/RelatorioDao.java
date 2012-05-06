package br.com.medralservicosrio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.medralservicosrio.conexao.ConnectionFactory;
import br.com.medralservicosrio.generics.DAO;
import br.com.medralservicosrio.relatorios.RelatorioGerencialCompras;


public class RelatorioDao extends DAO{

	public RelatorioDao() {
	}
	
	private Connection connection;
	private PreparedStatement pstmt; 
	private ResultSet rs;
	
	 /*public List gerarRelatorioGerencialDeMateriais(String numNota, String nomeProduto,Date dataInicial, Date dataFinal) throws SQLException {
	 

		Query q = getConsulta(); 
		Session s = getSessao();
		q = s.createSQLQuery(getSQLGerarRelatorioGerencialDeCompras(numNota, nomeProduto, dataInicial, dataFinal)).setResultTransformer(Transformers.aliasToBean(RelatorioGerencialCompras.class)); 
		return q.list();
		
	 }

	 @SuppressWarnings("unused")
	private String getSQLGerarRelatorioGerencialDeCompras(String numNota, String nomeProduto,Date dataInicial, Date dataFinal) {
			StringBuffer query = new StringBuffer();
			query.append(" SELECT entrada.nota  AS NUM_NOTA,							\n");
			query.append("        p.produto 	AS NOME_PRODUTO,						\n");
			query.append("        sum(qntd) 	AS QTD,									\n");
			query.append("        sum(qntd*entrada.valor) AS VAL,						\n");
			query.append("        entrada.data AS DATA_ENTRADA							\n");
			query.append("   FROM entradas_via_nota entrada,							\n");
			query.append("        produtos p											\n");
			query.append("  WHERE p.idproduto = entrada.idprod							\n");
			
			if(!numNota.isEmpty()){
				query.append("    AND entrada.nota = ?						\n");
			}
			if(!nomeProduto.isEmpty()){
				query.append("    AND upper(p.produto) LIKE(upper(?))		\n");			
			}
			if((dataInicial != null) && (dataInicial != null)){
				query.append("    AND entrada.data   between STR_TO_DATE( ?, '%Y-%m-%d' ) AND STR_TO_DATE( ?, '%Y-%m-%d' )	\n");			
			}else if(dataInicial != null && dataInicial == null) {
				query.append("    AND entrada.data   > STR_TO_DATE( ?, '%Y-%m-%d' )	\n");
			}else if(dataInicial == null && dataInicial != null ){
				query.append("    AND entrada.data   < STR_TO_DATE( ?, '%Y-%m-%d' )	\n");
			}
			
			query.append("    group by NUM_NOTA,							\n");
			query.append("             NOME_PRODUTO,						\n");
			query.append("             DATA_ENTRADA							\n");
			query.append("    order by NUM_NOTA								\n");
			
			return query.toString();
		}*/
	 
	/**
	 * TODO Transforma em createSQLQuery 
	 */
	 public List gerarRelatorioGerencialDeMateriais(String nomeProduto) throws SQLException {

		 connection = ConnectionFactory.getConnection();
		 pstmt = connection.prepareStatement(getSQLGerarRelatorioGerencialDeMateriais(nomeProduto));
		 //pstmt.setString(1, "\'%"+  nomeProduto  +"%\'");
		 rs = pstmt.executeQuery();
		 List lista = new ArrayList();

		 while(rs.next()){
			 
			 RelatorioGerencialCompras relGerencialCompras = new RelatorioGerencialCompras();
			 relGerencialCompras.getProduto().setProduto(rs.getString("nome_produto"));
			 relGerencialCompras.getProduto().setValor(rs.getDouble("valor_produto"));
			 relGerencialCompras.setQtdTeste(rs.getInt("QTD_TESTE"));
			 relGerencialCompras.setQtdReformado(rs.getInt("QTD_REFORMA"));
			 relGerencialCompras.setQtdEstoque(rs.getInt("QTD_ESTOQUE"));
			 relGerencialCompras.setQtdFuncionando(rs.getInt("QTD_INDIVIDUAL"));
			 relGerencialCompras.setQtdVeiculo(rs.getInt("QTD_VEICULOS"));
			 lista.add(relGerencialCompras);
		 }
		 return lista;
	 }

	 private String getSQLGerarRelatorioGerencialDeMateriais(String nomeProduto) {
		 StringBuffer query = new StringBuffer();
		 query.append(" SELECT							\n");
		 query.append("    p.idproduto AS id,			\n");
		 query.append("    p.produto AS nome_produto,	\n");
		 query.append("    p.valor AS valor_produto,	\n");
		 query.append("    (select count(teste_eletrico.idproduto) from teste_eletrico where teste_eletrico.idproduto = p.idproduto) AS QTD_TESTE,	\n");
		 query.append("    (select count(reforma.idproduto) from reforma where reforma.idproduto = p.idproduto) AS QTD_REFORMA,						\n");
		 query.append("    (select count(estoque.idproduto) from estoque where estoque.idproduto = p.idproduto) AS QTD_ESTOQUE,						\n");
		 query.append("    (select count(individual.idproduto) from individual where individual.idproduto = p.idproduto) AS QTD_INDIVIDUAL,			\n");
		 query.append("    (select count(veiculos.idproduto) from veiculos where veiculos.idproduto = p.idproduto) AS QTD_VEICULOS					\n");
		 query.append(" from produtos p					\n");
		 //temporario
		 if(!nomeProduto.isEmpty()){
			query.append(" where upper(p.produto) LIKE (upper('%"+ nomeProduto  + "%'))		\n");
		 }
		 return query.toString();
	 }

	public List gerarRelatorioGerencialDeCompras(String numNota, String nomeProduto, Date dataInicial, Date dataFinal) throws SQLException {
		
		connection = ConnectionFactory.getConnection();
		pstmt = connection.prepareStatement(getSQLGerarRelatorioGerencialDeCompras(numNota, nomeProduto, dataInicial, dataFinal));
		int i = 1; 
		
		if(!numNota.isEmpty()){
			pstmt.setInt(i++, Integer.valueOf(numNota));
		}

		 if(!nomeProduto.isEmpty()){
			 pstmt.setString(i++, "%"+  nomeProduto  +"%");			 
		 }
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 if(dataInicial != null){
			 pstmt.setString(i++, sdf.format(dataInicial));
		 }
		 if(dataInicial != null){
			 pstmt.setString(i++, sdf.format(dataInicial));			 
		 }
		 
		 rs = pstmt.executeQuery();
		 List lista = new ArrayList();
		 
		 while(rs.next()){
			 
			 RelatorioGerencialCompras relGerencialCompras = new RelatorioGerencialCompras();
			 relGerencialCompras.setNumNota(rs.getInt("NUM_NOTA"));
			 relGerencialCompras.getProduto().setProduto(rs.getString("NOME_PRODUTO"));
			 relGerencialCompras.setQtd(rs.getInt("QTD"));
			 relGerencialCompras.getProduto().setValor(rs.getDouble("VAL"));
			 relGerencialCompras.setData(rs.getDate("DATA_ENTRADA"));
			 lista.add(relGerencialCompras);
		 }
		 
		return lista;
	}

	@SuppressWarnings("unused")
	private String getSQLGerarRelatorioGerencialDeCompras(String numNota, String nomeProduto,Date dataInicial, Date dataFinal) {
		StringBuffer query = new StringBuffer();
		query.append(" SELECT entrada.nota  AS NUM_NOTA,							\n");
		query.append("        p.produto 	AS NOME_PRODUTO,						\n");
		query.append("        sum(qntd) 	AS QTD,									\n");
		query.append("        sum(qntd*entrada.valor) AS VAL,						\n");
		query.append("        entrada.data AS DATA_ENTRADA							\n");
		query.append("   FROM entradas_via_nota entrada,							\n");
		query.append("        produtos p											\n");
		query.append("  WHERE p.idproduto = entrada.idprod							\n");
		
		if(!numNota.isEmpty()){
			query.append("    AND entrada.nota = ?						\n");
		}
		if(!nomeProduto.isEmpty()){
			query.append("    AND upper(p.produto) LIKE(upper(?))		\n");			
		}
		if((dataInicial != null) && (dataInicial != null)){
			query.append("    AND entrada.data   between STR_TO_DATE( ?, '%Y-%m-%d' ) AND STR_TO_DATE( ?, '%Y-%m-%d' )	\n");			
		}else if(dataInicial != null && dataInicial == null) {
			query.append("    AND entrada.data   > STR_TO_DATE( ?, '%Y-%m-%d' )	\n");
		}else if(dataInicial == null && dataInicial != null ){
			query.append("    AND entrada.data   < STR_TO_DATE( ?, '%Y-%m-%d' )	\n");
		}
		
		query.append("    group by NUM_NOTA,							\n");
		query.append("             NOME_PRODUTO,						\n");
		query.append("             DATA_ENTRADA							\n");
		query.append("    order by NUM_NOTA								\n");
		
		return query.toString();
	}
}
