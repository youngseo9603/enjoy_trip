package com.example.trip.controller;

import com.example.trip.domain.AttractionInfo;

import com.example.trip.service.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attraction")
public class AttractionController {

    @Autowired
    private AttractionService attractionService;

    @GetMapping
    public ResponseEntity<?> getAttractions(){
        List<AttractionInfo> list = attractionService.attractionInfoList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/a01")
    public ResponseEntity<?> getA01(@RequestHeader int pageNum){
        List<AttractionInfo> list = attractionService.cat1attractionInfoList(pageNum);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/a02")
    public ResponseEntity<?> getA02(@RequestHeader int pageNum){
        List<AttractionInfo> list = attractionService.cat2attractionInfoList(pageNum);
        return ResponseEntity.ok(list);
    }

}
