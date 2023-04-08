package com.works.restcontrollers;

import com.works.entities.Repair;
import com.works.services.RepairService;
import com.works.utils.EnumStatus;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/repair")
public class RepairRestController {

    final RepairService repairService;
    final CacheManager cacheManager;

    public RepairRestController(RepairService repairService, CacheManager cacheManager) {
        this.repairService = repairService;
        this.cacheManager = cacheManager;
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Repair repair){
        cacheManager.getCache("repairList").clear();
        return repairService.save(repair);
    }

    @Cacheable("repairList")
    @GetMapping("/listAll")
    public ResponseEntity listAll(){
        return repairService.listAll();
    }

    @Cacheable("repairListByStatus")
    @GetMapping("/listByStatus")
    public ResponseEntity listByStatus(@RequestParam EnumStatus enumStatus){
        cacheManager.getCache("repairListByStatus").clear();
        return repairService.listByStatus(enumStatus);
    }

    @PutMapping("/productUpdate")
    public ResponseEntity productUpdate(@RequestBody Repair repair){
        return repairService.productUpdate(repair);
    }
}
