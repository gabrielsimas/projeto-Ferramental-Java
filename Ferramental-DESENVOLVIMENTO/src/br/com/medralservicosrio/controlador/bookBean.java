/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.medralservicosrio.controlador;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eliane
 */

/*
 * Coleção de dados que apareceram , quando clicar no botão cadastrar.
 * Utilizando Componente Collector do Primeface
 */
public class bookBean {

	private Book book = new Book();
	
	private List<Book> books = new ArrayList<Book>();

	public String reinit() {
		book = new Book();
		
		return null;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}	
					