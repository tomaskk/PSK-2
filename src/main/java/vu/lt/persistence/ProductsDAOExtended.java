package vu.lt.persistence;

import vu.lt.entities.Product;
import vu.lt.interceptors.LoggedInvocation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

@ApplicationScoped
@Specializes
public class ProductsDAOExtended extends ProductsDAO {

    @Override
    @LoggedInvocation
    public void persist(Product product){
        System.out.println(" i'm in ProductsDAO Extended --> add product\n");
        product.setName(product.getName() + "test");
        this.em.persist(product);
    }

    @Override
    @LoggedInvocation
    public Product update(Product prod, int id){

        Product productToUpdate = this.em.find(Product.class, id);
        if(productToUpdate == null)
        {
            System.out.println(" i'm in ProductsDAO Extended --> product update failed \n");
            return null; // fail
        }
        else {
            System.out.println(" i'm in ProductsDAO Extended --> product update successful\n");
            prod.setId(id);
            return this.em.merge(prod);
        }
    }

    @Override
    @LoggedInvocation
    public Product findProduct(int id){
        System.out.println(" i'm in ProductsDAO Extended --> find product \n");
        return this.em.find(Product.class, id);
    }
}