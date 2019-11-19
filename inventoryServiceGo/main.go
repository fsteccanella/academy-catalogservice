package main

import (
	"encoding/json"
	"log"
	"math"
	"math/rand"
	"net/http"
	"strconv"

	"github.com/gorilla/mux"
)

type InventoryItem struct {
	ItemId   int64   `json:"itemId"`
	Quantity float64 `json:"quantity"`
}

type InventoryError struct {
	Err string `json:"error"`
}

func inventoryHandler(w http.ResponseWriter, r *http.Request) {
	itemId, err := strconv.ParseInt(mux.Vars(r)["itemId"], 10, 64)

	if err != nil {
		json.NewEncoder(w).Encode(InventoryError{Err: err.Error()})
		return
	}

	item := InventoryItem{ItemId: itemId}
	item.Quantity = math.Round((rand.Float64()*100)*100) / 100

	json.NewEncoder(w).Encode(item)
}

func main() {
	router := mux.NewRouter().StrictSlash(true)
	router.HandleFunc("/inventory/{itemId}", inventoryHandler)
	log.Fatal(http.ListenAndServe(":8080", router))
}
