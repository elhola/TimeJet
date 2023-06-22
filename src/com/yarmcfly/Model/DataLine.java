package com.yarmcfly.Model;

import java.time.LocalDate;

/* класс данных, представляющий строку данных о времени ожидания. 
В нем хранится услуга, тип вопроса, тип ответа, дата и время */
public class DataLine {
    private String[] service;
    private String[] questionType;
    private char responseType;
    private LocalDate date;
    private int time;

    public DataLine(String[] service, String[] questionType, char responseType, LocalDate date, int time) {
        this.service = service;
        this.questionType = questionType;
        this.responseType = responseType;
        this.date = date;
        this.time = time;
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

    public LocalDate getDate() {
        return date;
    }

    public int getTime() {
        return time;
    }
}