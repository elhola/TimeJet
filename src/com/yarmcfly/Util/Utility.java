package com.yarmcfly.Util;

import java.util.List;

import com.yarmcfly.Model.DataLine;

public class Utility {
	public List<DataLine> data;

	public Utility() {
	}

	/* метод проверяет, соответствует ли служба объекта DataLine службе запроса.
	Он сравнивает компоненты службы с учетом подстановочных знаков (*) */
	public boolean matchesService(String[] dataService, String[] queryService) {
	    return (queryService[0].equalsIgnoreCase("*") || dataService[0].equals(queryService[0]))
	            && (queryService.length <= 1 || (dataService.length >= 2 && dataService[1].equals(queryService[1])));
	}

	public boolean matchesQuestionType(String[] dataQuestionType, String[] queryQuestionType) {
	    return (queryQuestionType[0].equalsIgnoreCase("*") || dataQuestionType[0].equals(queryQuestionType[0]))
	            && (queryQuestionType.length <= 1 || dataQuestionType[1].equals(queryQuestionType[1]))
	            && (queryQuestionType.length <= 2 || dataQuestionType[2].equals(queryQuestionType[2]));
	}
}