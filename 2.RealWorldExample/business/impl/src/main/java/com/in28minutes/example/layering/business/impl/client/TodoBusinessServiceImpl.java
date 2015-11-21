package com.in28minutes.example.layering.business.impl.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.in28minutes.example.layering.business.api.client.TodoBusinessService;
import com.in28minutes.example.layering.data.api.client.TodoDataService;
import com.in28minutes.example.layering.model.api.client.Todo;

@Component
public class TodoBusinessServiceImpl implements TodoBusinessService {

	@Autowired
	TodoDataService todoDs;

	@Override
	public List<Todo> retrieveTodosRelatedToSpring(String user) {
		List<Todo> todos = todoDs.retrieveTodos(user);
		return filterTodosRelatedToSpring(todos);
	}

	// NOTE : We get everything from database and filter
	// instead of filtering in the database. Not the most efficient
	// implementation.
	private List<Todo> filterTodosRelatedToSpring(List<Todo> todos) {
		Date today = new Date();

		List<Todo> filteredTodos = new ArrayList<Todo>();
		for (Todo todo : todos) {
			if (todo.getDesc().contains("Spring")) {
				filteredTodos.add(todo);
			}
		}

		return filteredTodos;
	}

}
