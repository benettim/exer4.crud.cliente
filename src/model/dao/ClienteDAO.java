/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Cliente;
/**
 *
 * @author PIU2
 */
public class ClienteDAO {
    
    public void create(Cliente cliente){
        
        Connection con = ConnectionFactory.getConnection();
        Statement stmt = null;
        
        try {
            String sql1 = "INSERT INTO cliente (nome, email, cpf, telefone, dataaniversario) VALUES ('flavio','flavio@hotmail.com','00972600027','55999999547','14/04/1986')";
//            stmt = con.prepareStatement("INSERT INTO cliente (nome, email, cpf, telefone, dataaniversario) VALUES (?,?,?,?,?)");
//            stmt.setString(1, cliente.getNome());
//            stmt.setString(2, cliente.getEmail());
//            stmt.setString(3, cliente.getCpf());
//            stmt.setString(4, cliente.getTelefone());
//            stmt.setString(5, cliente.getDataaniversario());
            
            con.setAutoCommit(false);
            stmt = con.createStatement();
            stmt.executeUpdate(sql1);
            
            con.commit();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.rollback();
            } catch (Exception e) {
                System.out.println("Erro ao salvar sql1");
            }
        } 
    }
    
    public ArrayList<Cliente> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM cliente ORDER by idcliente");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setIdcliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setDataaniversario(rs.getString("dataaniversario"));
                listaClientes.add(cliente);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler os livros", "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return listaClientes;
    }
    
    public void update(Cliente cliente){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE cliente set nome = ?, email = ?, cpf = ?, telefone = ?, dataaniversario = ? "
                    +" WHERE idcliente = ?");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getDataaniversario());
            stmt.setInt(6, cliente.getIdcliente());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: "+ex);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        }
    }
    
    public void delete(Cliente cliente){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE FROM cliente WHERE idcliente = ?");
            stmt.setInt(1, cliente.getIdcliente());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: "+ex);
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        }
    }
}
