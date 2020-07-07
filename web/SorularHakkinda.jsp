<%@ page import="java.io.PrintWriter" %>
<%@ page import="Bean.Sorular" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="Bean.soruBilgisi" %>
<%--
  Created by IntelliJ IDEA.
  User: Recep
  Date: 1.07.2020
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="tempSoruNesnesi" class="Bean.soruBilgisi" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    tempSoruNesnesi = (soruBilgisi) session.getAttribute("soruNesnesi");
    String anketinIsmi = tempSoruNesnesi.getAnketinIsmi();
    String soruSayisi = tempSoruNesnesi.getSoruSayisi();

    int indis =  tempSoruNesnesi.getKacinciSoru();
    session.setAttribute("soruNesnesi", tempSoruNesnesi);

    List<Sorular> anketinSorulari;
    anketinSorulari = (List<Sorular>) session.getAttribute("anketinSorulari");
    session.setAttribute("anketinSorulari", anketinSorulari);
%>
<html>
<head>
    <style type="text/css">

    </style>
    <title>Soru Ekleme</title>
    <script>
        var intTextBox = 0;
        var link1 = "addElement();";
        var link2 = "removeElement()";
        var yedek;
        function disable_link() {
            document.getElementById('linkEkle').disabled=true;
            document.getElementById('linkSil').disabled=true;

            document.getElementById('linkEkle').removeAttribute('onclick');
            document.getElementById('linkSil').removeAttribute('onclick');

            document.getElementById('linkEkle').style.color = "grey";
            document.getElementById('linkSil').style.color = "grey";
            yedek = intTextBox;
            while(yedek > 0){
                document.getElementById("tb_" + yedek).disabled = true;
                yedek--;
            }
        }
        function enable_link() {
            document.getElementById('linkEkle').setAttribute("onclick",link1);
            document.getElementById('linkSil').setAttribute("onclick",link2);

            document.getElementById('linkEkle').style.color = "blue";
            document.getElementById('linkSil').style.color = "blue";
            yedek = intTextBox;
            while(yedek != 0){
                document.getElementById("tb_" + yedek).disabled = false;
                yedek--
            }
        }
        function addElement() {
            intTextBox++;
            var objNewDiv = document.createElement('div');
            objNewDiv.setAttribute('id', 'div_' + intTextBox);
            objNewDiv.innerHTML = '<p id="Secenek_' + intTextBox + '"> Seçenek' + intTextBox + '</p>' + ' <input type="text" id="tb_' + intTextBox + '" name="tb_' + intTextBox + '"/>';
            document.getElementById('content').appendChild(objNewDiv);
            document.getElementById('secenekSayisi').value = intTextBox;
        }
        function removeElement() {
            if(0 < intTextBox) {
                document.getElementById('content').removeChild(document.getElementById('div_' + intTextBox));
                intTextBox--;
                document.getElementById('secenekSayisi').value = intTextBox;
            } else {
                alert("Silinecek Seçenek Kalmadı.");
            }
        }
    </script>
</head>
<body>
<form action="/StajProje1_war_exploded/Servlet/soruEkle" method="post">
    <div id="wrapper">
        <div id = "header">
            <ul>
                <li><p><%=tempSoruNesnesi.getAnketinIsmi()%>
                    <c:out value ="${requestScope.soruNesnesi.kacinciSoru}"/>Anketi</p></li>
            <li><p> <%=tempSoruNesnesi.getKacinciSoru()%> / <%=tempSoruNesnesi.getSoruSayisi()%> Soru </p></li>
            </ul></div>
        <div id="Govde">
            <ul>

                <li><span>Soru</span></li>
                <li><textarea name="soruGovdesi" rows="4" cols="50"></textarea></li>
                <li><span>Soru Tipi</span>
                    <li><input type="radio" id="acik" name="soruTipiRadio" value="acikUclu" onchange="disable_link();" />Açık Uçlu</li>
                    <li><input type="radio" id="secenekli" name="soruTipiRadio" value="test" onchange="enable_link();"/> Test
                        <li>
                            <p id="butonlar">
                                <a href="javascript:void(0);" id="linkEkle" font-color="grey" >Seçenek Ekle</a>
                                <a href="javascript:void(0);" id="linkSil"  font-color="grey">Seçenek Sil</a>
                            </p>
                        </li>
                    </li>
                </li>
            </ul>
            <div id="content"></div>
        </div>
        <input type="submit" name="gonder" align="right" value="İleri"/>
    </div>
    <input type="hidden" value="" id="secenekSayisi" name="seceneksayisi"/>
    <input type="hidden" value="" name="kacinciSoru" />
</form>
</body>
</html>