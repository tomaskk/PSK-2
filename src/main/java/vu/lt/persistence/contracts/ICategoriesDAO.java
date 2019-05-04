package vu.lt.persistence.contracts;

import vu.lt.entities.Category;

public interface ICategoriesDAO {

    void persist(Category cat);
    Category update(Category cat, int id);
    Category findCategory(int id);

}
