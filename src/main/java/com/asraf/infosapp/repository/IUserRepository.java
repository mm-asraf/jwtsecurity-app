package com.asraf.infosapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.asraf.infosapp.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {

}
