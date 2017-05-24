package tarefas.controller;

import java.sql.SQLException;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import tarefas.dao.TarefaDao;
import tarefas.model.Tarefa;

//avisa que é para gerenciar a transação ao spring
@Transactional
@Controller
public class TarefaController {

	@Autowired
	TarefaDao dao;

	@RequestMapping("novaTarefa")
	public String form() {
		return "tarefa/formulario";
	}

	@RequestMapping("adicionaTarefa")
	public String adiciona(@Valid Tarefa tarefa, BindingResult result) throws SQLException {
		if (result.hasFieldErrors("descricao")) {

			return "tarefa/formulario";
		}
		dao.adiciona(tarefa);

		return "tarefa/adicionada";
	}

	@RequestMapping(value = { "listaTarefas", "" })
	public String lista(Model model) throws SQLException {
		model.addAttribute("tarefas", dao.lista());

		return "tarefa/lista";
	}

	@RequestMapping("alteraTarefa")
	public String altera(@Valid Tarefa t, BindingResult result) {
		if (result.hasFieldErrors("descricao")) {

			return "tarefa/mostra";
		}
		dao.altera(t);
		return "redirect:listaTarefa";
	}

	@RequestMapping("mostraTarefa")
	public String mostra(Long id, Model model) throws SQLException {
		model.addAttribute("tarefa", dao.buscaPorId(id));

		return "tarefa/mostra";
	}

	@RequestMapping("removeTarefa")
	public String remove(Tarefa tarefa) throws SQLException {
		dao.remove(tarefa);

		return "redirect:listaTarefas";
	}
}