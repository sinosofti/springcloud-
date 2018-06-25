package com.forezp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.forezp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
