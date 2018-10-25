package id.wiryawan.redistutorial.controller;

import id.wiryawan.redistutorial.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedisDemoController {

  private static final String REDIS_INDEX_KEY = "Product";

  @Autowired RedisTemplate<String, Object> redisTemplate;

  @PostMapping("/products")
  public String createProduct(@RequestBody Product product) {

    redisTemplate.opsForHash().put(REDIS_INDEX_KEY, product.getProductId(), product.toString());
    return "Product is saved successfully";
  }

  @GetMapping("/products")
  public ResponseEntity getProducts() {
    return new ResponseEntity(redisTemplate.opsForHash().entries(REDIS_INDEX_KEY), HttpStatus.OK);
  }

  @PutMapping("/products/{id}")
  public String updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
    redisTemplate.opsForHash().put(REDIS_INDEX_KEY, product.getProductId(), product.toString());
    return "Product is updated successfuly";
  }

  @DeleteMapping("/products/{id}")
  public String deleteProduct(@PathVariable("id") String id) {
    redisTemplate.opsForHash().delete(REDIS_INDEX_KEY, id);
    return "Product is deleted successfully";
  }

  @GetMapping("/products/{id}")
  public Object getProductById(@PathVariable("id") String id) {
    return redisTemplate.opsForHash().get(REDIS_INDEX_KEY, id);
  }
}
