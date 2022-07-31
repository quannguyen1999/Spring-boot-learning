package com.example.springbatch.repository;

import com.example.springbatch.model.Tutorials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialRepository extends JpaRepository<Tutorials, Integer> {
}
