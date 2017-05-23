package tarefas.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tarefas.usuarios.UserDAO;

@Controller
public class LoginController {

	private final UserDAO dao;

	@Autowired
	public LoginController(UserDAO userDAO) {
		this.dao = userDAO;
	}

	@RequestMapping("loginForm")
	public String loginForm() {
		return "login/login";
	}

	@RequestMapping("efetuaLogin")
	public String login(Usuario usuario, HttpSession session) {
		if (dao.searchUser(usuario)) {
			session.setAttribute("usuarioLogado", usuario);
			return "login/menu";
		}
		return "redirect:loginForm";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:loginForm";
	}
}
