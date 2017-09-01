package com.webstar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webstar.models.UserSubmissions;

@Repository
public interface SubmissionRepository  extends JpaRepository<UserSubmissions, Long>
{
   
}
