package com.ecommerce.model;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Item Model Class
 * 
 * Represents an e-commerce item with all its properties.
 * This class includes validation annotations to ensure data integrity.
 * 
 * Properties:
 * - id: Unique identifier for the item (auto-generated)
 * - name: Name of the item (required, 2-100 characters)
 * - description: Detailed description of the item (required, 10-1000
 * characters)
 * - price: Price of the item (required, must be positive)
 * - category: Category the item belongs to (required)
 * - quantity: Available stock quantity (required, must be non-negative)
 * - imageUrl: URL to the item's image (optional)
 * - createdAt: Timestamp when the item was created
 * - updatedAt: Timestamp when the item was last updated
 * 
 * @author Sanjana Kuhite
 * @version 1.0.0
 */
public class Item {

    /**
     * Unique identifier for the item.
     * Auto-generated when a new item is added.
     */
    private Long id;

    /**
     * Name of the item.
     * Must be between 2 and 100 characters.
     */
    @NotBlank(message = "Item name is required")
    @Size(min = 2, max = 100, message = "Item name must be between 2 and 100 characters")
    private String name;

    /**
     * Detailed description of the item.
     * Must be between 10 and 1000 characters.
     */
    @NotBlank(message = "Item description is required")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;

    /**
     * Price of the item in the default currency.
     * Must be a positive value.
     */
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Price must have at most 10 integer digits and 2 decimal places")
    private BigDecimal price;

    /**
     * Category the item belongs to.
     * Examples: Electronics, Clothing, Books, etc.
     */
    @NotBlank(message = "Category is required")
    @Size(min = 2, max = 50, message = "Category must be between 2 and 50 characters")
    private String category;

    /**
     * Available stock quantity.
     * Must be zero or greater.
     */
    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    /**
     * URL to the item's image.
     * Optional field.
     */
    @Pattern(regexp = "^(https?://.*)?$", message = "Image URL must be a valid URL or empty")
    private String imageUrl;

    /**
     * Timestamp when the item was created.
     * Auto-set when item is added.
     */
    private LocalDateTime createdAt;

    /**
     * Timestamp when the item was last updated.
     * Auto-updated when item is modified.
     */
    private LocalDateTime updatedAt;

    // Default constructor
    public Item() {
    }

    // All-args constructor
    public Item(Long id, String name, String description, BigDecimal price,
            String category, Integer quantity, String imageUrl,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", imageUrl='" + imageUrl + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
