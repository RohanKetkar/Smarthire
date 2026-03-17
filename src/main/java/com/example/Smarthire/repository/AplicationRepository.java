package com.example.Smarthire.repository;

import com.example.Smarthire.entity.Aplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AplicationRepository extends JpaRepository<Aplication, Long> {

    List<Aplication> findByCandidateId(Long candidateId);

    List<Aplication> findByJobId(Long jobId);
    Optional<Aplication> findByCandidateIdAndJobId(Long userid , Long jobid);
@Query(
        """
                Select j from Aplication j 
                                where j.candidate.id = :userid
                """
)
    List<Aplication> getAplicationByUserId(Long userid);


}
