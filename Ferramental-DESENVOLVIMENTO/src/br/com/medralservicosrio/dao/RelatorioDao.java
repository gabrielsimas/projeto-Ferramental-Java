package br.com.medralservicosrio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import br.com.medralservicosrio.conexao.ConnectionFactory;
import br.com.medralservicosrio.relatorios.RelatorioAdmIndividual;
import br.com.medralservicosrio.relatorios.RelatorioAdmMovimentacao;
import br.com.medralservicosrio.relatorios.RelatorioAdmReforma;
import br.com.medralservicosrio.relatorios.RelatorioAdmTesteEletrico;
import br.com.medralservicosrio.relatorios.RelatorioAdmVales;
import br.com.medralservicosrio.relatorios.RelatorioAdmVeiculo;
import br.com.medralservicosrio.relatorios.RelatorioAdministrativoEntrada;
import br.com.medralservicosrio.relatorios.RelatorioGerencialCompras;
import br.com.medralservicosrio.relatorios.RelatorioGerencialProdutosPorFuncionarios;
import br.com.medralservicosrio.relatorios.RelatorioGerencialRastreabilidade;
import br.com.medralservicosrio.relatorios.RelatorioGerencialRastreabilidadeSubReport;
import br.com.medralservicosrio.relatorios.RelatorioGerencialSucata;


public class RelatorioDao extends DAO<>{

	public RelatorioDao() {
	}
	
	private SQLQuery query;
	private Connection connection;
	private PreparedStatement pstmt; 
	private ResultSet rs;

	/**
	 * recuperar os valores do relatorio de materiais
	 */
	public List<RelatorioGerencialCompras> gerarRelatorioGerencialDeMateriais(String nomeProduto){

		List<RelatorioGerencialCompras> lista = new ArrayList<RelatorioGerencialCompras>();
		
		try {

			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT								\n");
			sb.append("    		p.idproduto AS idProduto,	\n");
			sb.append("   		p.produto AS produto,		\n");
			sb.append("   		p.valor AS valor,  			\n");
			sb.append("    		(select count(teste_eletrico.idproduto) from teste_eletrico where teste_eletrico.idproduto = p.idproduto) AS qtdTeste,		\n");
			sb.append("   		(select count(reforma.idproduto) from reforma where reforma.idproduto = p.idproduto) AS qtdReformado,						\n");
			sb.append("   		(select count(estoque.idproduto) from estoque where estoque.idproduto = p.idproduto) AS qtdEstoque,							\n");
			sb.append("   		(select count(individual.idproduto) from individual where individual.idproduto = p.idproduto) AS qtdFuncionando,			\n");
			sb.append("    		(select count(veiculos.idproduto) from veiculos where veiculos.idproduto = p.idproduto) AS qtdVeiculo						\n");
			sb.append(" from produtos p						\n");
			if(!nomeProduto.isEmpty()){
				sb.append(" where upper(p.produto) LIKE (upper(:nomeProduto))		\n");
			}
			
			Session s = getSessao();
			query =	s.createSQLQuery(sb.toString());
			query.addScalar("idProduto", Hibernate.INTEGER);
			query.addScalar("produto" , Hibernate.STRING);
			query.addScalar("valor" , Hibernate.DOUBLE);
			query.addScalar("qtdTeste" , Hibernate.INTEGER);
			query.addScalar("qtdReformado" , Hibernate.INTEGER);
			query.addScalar("qtdEstoque" , Hibernate.INTEGER);
			query.addScalar("qtdFuncionando" , Hibernate.INTEGER);
			query.addScalar("qtdVeiculo" , Hibernate.INTEGER);
			query.setResultTransformer(Transformers.aliasToBean(RelatorioGerencialCompras.class));
			
			if(!nomeProduto.isEmpty()){
				query.setString("nomeProduto", "%"+nomeProduto+"%");
			}
			
			lista = query.list();
			
		}catch (Exception e) {

			System.err.println("Erro ocorrido em gerarRelatorioGerencialDeMateriais :: RelatorioDao \nErro reportado: " + e.getMessage() );
			e.printStackTrace();
		}		
		return lista;
	}

	public List<RelatorioGerencialCompras> gerarRelatorioGerencialDeCompras(String numNota, String nomeProduto, Date dataInicial, Date dataFinal) throws SQLException {
		List<RelatorioGerencialCompras> lista = new ArrayList<RelatorioGerencialCompras>();
		try {

			Session s = getSessao();
			query =	s.createSQLQuery(getSQLGerarRelatorioGerencialDeCompras(numNota, nomeProduto, dataInicial, dataFinal));
			query.addScalar("numNota", Hibernate.INTEGER);
			query.addScalar("produto" , Hibernate.STRING);
			query.addScalar("qtd" , Hibernate.INTEGER);
			query.addScalar("valor" , Hibernate.DOUBLE);
			query.addScalar("data" , Hibernate.DATE);
			query.setResultTransformer(Transformers.aliasToBean(RelatorioGerencialCompras.class));

			
			if(!numNota.isEmpty()){
				query.setInteger("numNota", Integer.valueOf(numNota));
			}

			if(!nomeProduto.isEmpty()){
				query.setString("nomeProduto", "%"+  nomeProduto  +"%");			 
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(dataInicial != null){
				query.setString("dataInicial", sdf.format(dataInicial));
			}
			if(dataFinal != null){
				query.setString("dataFinal", sdf.format(dataFinal));			 
			}

			lista = query.list();

		}catch (Exception e) {

			System.err.println("Erro ocorrido em gerarRelatorioGerencialDeCompras :: RelatorioDao \nErro reportado: " + e.getMessage() );
			e.printStackTrace();
		}	
		return lista;
	}

	/**
	 * Gera a query para o relatorio gerencial de compras
	 * @param numNota
	 * @param nomeProduto
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getSQLGerarRelatorioGerencialDeCompras(String numNota, String nomeProduto,Date dataInicial, Date dataFinal) {
		StringBuffer query = new StringBuffer();
		query.append(" SELECT entrada.nota  AS numNota,									\n");
		query.append("        p.produto 	AS produto,									\n");
		query.append("        sum(qntd) 	AS qtd,										\n");
		query.append("        sum(qntd*entrada.valor) AS valor,							\n");
		query.append("        entrada.data  AS data										\n");
		query.append("   FROM entradas_via_nota entrada,								\n");
		query.append("        produtos p												\n");
		query.append("  WHERE p.idproduto = entrada.idprod								\n");

		if(!numNota.isEmpty()){
			query.append("    AND entrada.nota = :numNota								\n");
		}
		if(!nomeProduto.isEmpty()){
			query.append("    AND upper(p.produto) LIKE(upper(:nomeProduto))			\n");			
		}
		
		if((dataInicial != null) && (dataFinal != null)){
			query.append("    AND entrada.data   between :dataInicial AND :dataFinal	\n");			
		}else if(dataInicial != null && dataFinal == null) {
			query.append("    AND entrada.data   >= :dataInicial							\n");
		}else if(dataInicial == null && dataFinal != null ){
			query.append("    AND entrada.data   <= :dataFinal							\n");
		}

		query.append("    group by numNota,						\n");
		query.append("             produto,						\n");
		query.append("             data							\n");
		query.append("    order by numNota						\n");

		return query.toString();
	}
	
	public List gerarRelatorioGerencialDeSucataHibernate(String funcionario, String nomeProduto, Date dataInicial, Date dataFinal) throws SQLException {

		List<RelatorioGerencialSucata> lista = new ArrayList<RelatorioGerencialSucata>();
		Session s = getSessao();
		StringBuffer sb = new StringBuffer();
		sb.append(" select  s.data_exclu AS data,								\n");
		sb.append(" 		s.chapa AS matricula ,								\n");
		sb.append(" 		f.nome AS nome,										\n");
		sb.append(" 		p.produto AS produto,								\n");
		sb.append(" 		p.valor AS valor,									\n");
		sb.append(" 		s.idrastre AS rastreabilidade						\n");
		sb.append("from ((sucata s join produtos p) join funcionarios f) where ((s.idproduto = p.idproduto) and (s.chapa = f.chapa))");
		if(!funcionario.isEmpty()){
			sb.append("    and (upper(f.nome) LIKE (upper(:funcionario)))		\n");
		}
		if(!nomeProduto.isEmpty()){
			sb.append("    and upper(p.produto) LIKE (upper(:nomeProduto))		\n");
		}
		if((dataInicial != null) && (dataFinal != null)){
			sb.append("    AND s.data_exclu   between :dataInicial AND :dataFinal		\n");			
		}else if(dataInicial != null && dataFinal == null) {
			sb.append("    AND s.data_exclu   >= :dataInicial							\n");
		}else if(dataInicial == null && dataFinal != null ){
			sb.append("    AND s.data_exclu   <= :dataFinal							\n");
		}
		
		query =	s.createSQLQuery(sb.toString());
		query.addScalar("data", Hibernate.DATE);
		query.addScalar("matricula" , Hibernate.STRING);
		query.addScalar("nome" , Hibernate.STRING);
		query.addScalar("produto" , Hibernate.STRING);
		query.addScalar("valor" , Hibernate.DOUBLE);
		query.addScalar("rastreabilidade" , Hibernate.BIG_INTEGER);
		query.setResultTransformer(Transformers.aliasToBean(RelatorioGerencialSucata.class));
		
		if(!funcionario.isEmpty()){
			query.setString("funcionario", "%" + funcionario + "%");
		}
		if(!nomeProduto.isEmpty()){
			query.setString("nomeProduto", "%" + nomeProduto + "%");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(dataInicial != null){
			query.setString("dataInicial", sdf.format(dataInicial));			 
		}
		if(dataFinal != null){
			query.setString("dataFinal", sdf.format(dataFinal));			 
		}
 
		lista = query.list();
		return lista;
	}

	public List<RelatorioGerencialRastreabilidade> gerarRelatorioGerencialRastreabilidade(String rastreabilidade, String produto){
		
		List<RelatorioGerencialRastreabilidade> lista = new ArrayList<RelatorioGerencialRastreabilidade>();
		try{

			Session s = getSessao();
			query = s.createSQLQuery(getSQLGerarRelatorioGerencialRastreabilidade(rastreabilidade, produto));
			query.addScalar("idRastreabilidade", Hibernate.INTEGER);
			query.addScalar("produto", Hibernate.STRING);
			query.addScalar("data", Hibernate.DATE);
			//query.addScalar("tempoDeUso", Hibernate.INTEGER);
			query.setResultTransformer(Transformers.aliasToBean(RelatorioGerencialRastreabilidade.class));
			
			if(!rastreabilidade.isEmpty()){
				query.setInteger("idRastreio",Integer.valueOf(rastreabilidade));
			}
			if(!produto.isEmpty()){
				query.setString("produto", "%"+ produto +"%");			 
			}
			lista = query.list();
			
		}catch (Exception e) {

			System.err.println("Erro ocorrido em gerarRelatorioGerencialRastreabilidade :: RelatorioDao \nErro reportado: " + e.getMessage() );
			e.printStackTrace();
		}

		return lista;
		
	}

	private String getSQLGerarRelatorioGerencialRastreabilidade(String rastreabilidade, String produto) {
		StringBuffer query = new StringBuffer();

		query.append(" SELECT 																\n");
		query.append(" 	   r.idrastre    AS idRastreabilidade,								\n");
		query.append(" 	   p.produto     AS produto,										\n");
		query.append(" 	   r.data        AS data											\n");
		query.append(" 	    -- (SELECT IF(MAX(moviment.data) = MIN(moviment.data), 			\n");
		query.append(" 		-- 		  DATEDIFF(CURDATE(), moviment.DATA), 					\n");
		query.append(" 		--		  DATEDIFF(MAX(moviment.data),MIN(moviment.data)))		\n");
		query.append(" 		--  FROM moviment 												\n");
		query.append(" 		-- WHERE moviment.rastre    = r.idrastre 						\n");
		query.append(" 		--   AND moviment.idproduto = p.idproduto) AS tempoDeUso		\n");
		query.append("   FROM rastreabilidade r,											\n");
		query.append(" 	   produtos p														\n");
		query.append("  WHERE p.idproduto = r.idproduto										\n");
		if(!rastreabilidade.isEmpty())
			query.append("    AND r.idrastre = :idRastreio									\n");
		if(!produto.isEmpty())
			query.append("    AND p.produto  = :produto										\n");
		query.append("    GROUP BY idRastreabilidade,										\n");
		query.append(" 			produto,													\n");
		query.append(" 			data														\n");
		query.append(" 			-- TEMPO_DE_USO												\n");
		query.append("    ORDER BY RASTREIO;												\n");
		
		return query.toString();
	}
	
	/**
	 * 
	 * @param rastreio
	 * @return
	 * TODO ver o caminho 1 ,2, 3, 4, ...
	 */
	public List<RelatorioGerencialRastreabilidadeSubReport> gerarRelatorioGerencialRastreabilidadeDealhes(Integer rastreio){
		List<RelatorioGerencialRastreabilidadeSubReport> lista = new ArrayList<RelatorioGerencialRastreabilidadeSubReport>();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("   SELECT IF(moviment.tipo_mov = 2, 'PAGO','DEVOLVIDO') AS status,					\n");
			sb.append("          funcionarios.nome AS nomeFuncionario,										\n");
			sb.append("          moviment.data AS data,														\n");
			sb.append("          moviment.caminho AS caminho												\n");
			sb.append("     FROM   moviment, funcionarios, cadcicle carro									\n");
			sb.append("     where (moviment.chapa = funcionarios.chapa or moviment.chapa = carro.placa )	\n");
			sb.append("     and carro.chapa_enc = funcionarios.chapa										\n");
			sb.append("     and moviment.rastre = :idRastreio												\n");
			sb.append("     group by status, data, caminho													\n");
			
			
			
			Session s = getSessao();
			query = s.createSQLQuery(sb.toString());
			query.addScalar("status", Hibernate.STRING);
			query.addScalar("nomeFuncionario", Hibernate.STRING);
			query.addScalar("data", Hibernate.DATE);
			query.addScalar("caminho", Hibernate.STRING);
			//query.addScalar("tempoDeUso", Hibernate.INTEGER);
			query.setResultTransformer(Transformers.aliasToBean(RelatorioGerencialRastreabilidadeSubReport.class));
			//TODO VER CALCULO DE DIAS TUPF
			query.setInteger("idRastreio", rastreio);
			
			lista = query.list();
			
		}catch (Exception e) {

			System.err.println("Erro ocorrido em gerarRelatorioGerencialRastreabilidade :: RelatorioDao \nErro reportado: " + e.getMessage() );
			e.printStackTrace();
		}
		return lista;
	}
	
	
	public List<RelatorioGerencialProdutosPorFuncionarios> getSQLGerarRelatorioGerencialProdutosPorFuncionarios(String chapa){
		List<RelatorioGerencialProdutosPorFuncionarios> lista = new ArrayList<RelatorioGerencialProdutosPorFuncionarios>();
		try {
			
			connection = ConnectionFactory.getConnection();
			pstmt = connection.prepareStatement(getSQLGerarRelatorioGerencialProdutosPorFuncionarios());
			pstmt.setString(1, chapa);
			rs = pstmt.executeQuery();

			while(rs.next()){

				RelatorioGerencialProdutosPorFuncionarios relProdPorFuncionarios = new RelatorioGerencialProdutosPorFuncionarios();
				relProdPorFuncionarios.getProduto().setProduto(rs.getString("PRODUTO"));
				relProdPorFuncionarios.getProduto().setValor(rs.getDouble("VALOR"));
				relProdPorFuncionarios.setQuantidade(rs.getInt("QUANTIDADE"));
				lista.add(relProdPorFuncionarios);
			}
			
		} catch(SQLException e){

			System.err.println("Erro na query ou conexão não aberta em gerarRelatorioGerencialRastreabilidade :: RelatorioDao \nErro reportado: " + e.getMessage() );
			e.printStackTrace();
		}

		catch (Exception e) {

			System.err.println("Erro ocorrido em gerarRelatorioGerencialRastreabilidade :: RelatorioDao \nErro reportado: " + e.getMessage() );
			e.printStackTrace();
		}
		return lista;
	}
	
	private String getSQLGerarRelatorioGerencialProdutosPorFuncionarios() {
		StringBuffer query = new StringBuffer();

	


		System.out.println(query.toString());
		return query.toString();
	}

	public List gerarRelAdministrativoEntrada(String numNota, Date dataInicial){
		
		List<RelatorioAdministrativoEntrada> lista = new ArrayList<RelatorioAdministrativoEntrada>();
		try{

			Session s = getSessao();
			StringBuffer sb = new StringBuffer();
			sb.append(" select 	a.idforn AS idFornecedor,							\n");
			sb.append(" 		a.nota AS numNota,									\n");
			sb.append(" 		a.data AS data,										\n");
			sb.append(" 		p.produto AS produto,								\n");
			sb.append(" 		a.qntd AS quantidade,								\n");
			sb.append(" 		a.valor AS valor,									\n");
			sb.append(" 		a.valor*a.qntd AS total 							\n");
			sb.append(" 	from entradas_via_nota a 								\n");
			sb.append(" 		inner join produtos p on a.idprod = p.idproduto 	\n");
			int i = 0;
			if(!numNota.isEmpty()){
				sb.append(whereOrAND(i)+" a.nota= :nota								\n");
				i++;
			}
			if(dataInicial != null){
				sb.append(whereOrAND(i)+" a.data = :dataevento						\n");
			}
			
			
			query =	s.createSQLQuery(sb.toString());
			query.addScalar("idFornecedor", Hibernate.INTEGER);
			query.addScalar("numNota" , Hibernate.INTEGER);
			query.addScalar("quantidade" , Hibernate.INTEGER);
			query.addScalar("data" , Hibernate.DATE);
			query.addScalar("produto" , Hibernate.STRING);
			query.addScalar("valor" , Hibernate.DOUBLE);
			query.addScalar("total" , Hibernate.DOUBLE);
			query.setResultTransformer(Transformers.aliasToBean(RelatorioAdministrativoEntrada.class));
			if(!numNota.isEmpty()){
				query.setString("nota", numNota);				
			}

			if(dataInicial != null){
				query.setString("dataevento", new SimpleDateFormat("yyyy-MM-dd").format(dataInicial));
			}
	 
			lista = query.list();
		} 

		catch (Exception e) {

			System.err.println("Erro ocorrido em gerarRelAdministrativoEntrada :: RelatorioDao \nErro reportado: " + e.getMessage() );
			e.printStackTrace();
		}
		return lista;
	}
	
	
	public List gerarRelAdministrativoIndividual(String matricula){
		List lista = new ArrayList();
		try{

			Session s = getSessao();
			StringBuffer sb = new StringBuffer();
			sb.append(" select 	i.id as idIndividual,						\n");
			sb.append(" 		i.chapa AS matricula, 						\n");
			sb.append(" 		i.rastreabilidade AS rastreabilidade, 		\n");
			sb.append(" 		i.qntd AS quantidade, i.status AS status,  	\n");
			sb.append(" 		i.tipo_req AS tipoRequisicao, 				\n");
			sb.append(" 		p.produto AS produto, 						\n");
			sb.append(" 		p.durabilidade AS durabilidadeProduto,		\n");
			sb.append(" 	  	p.idproduto AS idProduto	 				\n");
			sb.append("    from individual i, produtos p  					\n");
			sb.append("   where i.idproduto = p.idproduto					\n");
			sb.append("     and i.chapa = :matricula						\n");
			
			query =	s.createSQLQuery(sb.toString());
			query.addScalar("idProduto", Hibernate.INTEGER);
			query.addScalar("produto" , Hibernate.STRING);
			query.addScalar("idIndividual" , Hibernate.INTEGER);
			query.addScalar("rastreabilidade" , Hibernate.INTEGER);
			query.addScalar("quantidade" , Hibernate.INTEGER);
			query.addScalar("status" , Hibernate.STRING	);
			query.addScalar("tipoRequisicao" , Hibernate.STRING);
			//query.addScalar("nome" , Hibernate.STRING);
			//query.addScalar("idFuncionario" , Hibernate.INTEGER);
			query.addScalar("movimentacao" , Hibernate.INTEGER);
			query.addScalar("dataEntrega" , Hibernate.DATE);
			query.addScalar("durabilidadeProduto" , Hibernate.INTEGER);
			query.setString("matricula", matricula);
			query.setResultTransformer(Transformers.aliasToBean(RelatorioAdmIndividual.class));
 
			lista = query.list();

		} 

		catch (Exception e) {

			System.err.println("Erro ocorrido em gerarRelAdministrativoIndividual :: RelatorioDao \nErro reportado: " + e.getMessage() );
			e.printStackTrace();
		}
		return lista;
	}
	
	public List gerarRelAdministrativoVeiculos(String placa){
		List<RelatorioAdmVeiculo> lista = new ArrayList<RelatorioAdmVeiculo>();
		try{

			Session s = getSessao();
			StringBuffer sql = new StringBuffer();
			/* SELECT v.`id` AS idveiculo,v.`placa` AS placa,v.`quantidade` AS vqtd, v.rastre as vRastre,v.`idproduto` AS vIdProd,p.`produto` AS produto, v.data as entrega, p.durabilidade as durabilidade,f.nome as nome FROM `produtos` p INNER JOIN `veiculos` v ON p.`idproduto` = v.`idproduto` inner join funcionarios f on v.chapaenc = f.chapa	*/

		} 

		catch (Exception e) {

			System.err.println("Erro ocorrido em gerarRelAdministrativoEntrada :: RelatorioDao \nErro reportado: " + e.getMessage() );
			e.printStackTrace();
		}
		return lista;
	}
	
	public List gerarRelAdministrativoMovimentacao(String matricula, String placa, String rastreabilidade, String produto){
		List<RelatorioAdmMovimentacao> lista = new ArrayList<RelatorioAdmMovimentacao>();
		try{

			Session s = getSessao();
			StringBuilder sb = new StringBuilder();
			sb.append(" select f.nome as nome, 	\n");
			sb.append(" 	   m.chapa as chapa,	\n");
			sb.append(" 	   m.idmove as idmove, 	\n");
			sb.append(" 	   m.rastre as rastre, 	\n");
			sb.append(" 	   m.motivo as motivo, 	\n");
			sb.append(" 	   m.status as status, 	\n");
			sb.append(" 	   m.tipo_mov as tipo,	\n");
			sb.append(" 	   m.caminho as caminho,	\n");
			sb.append(" 	   m.usuario as usuario,	\n");
			sb.append(" 	   m.quantidade qtd,	\n");
			sb.append("        p.produto as prod	\n");
			sb.append("   from funcionarios f inner join moviment m on f.chapa = m.chapa 	\n");
			sb.append("        inner join produtos p on p.idproduto = m.idproduto	\n");
			
			
	/*
			private Integer tipo;
			private String chapa;
			private String funcionario;
			private Date data;
			private String motivo;
			private String produto;
			private String status;
			private String rastreamento;
			private String destino;
			private Integer quantidade;
			private String usuario;
		*/	

		} 

		catch (Exception e) {

			System.err.println("Erro ocorrido em gerarRelAdministrativoEntrada :: RelatorioDao \nErro reportado: " + e.getMessage() );
			e.printStackTrace();
		}
		return lista;
	}
	
	public List gerarRelAdministrativoTesteEletrico(String produto, String rastreabilidade){
		List<RelatorioAdmTesteEletrico> lista = new ArrayList<RelatorioAdmTesteEletrico>();
		try{

			Session s = getSessao();
			StringBuffer sql = new StringBuffer();
	/*SELECT
	     	p.idproduto as id,
		p.produto as produto,
		p.valor as valor,
		r.idrastre as rastreado,
		t.data_ent as data
	
	from produtos p
		inner join rastreabilidade r
		on r.idproduto = p.idproduto
		inner join teste_eletrico t
		on t.idproduto = p.idproduto*/
		} 

		catch (Exception e) {

			System.err.println("Erro ocorrido em gerarRelAdministrativoEntrada :: RelatorioDao \nErro reportado: " + e.getMessage() );
			e.printStackTrace();
		}
		return lista;
	}
	
	
	public List gerarRelAdministrativoVales(String matricula, String setor, Date dataInicial, Date dataFinal){
		List<RelatorioAdmVales> lista = new ArrayList<RelatorioAdmVales>();
		try{

			Session s = getSessao();
			StringBuffer sql = new StringBuffer();
			//TODO ver query no php

		} 

		catch (Exception e) {

			System.err.println("Erro ocorrido em gerarRelAdministrativoEntrada :: RelatorioDao \nErro reportado: " + e.getMessage() );
			e.printStackTrace();
		}
		return lista;
	}
	
	
	public List gerarRelAdministrativoReforma(Date dataInicial, String produto){
		List<RelatorioAdmReforma> lista = new ArrayList<RelatorioAdmReforma>();
		try{

			Session s = getSessao();
			StringBuffer sql = new StringBuffer();
			//TODO ver query no php

		} 

		catch (Exception e) {

			System.err.println("Erro ocorrido em gerarRelAdministrativoEntrada :: RelatorioDao \nErro reportado: " + e.getMessage() );
			e.printStackTrace();
		}
		return lista;
	}

	private String whereOrAND(int i){
		return i > 0 ? "and" : "where";
	}
}
