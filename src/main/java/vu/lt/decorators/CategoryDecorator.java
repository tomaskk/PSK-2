package vu.lt.decorators;

import vu.lt.entities.Category;
import vu.lt.persistence.contracts.ICategoriesDAO;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Decorator
public abstract class CategoryDecorator implements ICategoriesDAO {

    @Inject
    @Delegate
    @Any
    ICategoriesDAO catDAO;

    @Transactional
    public void persist(Category cat) {
        System.out.println("\n>> Decorator doing stuff before ICategoriesDAO is invoked. \n");
        cat.setName(cat.getName() + "-decorated");
        catDAO.persist(cat);
    }
}