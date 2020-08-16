package com.fireflies.mcqs.service;

import com.fireflies.mcqs.model.Mcq;
import com.fireflies.mcqs.repository.McqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class McqServiceImpl implements McqService{
    @Autowired
    private McqRepository mcqRepository;

    @Override
    public List<Mcq> findAll() {
        return mcqRepository.findAll();
    }

    @Override
    public Mcq findAllById(Integer id) {
        return mcqRepository.findAllById(id);
    }

    @Override
    public List<Mcq> findAllByQuesSet(String set) {
       return mcqRepository.findAllByQuesSet(set);
    }

    @Override
    public Mcq save(Mcq mcq) {
        return mcqRepository.save(mcq);
    }

    @Override
    public void delete(Mcq mcq) {
         mcqRepository.delete(mcq);
    }

    @Override
    public List<String> findDistinctQuesSet() {
        return mcqRepository.findDistinctQuesSet();
    }


}
