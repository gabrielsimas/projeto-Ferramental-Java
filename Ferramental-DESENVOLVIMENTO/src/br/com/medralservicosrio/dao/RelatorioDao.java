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
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import br.com.medralservicosrio.conexao.ConnectionFactory;
import br.com.medralservicosrio.generics.DAO;
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
import br.com.medralservicosrio.relatorios.RelatorioGerencialSucata2;


public class RelatorioDao extends DAO{

	public RelatorioDao() {
	}
	
	private SQLQuery query;
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
	 * recuperar os valores do relatorio de materiais
	 */
	public List<RelatorioGerencialCompras> gerarRelatorioGerencialDeMateriais(String nomeProduto){

		List<RelatorioGerencialCompras> lista = new ArrayList<RelatorioGerencialCompras>();
		try {

			connection = ConnectionFactory.getConnection();
			pstmt = connection.prepareStatement(getSQLGerarRelatorioGerencialDeMateriais(nomeProduto));
			
			if(!nomeProduto.isEmpty()){
				pstmt.setString(1, "%"+nomeProduto+"%");
			}
			
			rs = pstmt.executeQuery();

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
			
			connection.close();
			
		} catch(SQLException e){
			
			System.err.println("Erro na query ou conexão não aberta em gerarRelatorioGerencialDeMateriais :: RelatorioDao \nErro reportado: " + e.getMessage() );
			e.printStackTrace();
		}
		
		catch (Exception e) {

			System.err.println("Erro ocorrido em gerarRelatorioGerencialDeMateriais :: RelatorioDao \nErro reportado: " + e.getMessage() );
			e.printStackTrace();
		}		
		return lista;
	}

	/**
	 * 
	 * @param nomeProduto
	 * @return
	 */
	private String getSQLGerarRelatorioGerencialDeMateriais(String nomeProduto) {
		StringBuffer query = new StringBuffer();
		query.append(" SELECT							\n");
		query.append("    p.idproduto AS id,			\n");
		query.append("    p.produto AS nome_produto,	\n");
		query.append("    p.valor AS valor_produto,  	\n");
		query.append("    (select count(teste_eletrico.idproduto) from teste_eletrico where teste_eletrico.idproduto = p.idproduto) AS QTD_TESTE,	\n");
		query.append("    (select count(reforma.idproduto) from reforma where reforma.idproduto = p.idproduto) AS QTD_REFORMA,						\n");
		query.append("    (select count(estoque.idproduto) from estoque where estoque.idproduto = p.idproduto) AS QTD_ESTOQUE,						\n");
		query.append("    (select count(individual.idproduto) from individual where individual.idproduto = p.idproduto) AS QTD_INDIVIDUAL,			\n");
		query.append("    (select count(veiculos.idproduto) from veiculos where veiculos.idproduto = p.idproduto) AS QTD_VEICULOS					\n");
		query.append(" from produtos p					\n");
		if(!nomeProduto.isEmpty()){
			query.append(" where upper(p.produto) LIKE (upper(?))		\n");
		}
		return query.toString();
	}

	public List<RelatorioGerencialCompras> gerarRelatorioGerencialDeCompras(String numNota, String nomeProduto, Date dataInicial, Date dataFinal) throws SQLException {
		List<RelatorioGerencialCompras> lista = new ArrayList<RelatorioGerencialCompras>();
		try {


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


			while(rs.next()){

				RelatorioGerencialCompras relGerencialCompras = new RelatorioGerencialCompras();
				relGerencialCompras.setNumNota(rs.getInt("NUM_NOTA"));
				relGerencialCompras.getProduto().setProduto(rs.getString("NOME_PRODUTO"));
				relGerencialCompras.setQtd(rs.getInt("QTD"));
				relGerencialCompras.getProduto().setValor(rs.getDouble("VAL"));
				relGerencialCompras.setData(rs.getDate("DATA_ENTRADA"));
				lista.add(relGerencialCompras);
			}
		} catch(SQLException e){

			System.err.println("Erro na query ou conexão não aberta em gerarRelatorioGerencialDeCompras :: RelatorioDao \nErro reportado: " + e.getMessage() );
			e.printStackTrace();
		}

		catch (Exception e) {

			System.err.println("Erro ocorrido em gerarRelatorioGerencialDeCompras :: RelatorioDao \nErro reportado: " + e.getMessage() );
			e.printStackTrace();
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

	public List<RelatorioGerencialSucata> gerarRelatorioGerencialDeSucata(String funcionario,	String nomeProduto, Date dataInicial, Date dataFinal) throws SQLException{
		List<RelatorioGerencialSucata> lista = new ArrayList<RelatorioGerencialSucata>();
		try {
			connection = ConnectionFactory.getConnection();
			pstmt = connection.prepareStatement(getSQLGerarRelatorioGerencialDeSucata(funcionario, nomeProduto, dataInicial, dataFinal));
			int i = 1;

			if(!funcionario.isEmpty()){
				pstmt.setString(i++, "%"+  funcionario +"%");
			}
			if(!nomeProduto.isEmpty()){
				pstmt.setString(i++, "%"+  nomeProduto  +"%");			 
			}

			/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 if(dataInicial != null){
				 pstmt.setString(i++, sdf.format(dataInicial));
			 }
			 if(dataInicial != null){
				 pstmt.setString(i++, sdf.format(dataInicial));			 
			 }*/

			rs = pstmt.executeQuery();
			while(rs.next()){

				RelatorioGerencialSucata sucata = new RelatorioGerencialSucata();
				sucata.setData(rs.getDate("DATA"));
				sucata.setMatricula(rs.getString("MATRICULA"));
				sucata.setNome(rs.getString("NOME"));
				sucata.setProduto(rs.getString("PRODUTO"));
				sucata.setValor(rs.getDouble("VALOR"));
				lista.add(sucata);
			}
		}  catch(SQLException e){

			System.err.println("Erro na query ou conexão não aberta em gerarRelatorioGerencialDeCompras :: RelatorioDao \nErro reportado: " + e.getMessage() );
			e.printStackTrace();
		}

		catch (Exception e) {

			System.err.println("Erro ocorrido em gerarRelatorioGerencialDeCompras :: RelatorioDao \nErro reportado: " + e.getMessage() );
			e.printStackTrace();
		}
		return lista;
	}

	private String getSQLGerarRelatorioGerencialDeSucata(String funcionario, String nomeProduto, Date dataInicial, Date dataFinal) {
		StringBuffer query = new StringBuffer();

		query.append(" select s.data_exclu AS DATA , s.chapa AS MATRICULA ,f.nome AS NOME , p.produto AS PRODUTO ,p.valor AS VALOR	\n");
		query.append("   from sucata s, produtos p, funcionarios f	 where s.idproduto = p.idproduto	and s.chapa = f.chapa		\n");
		if(!funcionario.isEmpty()){
			query.append("    and upper(s.chapa) LIKE (upper(?))														   			\n");
		}
		if(!nomeProduto.isEmpty()){
			query.append("    and upper(p.produto) LIKE (upper(?))																	\n");
		}
		//query.append("    AND s.data   between STR_TO_DATE( ?, 'YYYY-MM-DD' ) AND STR_TO_DATE( ?, 'YYYY-MM-DD' )		 				\n");

		return query.toString();
	}


	public List gerarRelatorioGerencialDeSucataHibernate(String funcionario,	String nomeProduto, Date dataInicial, Date dataFinal) throws SQLException {

		Session s = getSessao();
		StringBuffer sql = new StringBuffer();
		sql.append("select s.data_exclu AS data, s.chapa AS matricula, s.idrastre AS rastreabilidade, f.nome AS nome, p.produto AS produto, p.valor AS valor ");
		sql.append("from ((sucata s join produtos p) join funcionarios f) where ((s.idproduto = p.idproduto) and (s.chapa = f.chapa))");
		if(!funcionario.isEmpty()){
			sql.append("    and (upper(f.nome) LIKE (upper(:funcionario)))		\n");
		}
		if(!nomeProduto.isEmpty()){
			sql.append("    and upper(p.produto) LIKE (upper(:nomeProduto))	\n");
		}

		query =	s.createSQLQuery(sql.toString());
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
 
		List lista = query.list();
		return lista;
	}

	public List<RelatorioGerencialRastreabilidade> gerarRelatorioGerencialRastreabilidade(String rastreabilidade, String produto){
		
		List<RelatorioGerencialRastreabilidade> lista = new ArrayList<RelatorioGerencialRastreabilidade>();
		try{

			connection = ConnectionFactory.getConnection();
			pstmt = connection.prepareStatement(getSQLGerarRelatorioGerencialRastreabilidade(rastreabilidade, produto));
			int i = 1;

			if(!rastreabilidade.isEmpty()){
				pstmt.setInt(i++,Integer.valueOf(rastreabilidade));
			}
			if(!produto.isEmpty()){
				pstmt.setString(i++, "%"+  produto  +"%");			 
			}

			/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 if(dataInicial != null){
				 pstmt.setString(i++, sdf.format(dataInicial));
			 }
			 if(dataInicial != null){
				 pstmt.setString(i++, sdf.format(dataInicial));			 
			 }*/

			rs = pstmt.executeQuery();

			while(rs.next()){

				RelatorioGerencialRastreabilidade rastreio = new RelatorioGerencialRastreabilidade();
				rastreio.setData(rs.getDate("DATA_ENTRADA"));
				rastreio.setProduto(rs.getString("NOME_PRODUTO"));
				rastreio.setIdRastreabilidade(rs.getInt("RASTREIO"));
				lista.add(rastreio);
			}

		}catch(SQLException e){

			System.err.println("Erro na query ou conexão não aberta em gerarRelatorioGerencialRastreabilidade :: RelatorioDao \nErro reportado: " + e.getMessage() );
			e.printStackTrace();
		}

		catch (Exception e) {

			System.err.println("Erro ocorrido em gerarRelatorioGerencialRastreabilidade :: RelatorioDao \nErro reportado: " + e.getMessage() );
			e.printStackTrace();
		}

		return lista;
		
	}

	private String getSQLGerarRelatorioGerencialRastreabilidade(String rastreabilidade, String produto) {
		StringBuffer query = new StringBuffer();

		query.append(" SELECT 																\n");
		query.append(" 	   r.idrastre    AS RASTREIO, 									\n");
		query.append(" 	   p.produto     AS NOME_PRODUTO,								\n");
		query.append(" 	   r.data        AS DATA_ENTRADA								\n");
		query.append(" 	   -- (SELECT IF(MAX(moviment.data) = MIN(moviment.data), 			\n");
		query.append(" 		-- 		  DATEDIFF(CURDATE(), moviment.DATA), 				\n");
		query.append(" 		--		  DATEDIFF(MAX(moviment.data),MIN(moviment.data)))	\n");
		query.append(" 		--  FROM moviment 											\n");
		query.append(" 		-- WHERE moviment.rastre    = r.idrastre 						\n");
		query.append(" 		--   AND moviment.idproduto = p.idproduto) AS TEMPO_DE_USO	\n");
		query.append("   FROM rastreabilidade r,											\n");
		query.append(" 	   produtos p													\n");
		query.append("  WHERE p.idproduto = r.idproduto									\n");
		query.append(" -- AND r.idrastre = 1139 -- m.rastre								\n");
		query.append(" -- AND p.produto  = ?												\n");
		query.append("    GROUP BY RASTREIO,												\n");
		query.append(" 			NOME_PRODUTO,											\n");
		query.append(" 			DATA_ENTRADA											\n");
		query.append(" 			-- TEMPO_DE_USO											\n");
		query.append("    ORDER BY RASTREIO;												\n");
		
		System.out.println(query.toString());
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
			
			connection = ConnectionFactory.getConnection();
			pstmt = connection.prepareStatement(getSQLGerarRelatorioGerencialRastreabilidadeSubReport());
			pstmt.setInt(1, rastreio);
			rs = pstmt.executeQuery();

			while(rs.next()){

				RelatorioGerencialRastreabilidadeSubReport subRastreio = new RelatorioGerencialRastreabilidadeSubReport();
				subRastreio.setStatus(rs.getString("STATUS_TIPO_MOVIMENTO"));
				subRastreio.setNomeFuncionario(rs.getString("NOME"));
				subRastreio.setData(rs.getDate("DATA_MOVIMENTO"));
				subRastreio.setCaminho(rs.getString("CAMINHO"));
				//TODO VER CALCULO DE DIAS TUPF
				lista.add(subRastreio);
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
	
	private String getSQLGerarRelatorioGerencialRastreabilidadeSubReport() {
		StringBuffer query = new StringBuffer();

		query.append("   SELECT IF(moviment.tipo_mov = 2, 'PAGO','DEVOLVIDO') AS STATUS_TIPO_MOVIMENTO,	\n");
		query.append("          funcionarios.nome AS NOME,												\n");
		query.append("          moviment.data AS DATA_MOVIMENTO,										\n");
		query.append("          moviment.caminho AS CAMINHO												\n");
		query.append("     FROM   moviment, funcionarios, cadcicle carro								\n");
		query.append("     where (moviment.chapa = funcionarios.chapa or moviment.chapa = carro.placa )	\n");
		query.append("     and carro.chapa_enc = funcionarios.chapa										\n");
		query.append("     and moviment.rastre = ?														\n");
		query.append("     group by STATUS_TIPO_MOVIMENTO, DATA_MOVIMENTO, CAMINHO;						\n");
			
		
		System.out.println(query.toString());
		return query.toString();
	}
	
	public List<RelatorioGerencialProdutosPorFuncionarios> getSQLGerarRelatorioGerencialProdutosPorFuncionarios(String chapa){
		List<RelatorioGerencialProdutosPorFuncionarios> lista = new ArrayList<RelatorioGerencialProdutosPorFuncionarios>();
		try {
			
			connection = ConnectionFactory.getConnection();
			pstmt = connection.prepareStatement(getSQLGerarRelatorioGerencialRastreabilidadeSubReport());
			pstmt.setString(1, chapa);
			rs = pstmt.executeQuery();

			while(rs.next()){

				RelatorioGerencialProdutosPorFuncionarios relProdPorFuncionarios = new RelatorioGerencialProdutosPorFuncionarios();
				relProdPorFuncionarios.getProduto().setProduto(rs.getString("PrODUTO"));
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
			StringBuffer sql = new StringBuffer();

		} 

		catch (Exception e) {

			System.err.println("Erro ocorrido em gerarRelAdministrativoEntrada :: RelatorioDao \nErro reportado: " + e.getMessage() );
			e.printStackTrace();
		}
		return lista;
	}
	
	public List gerarRelAdministrativoVeiculos(String placa){
		List<RelatorioAdmVeiculo> lista = new ArrayList<RelatorioAdmVeiculo>();
		try{

			Session s = getSessao();
			StringBuffer sql = new StringBuffer();

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
		StringBuilder sb = new StringBuilder();
		
		
		
		
		return lista;
	}
	
	public List gerarRelAdministrativoTesteEletrico(String produto, String rastreabilidade){
		List<RelatorioAdmTesteEletrico> lista = new ArrayList<RelatorioAdmTesteEletrico>();
		try{

			Session s = getSessao();
			StringBuffer sql = new StringBuffer();

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
