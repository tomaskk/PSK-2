package vu.lt.persistence.contracts;
import vu.lt.entities.Shop;
import java.util.List;

public interface IShopsDAO {

    List<Shop> loadAll();
    void persist(Shop shop);
    Shop findOne(Integer id);
    Shop update(Shop shop);
}
