package tarefas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tarefas.model.Tarefa;
import tarefas.model.TarefaService;

@Controller
public class TarefaController {

	@RequestMapping("novaTarefa")
	public String form() {
		return "tarefa/tarefa-formulario";
	}

	@RequestMapping("adicionaTarefa")
	public String adiciona(Tarefa tarefa) {
		TarefaService dao = new TarefaService();
		dao.addTarefa(tarefa);

		return "tarefa/tarefa-adicionada";
	}
}