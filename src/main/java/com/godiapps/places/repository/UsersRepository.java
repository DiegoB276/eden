package com.godiapps.places.repository;
import com.godiapps.places.entity.Account;
import com.godiapps.places.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

}
