package org.example;

import java.util.List;

public class ProductService {
    private ProductDao productDao;

    public ProductService() {
        var connection = DBUtil.getConnection();
        this.productDao = new ProductDao(connection);
    }

    public ProductRecord findById(int id) {
        var find = productDao.findById(id);
        if (find == null) {
            throw new ProductNotFoundException();
        }
        return find;
    }

    public List<ProductRecord> findByName(String name) {
        var find = productDao.findByName(name);
        return find;
    }

    public int insert(ProductRecord productRecord) {
        try {
            var insert = productDao.insert(productRecord);
            return insert;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public int update(ProductRecord productRecord) {
        try {
            var update = productDao.update(productRecord);
            return update;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public int delete(int id) {
        try {
            var delete = productDao.delete(id);
            return delete;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
