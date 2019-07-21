package com.whiterabbits.bpevolution.dao;

import com.whiterabbits.bpevolution.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
