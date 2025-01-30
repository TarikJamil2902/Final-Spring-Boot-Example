package com.tj.inventorySpringBoot.repository;


import com.tj.inventorySpringBoot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {

    boolean existsByRoleNameIgnoreCase(String roleName);
    Optional<Role> findByRoleName(String roleName);

}
