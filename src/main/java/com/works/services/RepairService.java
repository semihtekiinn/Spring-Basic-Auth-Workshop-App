package com.works.services;

import com.works.utils.EnumStatus;
import com.works.utils.REnum;
import com.works.entities.Repair;
import com.works.repositories.RepairRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RepairService {

    final RepairRepository repairRepository;

    public RepairService(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
    }

    public ResponseEntity save(Repair repair){
        Map<REnum, Object> hm = new LinkedHashMap<>();
        repairRepository.save(repair);
        hm.put(REnum.status, true);
        hm.put(REnum.result, repair);

        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity listAll(){
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, true);
        hm.put(REnum.result, repairRepository.findAll());
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity listByStatus(EnumStatus enumStatus) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<Repair> list = repairRepository.findByStatusEquals(enumStatus);
        hm.put(REnum.status, true);
        hm.put(REnum.result, list);
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity productUpdate(Repair repair){
        Map<REnum, Object> hm = new LinkedHashMap<>();
        Optional<Repair> optionalRepair = repairRepository.findByRidEquals(repair.getRid());
        if (optionalRepair.isPresent()){
            repairRepository.saveAndFlush(repair);
            hm.put(REnum.status, true);
            hm.put(REnum.result, repair);
            return new ResponseEntity(hm, HttpStatus.OK);
        }else {
            hm.put(REnum.status, false);
            hm.put(REnum.message, "There is no product with this id.");
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }
    }
}
