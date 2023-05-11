package com.techelevator.dao;

import com.techelevator.model.GroceryList;

import java.util.List;

public interface GroceryListDao {
    List<GroceryList> getUserGroceryList (String username);
    GroceryList createGroceryList (String username, GroceryList groceryList);

}
