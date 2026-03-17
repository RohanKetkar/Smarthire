package com.example.Smarthire.repository;

import com.example.Smarthire.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByRecruiterId(Long RecruiterId); // In is job i is have User recruiter field in that id so spring will do recruiter.id
    Optional<Job> findByTitle(String title);
    @Query("""
            Select j from Job j 
            where (:title Is NULL or Lower(j.title) Like Lower(concat('%',:title,'%')))
                        AND (:location IS NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%')))
                        AND (:minSalary IS NULL OR j.salary >= :minSalary)
                        AND (:maxSalary IS NULL OR j.salary <= :maxSalary)
            """)
    List<Job> filterJobs(String title, String location, Double minSalary, Double maxSalary);

    @Query(
            """
 Select j from Job j
"""
    )
    List<Job> getAllJobs();


@Query("""

Select j from Job j
where j.recruiter.id = :recruiterid
""")
    List<Job> getjobbyrecruiterid(Long recruiterid);
}
