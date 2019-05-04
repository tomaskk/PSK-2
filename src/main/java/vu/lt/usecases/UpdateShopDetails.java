package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Shop;
import vu.lt.interceptors.LoggedInvocation;
import vu.lt.persistence.contracts.IShopsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class UpdateShopDetails implements Serializable {

    private Shop shop;

    @Inject
    private IShopsDAO shopsDAO;


    @PostConstruct
    public void init(){
        System.out.println("UpdateShopDetails INIT CALLED");

        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer shopId = Integer.parseInt(requestParameters.get("shopId"));
        this.shop = shopsDAO.findOne(shopId);
    }

    @Transactional
    @LoggedInvocation
    public String editShop(){
        try{
            this.shopsDAO.update(this.shop);
        } catch (OptimisticLockException e) {
            System.out.println(">>>--- OPTIMISTIC LOCK HAPPENED <<");

            return "/JPA/shopDetails.xhtml?faces-redirect=true&shopId=" + this.shop.getId() + "&error=optimistic-lock-exception";
        }

        return "/JPA/shops?faces-redirect=true";
    }
}