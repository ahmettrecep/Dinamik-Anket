package Servlet;

import Bean.Sorular;
import DAO.AnketDAO;
import Util.Utilization;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/Servlet/AnketiGetir")
public class AnketiGetir extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        String anketNo = req.getParameter("anketID");
        System.out.println("Anketigetir.java'da anketID textbox bilgisi : " + anketNo);
        AnketDAO anketMetotlari = new AnketDAO();
        List<Sorular> soru = new LinkedList<Sorular>();
        try {
            soru = anketMetotlari.sorularıBirlestir(anketNo);
            if(!soru.isEmpty()) {
                HttpSession session = req.getSession();
                session.setAttribute("formaAitSorular", soru);
                req.getRequestDispatcher("../anketiCoz.jsp").forward(req,resp);
            }else{
                PrintWriter out = resp.getWriter();
                out.println("Böyle Bir Anket Yok!");
                req.getRequestDispatcher("../sorularEklendi.jsp").forward(req,resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}