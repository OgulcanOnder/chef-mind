package com.ogulcanonder.chef_mind.controller;

import com.ogulcanonder.chef_mind.dto.request.DtoIngredientRequest;
import com.ogulcanonder.chef_mind.dto.response.DtoIngredientResponse;
import com.ogulcanonder.chef_mind.service.IIngredientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingredient")
public class IngredientController {

    private final IIngredientService ingredientService;

    public IngredientController(IIngredientService ingredientService){
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<DtoIngredientResponse>createIngredient(@Valid @RequestBody DtoIngredientRequest dtoIngredientRequest){
        DtoIngredientResponse saveIngredient = ingredientService.createIngredient(dtoIngredientRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveIngredient);
    }

    @GetMapping
    public ResponseEntity<List<DtoIngredientResponse>> getAllIngredient(){
        List<DtoIngredientResponse> getAllIngredient=ingredientService.getAllIngredient();
        return ResponseEntity.status(HttpStatus.OK).body(getAllIngredient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoIngredientResponse>findByIngredientId(@PathVariable(name = "id")Long id){
        DtoIngredientResponse ingredientResponse=ingredientService.findByIngredient(id);
        return ResponseEntity.status(HttpStatus.OK).body(ingredientResponse);
    }

    @PutMapping("/{id}")
        public ResponseEntity<Void> updateIngredient (@Valid @RequestBody DtoIngredientRequest dtoIngredientRequest,
                @PathVariable (name = "id")Long id){
             ingredientService.updateIngredient(dtoIngredientRequest,id);
            return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteIngredient(@PathVariable(name = "id")Long id){
        ingredientService.deleteIngredient(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
