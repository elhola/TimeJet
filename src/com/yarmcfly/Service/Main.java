package com.yarmcfly.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.yarmcfly.Input.*;
import com.yarmcfly.Model.*;
import com.yarmcfly.Util.*;

public class Main {
    public Input data = new Input(new Utility());
	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public Main() {
        data.data.data = new ArrayList<>();
    }

    // метод вычисляет среднее время ожидания на основе указанного запроса
    public String calculateAverageWaitingTime(Query query) {
        List<DataLine> matchingData = data.data.data.stream()
                .filter(dataLine -> matchesQuery(dataLine, query))
                .collect(Collectors.toList());

        if (matchingData.isEmpty()) {
            return "-";
        }

        int totalWaitingTime = matchingData.stream()
                .mapToInt(DataLine::getTime)
                .sum();

        int averageWaitingTime = totalWaitingTime / matchingData.size();
        return String.valueOf(averageWaitingTime);
    }

    // метод проверяет соответствует ли объект DataLine указанному запросу 
    private boolean matchesQuery(DataLine dataLine, Query query) {
        return data.data.matchesService(dataLine.getService(), query.getService())
                && data.data.matchesQuestionType(dataLine.getQuestionType(), query.getQuestionType())
                && dataLine.getResponseType() == query.getResponseType()
                && (query.getDateFrom() == null || !dataLine.getDate().isBefore(query.getDateFrom()))
                && (query.getDateTo() == null || !dataLine.getDate().isAfter(query.getDateTo()));
    }

    // точка входа в программу. Вызывает метод processInput, чтобы начать обработку 
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int numLines = Integer.parseInt(scanner.nextLine());
            List<String> inputLines = new ArrayList<>();
            for (int i = 0; i < numLines; i++) {
                inputLines.add(scanner.nextLine());
            }

            Main analyzer = new Main();
            analyzer.data.processInput(analyzer, inputLines);
        } catch (NumberFormatException e) {
            System.err.println("Invalid input format");
            e.printStackTrace();
        }
    }
}