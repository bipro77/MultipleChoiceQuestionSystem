package com.fireflies.mcqs.repository;

import com.fireflies.mcqs.model.Mcq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface McqRepository  extends JpaRepository<Mcq, Long> {

    List<Mcq>  findAll();

    Mcq findAllById(Integer id);

    List<Mcq> findAllByQuesSet(String set);

    Mcq save(Mcq mcq);

    void delete(Mcq mcq);

    @Query("SELECT DISTINCT quesSet FROM Mcq")
    List<String> findDistinctQuesSet(); //mcqForm e update lagbe
}
