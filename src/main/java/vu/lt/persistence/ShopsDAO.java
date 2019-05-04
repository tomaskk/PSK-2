package vu.lt.persistence;

import vu.lt.entities.Shop;
import vu.lt.persistence.contracts.IShopsDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.List;

@ApplicationScoped
public class ShopsDAO implements IShopsDAO {

    @Inject
    private EntityManager em;

    public List<Shop> loadAll() {
        System.out.println(" i'm in main shops dao");
        return em.createNamedQuery("Shop.findAll", Shop.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Shop shop){
        System.out.println(" i'm in main shops dao");
        this.em.persist(shop);
    }

    public Shop findOne(Integer id) {
        System.out.println(" i'm in main shops dao");
        return em.find(Shop.class, id);
    }

    public Shop update(Shop shop){
        System.out.println(" i'm in main shops dao");
        if(shop.getId() != null)
            return em.merge(shop);
        else
            return null;

    }
}