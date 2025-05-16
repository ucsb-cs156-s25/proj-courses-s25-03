package edu.ucsb.cs156.courses.repositories;

import edu.ucsb.cs156.courses.entities.Job;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface JobsRepository extends JpaRepository<Job, Long> {
}