package com.whiterabbits.bpevolution.dao;

import com.whiterabbits.bpevolution.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
