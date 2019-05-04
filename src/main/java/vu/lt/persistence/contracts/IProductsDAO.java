package vu.lt.persistence.contracts;

import vu.lt.entities.Product;

public interface IProductsDAO {

    void persist(Product product);
    Product findProduct(int id);
    Product update(Product product, int id);
}
