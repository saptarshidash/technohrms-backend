package com.saptarshi.technohrms.repository.user;

import com.saptarshi.technohrms.entity.Employee;
import com.saptarshi.technohrms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String userName);

    boolean existsByEmployee(Employee employee);

    boolean existsByUsername(String username);


}
