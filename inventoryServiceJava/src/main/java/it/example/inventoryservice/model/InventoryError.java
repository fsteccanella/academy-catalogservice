package it.example.inventoryservice.model;

/**
 * InventoryError
 */
public class InventoryError {

    private String error;

    public InventoryError(String error){
        this.error=error;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }
}