package vu.lt.persistence;

import vu.lt.entities.Product;
import vu.lt.persistence.contracts.IProductsDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class ProductsDAO implements IProductsDAO {
    @Inject
    protected EntityManager em;

    public void persist(Product product){
        System.out.println(" i'm in ProductsDAO MAIN --> add product\n");
        this.em.persist(product);
    }

    public Product update(Product prod, int id){
        System.out.println(" i'm in ProductsDAO MAIN --> add product\n");
        Product productToUpdate = this.em.find(Product.class, id);
        if(productToUpdate == null)
            return null; // fail
        else {
            prod.setId(id);
            return this.em.merge(prod);
        }
    }

    public Product findProduct(int id){
        System.out.println(" i'm in ProductsDAO MAIN --> find product\n");
        return this.em.find(Product.class, id);
    }
}