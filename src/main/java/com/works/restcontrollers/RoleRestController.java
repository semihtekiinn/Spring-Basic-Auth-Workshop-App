package com.works.restcontrollers;

import com.works.entities.Role;
import com.works.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleRestController {

    final RoleService roleService;

    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Role role){
        return roleService.save(role);
    }

}
