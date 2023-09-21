package com.tonde.maisonchapback.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/admin")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {


    /**
     * {code} GET /api/admin/authorities
     * @return List<String> tous les roles
     */
   @GetMapping("authorities")
   public List<String> roles(){
         return List.of("ROLE_ADMIN", "ROLE_FREE_USER", "ROLE_FREE_PROPRIO", "ROLE_PREMIUM_USER", "ROLE_STANDARD_USER", "ROLE_STANDARD_PROPRIO");
   }




}
