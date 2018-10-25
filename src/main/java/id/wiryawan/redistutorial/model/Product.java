package id.wiryawan.redistutorial.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product {
    private String productId;
    private String productName;
    private String price;
}
