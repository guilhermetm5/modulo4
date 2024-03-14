package model;

//Criado por Gui//

//Classes de conexao com o banco de dados//
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClienteDao {
    private Connection conexao;
        
        private PreparedStatement st;
        
        public int conectar() {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/dados", "root", "");
                
                return 1;
            } catch (SQLException ex) {
                return 0;
            } catch (ClassNotFoundException x){
                return 0;
            }
        }
    public int salvarCliente(Cliente cli) {
        try {
            
            String comando = "INSERT INTO `dados`.`cliente` (`nome` , `telefone` ,`idade` ,`email` )VALUES (?,?,?,?)";
            st = this.conexao.prepareStatement(comando);
            st.setString(1, cli.getNome());
            st.setString(2, cli.getTel());
            st.setInt(3, cli.getIdade());
            st.setString(4, cli.getEmail());
            st.execute();
            
            return 1;
        } catch (SQLException ex) {
            /*int codErro=ex.getErrorCode();
            String mensagemErro=ex.getMessage();*/
            if (ex.getErrorCode() == 1062) {
                return 2;
            } else {
                return 0;
            }
        }
    }
    
    public void desconectar() {
        try {
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList consultarClienteNome(String n) {
        try {
            String sql = "select * from cliente where nome like ?";
            ArrayList<Cliente> lista = new ArrayList<Cliente>();
            st = conexao.prepareStatement(sql);
            st.setString(1, "%"+n+"%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setNome(rs.getString("nome"));
                cli.setTel(rs.getString("tel"));
                cli.setEmail(rs.getString("email"));
                cli.setIdade(rs.getInt("idade"));
                
                lista.add(cli);
            }
            return lista;
        }catch (SQLException ex) {
            return null;
        } 
    }
    public ArrayList consultarTodosCliente() {
        try {
            String sql = "select * from cliente where nome like ?";
            ArrayList<Cliente> lista = new ArrayList<Cliente>();
            st = conexao.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setNome(rs.getString("nome"));
                cli.setTel(rs.getString("tel"));
                cli.setEmail(rs.getString("email"));
                cli.setIdade(rs.getInt("idade"));
                
                lista.add(cli);
            }
            return lista;
        }catch (SQLException ex) {
            return null;
        } 
    }
}
