package com.tj.inventorySpringBoot.repository;



import com.tj.inventorySpringBoot.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// import java.awt.print.Pageable;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUserNameIgnoreCase(String userName);

    Optional<User> findByUserName(String userName);
    boolean existsByEmailIgnoreCase(String email);

    boolean existsByUserName(String username);

    boolean existsByEmail(String email);

    // Page<User> findAll(Pageable pageable);



//    Page<User> findByUserNameContainingIgnoreCaseOrUserFirstNameContainingIgnoreCaseOrUserLastNameContainingIgnoreCaseOrUserEmailContainingIgnoreCase
//            (String userName, String firstName, String lastName, String email, Pageable pageable);

}