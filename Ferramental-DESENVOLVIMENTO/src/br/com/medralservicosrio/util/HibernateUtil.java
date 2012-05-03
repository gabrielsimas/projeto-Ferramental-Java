package br.com.medralservicosrio.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.medralservicosrio.modelo.EntradaViaNota;
import br.com.medralservicosrio.modelo.Estoque;
import br.com.medralservicosrio.modelo.Fornecedor;
import br.com.medralservicosrio.modelo.Funcionario;
import br.com.medralservicosrio.modelo.HistoricoEntrada;
import br.com.medralservicosrio.modelo.LogUsuario;
import br.com.medralservicosrio.modelo.MotoSerra;
import br.com.medralservicosrio.modelo.Movimentacao;
import br.com.medralservicosrio.modelo.MovimentacaoIndividual;
import br.com.medralservicosrio.modelo.Produto;
import br.com.medralservicosrio.modelo.Rastreabilidade;
import br.com.medralservicosrio.modelo.Reforma;
import br.com.medralservicosrio.modelo.Sucata;
import br.com.medralservicosrio.modelo.TesteEletrico;
import br.com.medralservicosrio.modelo.Usuario;
import br.com.medralservicosrio.modelo.Vale;
import br.com.medralservicosrio.modelo.Veiculo;

//import com.jamacedo.tutorial.livro.modelo.Livro;
/**
 * Classe que se utiliza do Design (ou anti-Design) Pattern
 * Singleton
 * No qual temos um construtor vazio 
 * e um atributo privado que irá fazer parte
 * da conexão.
 * 
 * Isso fará com que o Hibernate abre apenas uma sessão para conexão ao BD
 * se estiver ativa, ele a utiliza
 * caso não, ele cria uma e a utiliza.
 * 
 * @author Gabriel
 *
 */
public class HibernateUtil {
	
	private static SessionFactory sessionFactory;

	private HibernateUtil() {
		
	}
	
	public static SessionFactory getSessionFactory(){
		if (sessionFactory == null){
			try{
				//sessionFactory = new AnnotationConfiguration().configure("com/jamacedo/tutorial/livro/util/hibernate.cfg.xml").buildSessionFactory();
				AnnotationConfiguration ac = new AnnotationConfiguration();
				
				//Inserindo Métodos para uso
				ac.setProperty("hibernate.show_sql", "true");
				
				//Inserindo as Classes Anotadas
				ac.addAnnotatedClass(EntradaViaNota.class);
				ac.addAnnotatedClass(Estoque.class);
				ac.addAnnotatedClass(Fornecedor.class);
				ac.addAnnotatedClass(Funcionario.class);
				ac.addAnnotatedClass(HistoricoEntrada.class);
				ac.addAnnotatedClass(LogUsuario.class);
				ac.addAnnotatedClass(MotoSerra.class);
				ac.addAnnotatedClass(Movimentacao.class);
				ac.addAnnotatedClass(MovimentacaoIndividual.class);
				ac.addAnnotatedClass(Produto.class);
				ac.addAnnotatedClass(Rastreabilidade.class);
				ac.addAnnotatedClass(Reforma.class);
				ac.addAnnotatedClass(Sucata.class);
				ac.addAnnotatedClass(TesteEletrico.class);
				ac.addAnnotatedClass(Usuario.class);
				ac.addAnnotatedClass(Vale.class);
				ac.addAnnotatedClass(Veiculo.class);
				
				//sessionFactory = ac.configure().buildSessionFactory();
				sessionFactory = ac.configure("br/com/medralservicosrio/util/hibernate.cfg.xml").buildSessionFactory();
				
				
			} catch(Throwable ex) {
				System.err.println("Criacao da instancia para conexao com o BD falhou!" +
						"\nErro: " + ex);
				throw new ExceptionInInitializerError(ex);
			}
			
			return sessionFactory;
			
		} else {
		return sessionFactory;
		}
	}
}
