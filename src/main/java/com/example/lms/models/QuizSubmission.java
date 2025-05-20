package com.example.lms.models;
import java.util.List;
import java.util.Map;

public class QuizSubmission {
    private List<QuestionModel> questions;
    private Map<Long, String> submittedAnswers; // Question ID → Answer

    public List<QuestionModel > getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionModel> questions) {
        this.questions = questions;
    }

    public Map<Long, String> getSubmittedAnswers() {
        return submittedAnswers;
    }

    public void setSubmittedAnswers(Map<Long, String> submittedAnswers) {
        this.submittedAnswers = submittedAnswers;
    }

    public String getAnswer(Long questionId) {
        return submittedAnswers.get(questionId);
    }
}
