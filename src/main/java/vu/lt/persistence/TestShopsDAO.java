package vu.lt.persistence;

import vu.lt.entities.Shop;
import vu.lt.interceptors.LoggedInvocation;
import vu.lt.persistence.contracts.IShopsDAO;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Alternative
@ApplicationScoped
public class TestShopsDAO implements IShopsDAO {

    @Inject
    private EntityManager em;

    @LoggedInvocation
    public List<Shop> loadAll() {
        System.out.println(" i'm in test shops dao --> load all shops\n");
        return em.createNamedQuery("Shop.findAll", Shop.class).getResultList();
    }

    @LoggedInvocation
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @LoggedInvocation
    public void persist(Shop shop){
        System.out.println(" i'm in test shops dao --> add new shop\n");
        this.em.persist(shop);
    }

    @LoggedInvocation
    public Shop findOne(Integer id) {
        System.out.println(" i'm in test shops dao --> find specific shop\n");
        return em.find(Shop.class, id);
    }

    @LoggedInvocation
    public Shop update(Shop shop){
        System.out.println(" i'm in test shops dao --> update shop details\n");
        if(shop.getId() != null)
            return em.merge(shop);
        else
            return null;

    }
}