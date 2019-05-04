package vu.lt.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ShopDTO {

    private String name;
    private String address;
    private Integer opt_Lock_Version;

}