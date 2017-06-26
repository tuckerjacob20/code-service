package com.e451.rest.services.impl;

import com.e451.rest.domains.question.Question;
import com.e451.rest.repositories.QuestionRepository;
import com.e451.rest.services.AuthService;
import com.e451.rest.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by e384873 on 6/9/2017.
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;
    private AuthService authService;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, AuthService authService) {
        this.questionRepository = questionRepository;
        this.authService = authService;
    }

    @Override
    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question getQuestion(String id) {
        return questionRepository.findOne(id);
    }

    @Override
    @SuppressWarnings("Duplicates")
    public Question createQuestion(Question question) {
        question.setCreatedDate(new Date());
        question.setModifiedDate(new Date());

        question.setCreatedBy(authService.getActiveUser());
        question.setModifiedBy(authService.getActiveUser());

        return questionRepository.insert(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        question.setModifiedDate(new Date());
        question.setModifiedBy(authService.getActiveUser());
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(String id) {
        questionRepository.delete(id);
    }
}
