package controle;

// Criado por Gui//
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.ClienteDao;

@WebServlet(name = "Controle", urlPatterns = {"/Controle"})
public class Controle extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensagem;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String nome, tel, email;
        int idade;
        String flag = request.getParameter("flag");

        if (flag.equalsIgnoreCase("salvarcli")) {

            nome = request.getParameter("nome");
            tel = request.getParameter("tel");
            idade = Integer.parseInt(request.getParameter("idade"));
            email = request.getParameter("email");

            Cliente cli = new Cliente();

            cli.setIdade(idade);
            cli.setNome(nome);
            cli.setTel(tel);
            cli.setEmail(email);

            ClienteDao dao = new ClienteDao();
            int r = dao.conectar();

            if (r == 0) {
                mensagem = "Erro ao se conectar ao banco de dados";
                request.setAttribute("mensagem", mensagem);
                RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                d.forward(request, response);
            } else {
                r = dao.salvarCliente(cli);
                if (r == 1) {
                    mensagem = "Cliente Cadastrado!!!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher d = request.getRequestDispatcher("cadastrosucesso.jsp");
                    d.forward(request, response);
                } else {
                    mensagem = "Ocorreu um erro!!!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                    d.forward(request, response);
                }
                dao.desconectar();
            }
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher d = request.getRequestDispatcher("cadastrosucesso.jsp");
            d.forward(request, response);
            
        } else if (flag.equalsIgnoreCase("consultarclientenome")) {
                nome = request.getParameter("nome");
                
                ClienteDao dao = new ClienteDao();
                int r = dao.conectar();
                if (r == 0) {
                    mensagem = "erro na conexão com o bd";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                    d.forward(request, response);
                } else {
                ArrayList lista = dao.consultarClienteNome(nome);
                if (!lista.isEmpty()) {
                request.setAttribute("lista", lista);
                RequestDispatcher d = request.getRequestDispatcher("exibir_cliente.jsp");
                d.forward(request, response);
                return;
                } else {
                mensagem = "Usuario não encontrado";
                request.setAttribute("mensagem", mensagem);
                RequestDispatcher d = request.getRequestDispatcher("mensagens.jsp");
                d.forward(request, response);
                }
                dao.desconectar();
            }
            
        } else if (flag.equalsIgnoreCase("consultartodosclientes")) {
                ClienteDao dao = new ClienteDao();
                int r = dao.conectar();
                if (r == 0) {
                mensagem = "Erro na conexão com o db";
                request.setAttribute("mensagem", mensagem);
                RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                d.forward(request, response);
                } else {
                ArrayList lista = dao.consultarTodosCliente();
                if (!lista.isEmpty()) {
                request.setAttribute("lista", lista);
                RequestDispatcher d = request.getRequestDispatcher("mensagens.jsp");
                d.forward(request, response);
                }
                dao.desconectar();
            } 
        } else {
                mensagem = "Erro na flag!!!";
                request.setAttribute("mensagem", mensagem);
                RequestDispatcher d = request.getRequestDispatcher("erro.jsp");
                d.forward(request, response);
            }
    }
}
