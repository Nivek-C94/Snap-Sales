// ProductResponse.java
package com.snapsales.network;

import java.util.List;

public class ProductResponse {
    private String code;
    private List<Product> items;

    // getters and setters
}

// Product.java
package com.snapsales.network;

public class Product {
    private String title;
    private String brand;
    private String description;
    private List<String> images;

    // getters and setters
}