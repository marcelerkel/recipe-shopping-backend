package com.musthavecaffeine.recipeapp.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.musthavecaffeine.recipeapp.api.v1.mapper.IngredientMapper;
import com.musthavecaffeine.recipeapp.api.v1.model.IngredientDTO;
import com.musthavecaffeine.recipeapp.bootstrap.SpringJpaBootstrap;
import com.musthavecaffeine.recipeapp.domain.Ingredient;
import com.musthavecaffeine.recipeapp.repositories.IngredientRepository;
import com.musthavecaffeine.recipeapp.repositories.IngredientRowRepository;
import com.musthavecaffeine.recipeapp.repositories.RecipeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IngredientServiceImplIntegrationTest {

	@Autowired
	IngredientRepository ingredientRepository;
	
	@Autowired
	IngredientRowRepository ingredientRowRepository;
	
	@Autowired
	RecipeRepository recipeRepository;

	IngredientService ingredientService;
	
	@Before
	public void setUp() throws Exception {
		SpringJpaBootstrap bootstrap = new SpringJpaBootstrap(ingredientRepository, ingredientRowRepository, recipeRepository);
		bootstrap.run();
		
		ingredientService = new IngredientServiceImpl(IngredientMapper.INSTANCE, ingredientRepository);
	}
	
	@Test
	public void getIngredientByName() {
		long id = getIngredientIdValue();
		
		Ingredient ingredient = ingredientRepository.getOne(id);
		assertNotNull(ingredient);
		
		IngredientDTO ingredientDto = ingredientService.getIngredientByName(ingredient.getName());
		assertNotNull(ingredientDto);
		
		assertEquals(ingredient.getId(), ingredientDto.getId());
		assertEquals(ingredient.getName(), ingredientDto.getName());
	}
	
	private Long getIngredientIdValue() {
		List<Ingredient> ingredients = ingredientRepository.findAll();
		
		// return first id
		return ingredients.get(0).getId();
	}
}
