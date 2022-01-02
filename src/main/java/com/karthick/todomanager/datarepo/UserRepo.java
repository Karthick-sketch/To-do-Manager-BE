package com.karthick.todomanager.datarepo;

import com.karthick.todomanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {}
