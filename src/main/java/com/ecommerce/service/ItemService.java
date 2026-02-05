package com.ecommerce.service;

import com.ecommerce.exception.ItemNotFoundException;
import com.ecommerce.model.Item;
import com.ecommerce.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(
                        "Item not found with ID: " + id));
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public long getItemCount() {
        return itemRepository.count();
    }
}
