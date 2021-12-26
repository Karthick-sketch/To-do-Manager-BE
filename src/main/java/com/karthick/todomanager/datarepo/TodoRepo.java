package com.karthick.todomanager.datarepo;

import com.karthick.todomanager.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<Todo, Integer> {}
