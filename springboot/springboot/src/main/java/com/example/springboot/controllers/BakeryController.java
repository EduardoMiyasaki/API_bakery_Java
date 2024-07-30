package com.example.springboot.controllers;

import com.example.springboot.Models.BakeryModel;
import com.example.springboot.dtos.BakeryRecordDto;
import com.example.springboot.repositories.BakeryRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class BakeryController {

    @Autowired
    BakeryRepository bakeryRepository;

    // injeção de dependências meio que instanciando

    // Metódo Post = receber dados,fazer validações e salvar na base de dados

    // esse productRecordDto @valid para ter as validações ditas no productRecordDto
    // depois você instancia um productModel
    // depois conversão de recordDto para model utilizando um recurso do próprio spring
    // no product Repository tem métodos default e esse productRespository que vai o productModel salvar no banco

    @PostMapping("/bakerys")
    public ResponseEntity<BakeryModel> saveBakery(@RequestBody @Valid BakeryRecordDto bakeryRecordDto){

        //instanciando um novo model
        var bakeryModel = new BakeryModel();
        // conversão de dto para model
        BeanUtils.copyProperties(bakeryRecordDto , bakeryModel);
        // salvando
        return ResponseEntity.status(HttpStatus.CREATED).body(bakeryRepository.save(bakeryModel));
    }

    @GetMapping("/bakers")
    public ResponseEntity<List<BakeryModel>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(bakeryRepository.findAll());
    }

    // <Object> pois vamos ter dois tipos de respostas
    // @PathVariable serve para você receber o id e ele ser passado pros parâmetros da função

    @GetMapping("/bakers/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id){

        Optional<BakeryModel> bakery0 = bakeryRepository.findById(id);

        if(bakery0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("bakery not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(bakery0.get());
    }

    // update
    @PutMapping("/bakers/{id}")
    public ResponseEntity<Object> updateBakery(@PathVariable(value = "id")UUID id, @RequestBody @Valid BakeryRecordDto bakeryRecordDto){

        Optional<BakeryModel> bakery0 = bakeryRepository.findById(id);

        if(bakery0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bakery not found");
        }

        var bakeryModel = bakery0.get();
        BeanUtils.copyProperties(bakeryRecordDto , bakeryModel);
        return ResponseEntity.status(HttpStatus.OK).body(bakeryRepository.save(bakeryModel));
    }

    @DeleteMapping("/bakers/{id}")
    public ResponseEntity<Object> deleteBakery(@PathVariable(value = "id")UUID id){

        Optional<BakeryModel> bakery0 = bakeryRepository.findById(id);

        if (bakery0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bakery not found");
        }

        bakeryRepository.delete(bakery0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Bakery has been deleted");
    }
}
