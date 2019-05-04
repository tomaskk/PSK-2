package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Shop;
import vu.lt.interceptors.LoggedInvocation;
import vu.lt.persistence.contracts.IShopsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Shops {

    @Inject
    private IShopsDAO shopsDAO;

    @Getter @Setter
    private Shop shopToCreate = new Shop();

    @Getter @Setter
    private Shop shopToEdit = new Shop();

    @Getter
    private List<Shop> allShops;

    @PostConstruct
    public void init(){
        loadAllShops();
    }

    @Transactional
    @LoggedInvocation
    public String createShop(){
        this.shopsDAO.persist(shopToCreate);
        return "/JPA/shops?faces-redirect=true";
    }

    @Transactional
    public String editShop(){

        this.shopsDAO.update(shopToEdit);

        return "/JPA/shops?faces-redirect=true";
    }

    private void loadAllShops(){
        this.allShops = shopsDAO.loadAll();
    }
}