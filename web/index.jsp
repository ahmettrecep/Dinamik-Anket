<%--
  Created by IntelliJ IDEA.
  User: Recep
  Date: 29.06.2020o
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Anket Oluştur</title>
      <script>
          function isNumber(evt) {
              evt = (evt) ? evt : window.event;
              var charCode = (evt.which) ? evt.which : evt.keyCode;
              if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                  return false;
              }
              return true;
          }
      </script>
  </head>
  <body>
    <form action="/StajProje1_war_exploded/Servlet/anketBilgisi" method="post">
      Anket Formuna Bir İsim veriniz.
      <input type="text" name="anketIsim" />
      Ankette Kaç Soru Bulunacak?
      <input type="text" name="soruSayisi" onkeypress="return isNumber(event)" placeholder="Sadece Rakam Girilir..." />
      <input type="submit" value="Gonder" name="gonder"/>
        <input type="hidden" value="1" name="indis"/>
    </form>
  </body>
</html>