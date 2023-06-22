package com.yarmcfly.Input;

import java.time.LocalDate;
import java.util.List;

import com.yarmcfly.Model.DataLine;
import com.yarmcfly.Model.Query;
import com.yarmcfly.Service.Main;
import com.yarmcfly.Util.Utility;

public class Input {
	public Utility data;

	public Input(Utility data) {
		this.data = data;
	}

	//метод получает список входных строк и обрабатывает каждую строку
	public void processInput(Main main, List<String> inputLines) {
	    for (String line : inputLines) {
	        String[] parts = line.trim().split(" ");
	        if (parts.length < 1) {
	            continue;
	        }
	
	        String command = parts[0];
	        switch (command) {
	            case "C":
	                main.data.processWaitingTimeline(main, parts);
	                break;
	            case "D":
	                main.data.processQuery(main, parts);
	                break;
	            default:
	                break;
	        }
	    }
	}

	/* метод обрабатывает команду шкалы ожидания. Он извлекает услугу, 
    тип вопроса, тип ответа, дату и время из входящих данных и создает объект DataLine*/
	public void processWaitingTimeline(Main main, String[] parts) {
	    if (parts.length < 6) {
	        throw new IllegalArgumentException("Invalid input format for waiting timeline");
	    }
	
	    String[] service = parts[1].split("\\.");
	    String[] questionType = parts[2].split("\\.");
	    char responseType = parts[3].charAt(0);
	
	    LocalDate date = LocalDate.parse(parts[4], Main.DATE_FORMATTER);
	    int time = Integer.parseInt(parts[5]);
	
	    DataLine dataLine = new DataLine(service, questionType, responseType, date, time);
	    data.data.add(dataLine);
	}

	/* метод обрабатывает команду запроса. Он извлекает 
    услугу, тип вопроса, тип ответа и диапазон дат из входящих данных и создает объект Query*/
	public void processQuery(Main main, String[] parts) {
	    if (parts.length < 5) {
	        throw new IllegalArgumentException("Invalid input format for query");
	    }
	
	    String[] service = parts[1].split("\\.");
	    String[] questionType = parts[2].split("\\.");
	    char responseType = parts[3].charAt(0);
	    String[] dateRange = parts[4].split("-");
	
	    LocalDate dateFrom = LocalDate.parse(dateRange[0], Main.DATE_FORMATTER);
	    LocalDate dateTo = null;
	    if (dateRange.length > 1) {
	        dateTo = LocalDate.parse(dateRange[1], Main.DATE_FORMATTER);
	    }
	
	    /* объект Query используется для вызова метода calculateAverageWaitingTime
	    для вычисления среднего ожидания.*/
	    Query query = new Query(service, questionType, responseType, dateFrom, dateTo);
	    String averageWaitingTime = main.calculateAverageWaitingTime(query);
	    System.out.println(averageWaitingTime);
	}
}