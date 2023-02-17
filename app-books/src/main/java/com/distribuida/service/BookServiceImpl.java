package com.distribuida.service;

import com.distribuida.dto.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class BookServiceImpl implements BookService {

    @PersistenceContext(unitName = "per")
    private EntityManager entityManager;

    @Override
    public List<Book> findAll() throws ExecutionException, InterruptedException {
        return entityManager.createNamedQuery("Book.findAll").getResultList();
    }

    @Override
    public Book findOne(long id) throws ExecutionException, InterruptedException {
        return (Book) entityManager.createNamedQuery("Book.findById").setParameter("id", id).getSingleResult();
    }

    @Override
    @Transactional
    public void save(Book book) {
        this.entityManager.persist(book);
    }

    @Override
    @Transactional
    public void update(long id, Book book) {
        var oldBook = this.entityManager.find(Book.class, id);
        oldBook.setAuthor(book.getAuthor());
        oldBook.setIsbn(book.getIsbn());
        oldBook.setTitle(book.getTitle());
        oldBook.setPrice(book.getPrice());
        this.entityManager.merge(oldBook);
    }
    @Override
    @Transactional
    public void delete(long id) {
        var query = this.entityManager.createQuery("DELETE FROM Book b WHERE b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
