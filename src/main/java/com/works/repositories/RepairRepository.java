package com.works.repositories;

import com.works.entities.Repair;
import com.works.utils.EnumStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepairRepository extends JpaRepository<Repair, Integer> {

    Optional<Repair> findByRidEquals(Integer rid);

    List<Repair> findByStatusEquals(EnumStatus status);

}