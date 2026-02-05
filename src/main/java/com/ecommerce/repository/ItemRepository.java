package com.ecommerce.repository;

import com.ecommerce.model.Item;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ItemRepository {

    private final List<Item> items = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(0);
    
    public Item save(Item item) {
       
        item.setId(idCounter.incrementAndGet());
        
        LocalDateTime now = LocalDateTime.now();
        item.setCreatedAt(now);
        item.setUpdatedAt(now);
        
        items.add(item);
        
        return item;
    }

    public Optional<Item> findById(Long id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    public List<Item> findAll() {
        return new ArrayList<>(items);
    }
    
    public boolean existsById(Long id) {
        return items.stream()
                .anyMatch(item -> item.getId().equals(id));
    }

    public long count() {
        return items.size();
    }

    public void deleteAll() {
        items.clear();
        idCounter.set(0);
    }
}
