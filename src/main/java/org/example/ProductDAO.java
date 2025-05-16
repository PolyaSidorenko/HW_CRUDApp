package org.example;

import java.util.List;

public interface ProductDAO {
    void create(Product product);

    List<Product> getAll();

    void update(Product product);

    void delete(Long id);
}
