package com.saptarshi.technohrms.repository.training;

import com.saptarshi.technohrms.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {

    boolean existsByName(String name);
}
