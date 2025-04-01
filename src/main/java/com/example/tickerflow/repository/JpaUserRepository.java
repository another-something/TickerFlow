package com.example.tickerflow.repository;

import com.example.tickerflow.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    @Autowired
    private EntityManagerFactory emf;
    @PersistenceContext
    private EntityManager em;



//    @Override
//    public UserInfo create(UserInfo userInfo){
//        try {
//            em = emf.createEntityManager();
//            EntityTransaction tx = em.getTransaction();
//            tx.begin();
//            try {
//                em.persist(userInfo);
//            } catch (Exception e) {
//                tx.rollback();
//                System.out.println(e.getMessage());
//            } finally {
//                em.clear();
//            }
//        }
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return userInfo;
//    }


    @Override
    public List<User> readAll(){
        return null;
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return em.createQuery("SELECT u FROM User u WHERE u.username= :userId", User.class)
                .setParameter("userId", userId)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public void flush() {
        em.flush();
    }

    @Override
    public int existsByUserId(String userId) {
        Long count = em.createQuery("SELECT count(u) FROM User u WHERE u.username= :userId", Long.class)
                .setParameter("userId", userId)
                .getSingleResult();
        return count.intValue();
    }
}
