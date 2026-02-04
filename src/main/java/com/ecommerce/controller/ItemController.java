package com.ecommerce.controller;

import com.ecommerce.dto.ApiResponse;
import com.ecommerce.model.Item;
import com.ecommerce.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Item REST Controller
 * 
 * This controller exposes RESTful API endpoints for managing items.
 * It handles HTTP requests and delegates business logic to the service layer.
 * 
 * Base URL: /api/items
 * 
 * Endpoints:
 * - POST /api/items - Add a new item
 * - GET /api/items/{id} - Get an item by ID
 * - GET /api/items - Get all items (bonus endpoint)
 * 
 * @author Sanjana Kuhite
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/items")
@Tag(name = "Item Management", description = "API endpoints for managing e-commerce items")
@CrossOrigin(origins = "*") // Enable CORS for all origins
public class ItemController {

    private final ItemService itemService;

    /**
     * Constructor injection for ItemService.
     * 
     * @param itemService The service for item business operations
     */
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Add a New Item
     * 
     * Creates a new item in the system with the provided details.
     * All required fields must be present and valid.
     * 
     * HTTP Method: POST
     * URL: /api/items
     * Request Body: Item JSON object
     * 
     * @param item The item details from request body (validated)
     * @return ResponseEntity with created item and HTTP 201 status
     */
    @PostMapping
    @Operation(summary = "Add a new item", description = "Creates a new item in the e-commerce catalog. " +
            "Required fields: name, description, price, category, quantity. " +
            "Optional: imageUrl.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Item created successfully", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input - validation error", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    public ResponseEntity<ApiResponse<Item>> addItem(
            @Valid @RequestBody Item item) {

        Item createdItem = itemService.addItem(item);

        ApiResponse<Item> response = ApiResponse.<Item>builder()
                .success(true)
                .message("Item created successfully")
                .data(createdItem)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Get Item by ID
     * 
     * Retrieves a single item from the system using its unique identifier.
     * 
     * HTTP Method: GET
     * URL: /api/items/{id}
     * Path Variable: id - The unique item ID
     * 
     * @param id The unique identifier of the item
     * @return ResponseEntity with the found item and HTTP 200 status
     * @throws ItemNotFoundException if item doesn't exist (returns 404)
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get item by ID", description = "Retrieves a single item from the catalog using its unique identifier.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Item found successfully", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Item not found", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    public ResponseEntity<ApiResponse<Item>> getItemById(
            @Parameter(description = "ID of the item to retrieve", required = true) @PathVariable Long id) {

        Item item = itemService.getItemById(id);

        ApiResponse<Item> response = ApiResponse.<Item>builder()
                .success(true)
                .message("Item retrieved successfully")
                .data(item)
                .build();

        return ResponseEntity.ok(response);
    }

    /**
     * Get All Items (Bonus Endpoint)
     * 
     * Retrieves all items from the system.
     * Useful for listing all available products.
     * 
     * HTTP Method: GET
     * URL: /api/items
     * 
     * @return ResponseEntity with list of all items and HTTP 200 status
     */
    @GetMapping
    @Operation(summary = "Get all items", description = "Retrieves all items from the e-commerce catalog.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Items retrieved successfully", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    public ResponseEntity<ApiResponse<List<Item>>> getAllItems() {
        List<Item> items = itemService.getAllItems();

        ApiResponse<List<Item>> response = ApiResponse.<List<Item>>builder()
                .success(true)
                .message("Retrieved " + items.size() + " item(s)")
                .data(items)
                .build();

        return ResponseEntity.ok(response);
    }
}
