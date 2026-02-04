package com.ecommerce.service;

import com.ecommerce.exception.ItemNotFoundException;
import com.ecommerce.model.Item;
import com.ecommerce.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Item Service Layer
 * 
 * This service class contains the business logic for item operations.
 * It acts as an intermediary between the controller and repository layers.
 * 
 * Responsibilities:
 * - Orchestrating business operations
 * - Calling repository methods
 * - Handling business exceptions
 * 
 * @author Sanjana Kuhite
 * @version 1.0.0
 */
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * Constructor injection for ItemRepository.
     * 
     * @param itemRepository The repository for item data operations
     */
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * Adds a new item to the store.
     * 
     * Business Logic:
     * - Validates input (handled by controller via annotations)
     * - Delegates persistence to repository
     * - Returns the created item with generated ID
     * 
     * @param item The item to add (without ID)
     * @return The created item with generated ID and timestamps
     */
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    /**
     * Retrieves an item by its unique ID.
     * 
     * @param id The ID of the item to retrieve
     * @return The found item
     * @throws ItemNotFoundException if no item exists with the given ID
     */
    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(
                        "Item not found with ID: " + id));
    }

    /**
     * Retrieves all items from the store.
     * 
     * @return List of all items (empty list if none exist)
     */
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    /**
     * Gets the total count of items in the store.
     * 
     * @return The number of items
     */
    public long getItemCount() {
        return itemRepository.count();
    }
}
