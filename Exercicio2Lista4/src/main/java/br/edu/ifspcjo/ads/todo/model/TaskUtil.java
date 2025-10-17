package br.edu.ifspcjo.ads.todo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskUtil {

	public List<Task> getTaskList(String task){
		List<Task> taskList = new ArrayList<>();
		if(task.length() > 0) {
			String[] lines = task.split("\n");
			for(int i = 0; i < lines.length; i++) {
				String[] data = lines[i].split("\\s*;\\s*");
				if(data.length == 3){
					Task tarefa = new Task();
					tarefa.setId(Integer.parseInt(data[0]));
					tarefa.setDescription(data[1]);
					tarefa.setDate(LocalDate.parse(data[2]));
					taskList.add(tarefa);
				}
			}
		}
		return taskList;
	}
}