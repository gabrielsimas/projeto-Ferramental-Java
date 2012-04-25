package br.com.medralservicosrio.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.medralservicosrio.modelo.Fornecedor;
import br.com.medralservicosrio.modelo.Funcionario;
import br.com.medralservicosrio.modelo.MotoSerra;
import br.com.medralservicosrio.modelo.Produto;
import br.com.medralservicosrio.modelo.Rastreabilidade;
import br.com.medralservicosrio.modelo.Usuario;
import br.com.medralservicosrio.modelo.Veiculo;

//import com.jamacedo.tutorial.livro.modelo.Livro;
/**
 * Classe que se utiliza do Design (ou anti-Design) Pattern
 * Singleton
 * No qual temos um construtor vazio 
 * e um atributo privado que ir� fazer parte
 * da conex�o.
 * 
 * Isso far� com que o Hibernate abre apenas uma sess�o para conex�o ao BD
 * se estiver ativa, ele a utiliza
 * caso n�o, ele cria uma e a utiliza.
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
				
				//Inserindo M�todos para uso
				ac.setProperty("hibernate.show_sql", "true");
				
				//Inserindo as Classes Anotadas
				ac.addAnnotatedClass(Fornecedor.class);
				ac.addAnnotatedClass(Funcionario.class);
				ac.addAnnotatedClass(MotoSerra.class);
				ac.addAnnotatedClass(Produto.class);
				ac.addAnnotatedClass(Rastreabilidade.class);
				ac.addAnnotatedClass(Usuario.class);
				ac.addAnnotatedClass(Veiculo.class);
				
				//sessionFactory = ac.configure().buildSessionFactory();
				sessionFactory = ac.configure("com/jamacedo/tutorial/livro/util/hibernate.cfg.xml").buildSessionFactory();
				
				
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
