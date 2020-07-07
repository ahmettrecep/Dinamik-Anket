<%@ page import="Bean.soruBilgisi" %><%--
  Created by IntelliJ IDEA.
  User: Recep
  Date: 3.07.2020
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    soruBilgisi tempSoruNesnesi = (soruBilgisi) request.getAttribute("soruNesnesi");
    System.out.print("Kaçıncı Soru : " + tempSoruNesnesi.getKacinciSoru());
    request.setAttribute("soruNesnesi", tempSoruNesnesi);
    request.getRequestDispatcher("/StajProje1_war_exploded/Servlet/soruEkle").forward(request,response);

%>
