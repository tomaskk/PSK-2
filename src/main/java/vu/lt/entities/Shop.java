package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Shop.findAll", query = "select t from Shop as t"),
        @NamedQuery(name = "Shop.count", query = "select count(t) from Shop as t")
})
@Table(name = "SHOP")
@Getter @Setter
public class Shop {

    public Shop(){

    }

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String address;

    @OneToMany(mappedBy = "shop")
    private List<Product> products = new ArrayList<>();
    @OneToMany(mappedBy = "shop")
    private List<Category> categories = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return Objects.equals(name, shop.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}