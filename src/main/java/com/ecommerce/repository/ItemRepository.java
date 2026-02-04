package com.ecommerce.repository;

import com.ecommerce.model.Item;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-Memory Item Repository
 * 
 * This class implements an in-memory data store using ArrayList
 * to store and manage items. It provides CRUD operations for items.
 * 
 * Thread Safety: Uses AtomicLong for ID generation to ensure thread-safe
 * ID assignment in concurrent environments.
 * 
 * @author Sanjana Kuhite
 * @version 1.0.0
 */
@Repository
public class ItemRepository {

    /**
     * In-memory storage for items using ArrayList.
     * This simulates a database table.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Atomic counter for generating unique item IDs.
     * Starts at 0 and increments for each new item.
     */
    private final AtomicLong idCounter = new AtomicLong(0);

    /**
     * Saves a new item to the data store.
     * Automatically assigns an ID and timestamps.
     * 
     * @param item The item to save (without ID)
     * @return The saved item with generated ID and timestamps
     */
    public Item save(Item item) {
        // Generate unique ID
        item.setId(idCounter.incrementAndGet());
        
        // Set timestamps
        LocalDateTime now = LocalDateTime.now();
        item.setCreatedAt(now);
        item.setUpdatedAt(now);
        
        // Add to the in-memory list
        items.add(item);
        
        return item;
    }

    /**
     * Finds an item by its unique ID.
     * 
     * @param id The ID of the item to find
     * @return Optional containing the item if found, empty otherwise
     */
    public Optional<Item> findById(Long id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    /**
     * Retrieves all items from the data store.
     * 
     * @return List of all items (returns new ArrayList to prevent external modification)
     */
    public List<Item> findAll() {
        return new ArrayList<>(items);
    }

    /**
     * Checks if an item exists with the given ID.
     * 
     * @param id The ID to check
     * @return true if item exists, false otherwise
     */
    public boolean existsById(Long id) {
        return items.stream()
                .anyMatch(item -> item.getId().equals(id));
    }

    /**
     * Returns the total count of items in the store.
     * 
     * @return The number of items
     */
    public long count() {
        return items.size();
    }

    /**
     * Deletes all items from the store.
     * Useful for testing purposes.
     */
    public void deleteAll() {
        items.clear();
        idCounter.set(0);
    }
}
