package tarefas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OlaMundoController {
	
	@RequestMapping("/olaMundo")
	public String execute(){
		System.out.println("Executando...");
		return "ok";
	}
}
