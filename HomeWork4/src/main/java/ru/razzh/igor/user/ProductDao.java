package ru.razzh.igor.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createpRroductTable() {
        jdbcTemplate.execute("CREATE TABLE products (\n" +
                "  id SERIAL PRIMARY KEY, \n" +
                "  accountNumber VARCHAR (50) UNIQUE NOT NULL, \n" +
                "  balance DECIMAL NOT NULL, \n" +
                "  product_type VARCHAR (50) NOT NULL, \n" +
                "  user_id SERIAL NOT NULL);");
    }

    public void insertProduct(String accountNumber, BigDecimal balance, String productType, long userId) {
        String sql = "INSERT INTO products (accountNumber, balance, product_type, user_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, accountNumber, balance, productType, userId);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Product getById(long id) {
        String sql = "SELECT id, accountNumber, balance, product_type, user_id FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Product(rs.getLong("id"),
                rs.getString("accountNumber"), rs.getBigDecimal("balance"),
                rs.getString("product_type"), rs.getLong("user_id")), id);
    }

    public List<Product> getAllproductByUserId(Long userId) {
            String sql = "select * from products where user_id = ?";
            List<Map<String,Object>> list = jdbcTemplate.queryForList(sql, userId.toString());
            List<Product> plist = new ArrayList<>();
            list.forEach(m -> {
                Product p = new Product(Long.valueOf((int)m.get("id")), (String) m.get("accountNumber"),
                        (BigDecimal) m.get("balance"), (String) m.get("product_type"), Long.valueOf((int)m.get("user_id")));
                plist.add(p);
            });
            return plist;
        }
    }
