package com.works.services;

import com.works.utils.REnum;
import com.works.entities.Role;
import com.works.repositories.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class RoleService {

    final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ResponseEntity save(Role role){
        Map<REnum, Object> hm = new LinkedHashMap<>();
        roleRepository.save(role);
        hm.put(REnum.status, true);
        hm.put(REnum.message, "Role saved.");
        hm.put(REnum.result, role);
        return new ResponseEntity(hm, HttpStatus.OK);
    }

}
