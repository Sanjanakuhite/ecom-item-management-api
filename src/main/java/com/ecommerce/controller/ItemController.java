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

@RestController
@RequestMapping("/api/items")
@Tag(name = "Item Management", description = "API endpoints for managing e-commerce items")
@CrossOrigin(origins = "*") // Enable CORS for all origins
public class ItemController {

    private final ItemService itemService;

   
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

  
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
