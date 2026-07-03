package com.example.demo.reposetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Compose;
@Repository
public interface ComposeRepo extends JpaRepository<Compose, Integer> {

    // USER
    long countByParentUkid(Integer parentUkid);
    

    long countByParentUkidAndStatusIgnoreCase(Integer parentUkid, String status);
    long countByParentUkidAndStatusContainingIgnoreCase(
            Integer parentUkid, String status);

    List<Compose> findTop5ByParentUkidOrderByIdDesc(Integer parentUkid);

    // HR
    long countByStatusContainingIgnoreCase(String status);

    List<Compose> findTop5ByOrderByIdDesc();
}
