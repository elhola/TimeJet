package com.yarmcfly.Model;

import java.time.LocalDate;

/* класс данных, представляющий запрос. 
Он хранит службу,тип вопроса, тип ответа и диапазон дат.
используется для сопоставления строк данных временной шкалы */
public class Query {
    private String[] service;
    private String[] questionType;
    private char responseType;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public Query(String[] service, String[] questionType, char responseType, LocalDate dateFrom, LocalDate dateTo) {
        this.service = service;
        this.questionType = questionType;
        this.responseType = responseType;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String[] getService() {
        return service;
    }

    public String[] getQuestionType() {
        return questionType;
    }

    public char getResponseType() {
        return responseType;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }
}