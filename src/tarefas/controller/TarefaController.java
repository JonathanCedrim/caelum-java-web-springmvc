package tarefas.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import tarefas.model.Tarefa;
import tarefas.model.TarefaService;

@Controller
public class TarefaController {

	@RequestMapping("novaTarefa")
	public String form() {
		return "tarefa/formulario";
	}

	@RequestMapping("adicionaTarefa")
	public String adiciona(@Valid Tarefa tarefa, BindingResult result) {
		if (result.hasFieldErrors("descricao")) {
			return "tarefa/formulario";
		}

		TarefaService dao = new TarefaService();
		dao.addTarefa(tarefa);

		return "tarefa/adicionada";
	}

	@RequestMapping("listaTarefas")
	public String lista(Model model) {
		model.addAttribute("tarefas", new TarefaService().getTarefas());
		return "tarefa/lista";
	}
}