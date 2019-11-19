package it.example.inventoryservice.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import it.example.inventoryservice.model.InventoryError;
import it.example.inventoryservice.model.InventoryItem;

/**
 * InventoryController
 */
@RestController
public class InventoryController {


    @GetMapping("/inventory/{itemId}")
    public Object retrieveInventory(@PathVariable long itemId) {
        InventoryItem item = new InventoryItem();
        item.setItemId(itemId);
        double quantity = Math.random() * 100;
        item.setQuantity(quantity);
        return item;
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public InventoryError handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {

        return new InventoryError(ex.getMessage());
    }

}