package org.cognizant.repo;

import org.cognizant.api.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends CrudRepository<Subject, Long> {

    @Query("SELECT s FROM Subject s WHERE s.subBookIdentity.subjectId = :subjectId")
    List<Subject> findBySubjectId(@Param("subjectId") long subjectId);
}
