package com.fireflies.mcqs.service;

import com.fireflies.mcqs.model.Mcq;

import java.util.List;

public interface McqService {
    List<Mcq> findAll();
    Mcq findAllById(Integer id);
    List<Mcq> findAllByQuesSet(String set);
    Mcq save(Mcq mcq);
    void delete(Mcq mcq);
    List<String> findDistinctQuesSet();

}
