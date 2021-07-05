package com.saptarshi.technohrms.repository.training;

import com.saptarshi.technohrms.entity.Employee;
import com.saptarshi.technohrms.entity.Training;
import com.saptarshi.technohrms.entity.TrainingEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface TrainingEmployeeRepository extends JpaRepository<TrainingEmployee, Integer> {

    boolean existsByEmployeeAndTraining(Employee employee, Training training);

    List<TrainingEmployee> findAllByEmployee(Employee employee);

    List<TrainingEmployee> findAllByTraining(Training training);

    List<TrainingEmployee> findAllByCompletionStatus(boolean status);
}
