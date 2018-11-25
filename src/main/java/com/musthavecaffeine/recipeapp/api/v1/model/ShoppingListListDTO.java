package com.musthavecaffeine.recipeapp.api.v1.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingListListDTO {
	List<ShoppingListDTO> shoppinglists;
}