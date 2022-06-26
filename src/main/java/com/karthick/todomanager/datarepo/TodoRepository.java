package com.karthick.todomanager.datarepo;

import com.karthick.todomanager.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    @Query(value = "select * from todos where user_id = :user_id", nativeQuery = true)
    List<Todo> findByUserId(int user_id);
}
