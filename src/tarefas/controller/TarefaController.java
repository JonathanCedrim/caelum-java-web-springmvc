package tarefas.controller;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import tarefas.model.Tarefa;
import tarefas.model.TarefaService;

@Controller
public class TarefaController {

	private final TarefaService dao;
	
	@Autowired
	public TarefaController(TarefaService dao) {
		this.dao = dao;
	}

	@RequestMapping("novaTarefa")
	public String form() {
		return "tarefa/formulario";
	}

	@RequestMapping("adicionaTarefa")
	public String adiciona(@Valid Tarefa tarefa, BindingResult result) throws SQLException {
		if (result.hasFieldErrors("descricao")) {

			return "tarefa/formulario";
		}
		dao.addTarefa(tarefa);

		return "tarefa/adicionada";
	}

	@RequestMapping(value = {"listaTarefas", "" })
	public String lista(Model model) throws SQLException {
		model.addAttribute("tarefas", dao.getTarefas());

		return "tarefa/lista";
	}
	/*
	 * @RequestMapping("alteraTarefa") public String altera(@Valid Tarefa
	 * tarefa, BindingResult result) { if (result.hasFieldErrors()) {
	 * 
	 * return "tarefa/mostra"; } new TarefaService().alteraTarefa(tarefa);
	 * return "redirect:listaTarefa"; }
	 */

	@RequestMapping("mostraTarefa")
	public String mostra(Long id, Model model) throws SQLException {
		model.addAttribute("tarefa", dao.getTarefaById(id));

		return "tarefa/mostra";
	}

	@RequestMapping("removeTarefa")
	public String remove(Tarefa tarefa) throws SQLException {

		dao.removeTarefaById(tarefa.getId());

		return "redirect:listaTarefas";
	}
}