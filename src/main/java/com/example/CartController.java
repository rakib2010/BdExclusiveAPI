
package com.example;

import com.example.model.Cart;
import com.example.storage.service.FileStorageService;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CartController {
    @Autowired
    SessionFactory sessionFactory;
    
    @Autowired
    private FileStorageService fileStorageService;
    
    
    @PostMapping(value = "/cart/save", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<?> save(@RequestBody Cart entity) {
        try {
            
            Session session = sessionFactory.openSession();
            session.save(entity);
            session.flush();
            session.close();
            return ResponseEntity.ok("Data saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Save failed");
        }
    }
     
    
    
    
    
    @GetMapping(value = "/cart/getAll")
    public ResponseEntity<?> getAll() {
        try {
            Session session = sessionFactory.openSession();
            List<Cart> entityList = session.createQuery("From Cart").list();
            session.flush();
            session.close();
            return ResponseEntity.ok(entityList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Data fetch failed!");
        }
    }
    
    
    
    @GetMapping(value = "/cart/delete/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<?> deleteCart(@PathVariable(value = "id") long id) {
        try {
            Session session = sessionFactory.openSession();
            Cart entity = session.get(Cart.class, id);
            session.delete(entity);
            session.flush();
            session.close();
            return ResponseEntity.ok("Delete successful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Data fetch failed!");
        }
    }
     
     
    
    
    
}
