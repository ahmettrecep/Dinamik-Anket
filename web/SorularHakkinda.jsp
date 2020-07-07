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
    <link href='http://https://fonts.google.com/specimen/Raleway?subset=latin-ext' rel='stylesheet' type='text/css'>
    <style type="text/css">
        ::-webkit-scrollbar {
            width: 12px;
            overflow:hidden;
        }

        ::-webkit-scrollbar-track {
            -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
            border-radius: 10px;
        }

        ::-webkit-scrollbar-thumb {
            border-radius: 10px;
            -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.5);
        }
        body{
            background-color :#00BFFF;
            content: "";
            display: table;
            clear: both;
        }

        #wrapper ul{
            list-style:none;
            padding:3px;
            font-family: 'Raleway', sans-serif;
        }
        #wrapper{
            z-index:10;
            padding:10px;
            height:90%;
            position:absolute;
            overflow:auto;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            margin: auto;
            width:600px;
            background-color: #ffffff;
            border-top-right-radius:5px;
            border-bottom-right-radius:5px;
            border-top-left-radius: 20px;
            border-bottom-left-radius: 20px;
            padding:10px;
            box-shadow: 4px 4px 2px 2px #4169E1;
        }
        #wrapper textarea:focus{
            outline:none;
            color:#666666;
            border-width:1px;
            border-color:#00BFFF;
        }
        #wrapper textarea{
            width:100%;
            border-width:1px;
            border-color:#cccccc;
            font-family: 'Raleway', sans-serif;
            font-size:13pt;
            padding-left:5px;
            color:#7f7f7f;
        }
        #wrapper p{
            font-size:15pt;
        }
        #content input[type="text"]{
            width:100%;
            border-top:none;
            border-left:none;
            border-right:none;
            border-bottom-color:#cccccc;
            font-size:13pt;
            padding-left:5px;
            color:#7f7f7f;
        }
        #content input[type="text"]:focus{
            outline:none;
            border-bottom-color:#00BFFF;
            color:#666666;
        }
        #content p{
            font-family: 'Raleway', sans-serif;
            font-size:15pt;
        }
        #linkSil {
            background-color:#cccccc;
            border-radius:7px;
            border:1px solid #18ab29;
            display:inline-block;
            cursor:pointer;
            color:#ffffff;
            font-family:Arial;
            font-size:17px;
            padding:8px 17px;
            text-decoration:none;
            text-shadow:0px 1px 0px #2f6627;
        }
        #linkSil:hover {
            background-color:#B22222;
        }
        #linkSil:active {
            position:relative;
            top:1px;
        }
        #linkEkle {
            background-color:#cccccc;
            border-radius:7px;
            border:1px solid #18ab29;
            display:inline-block;
            cursor:pointer;
            color:#ffffff;
            font-family:Arial;
            font-size:17px;
            padding:8px 17px;
            text-decoration:none;
            text-shadow:0px 1px 0px #2f6627;
        }
        #linkEkle:hover {
            background-color:#5cbf2a;
        }
        #linkEkle:active {
            position:relative;
            top:1px;
        }
        .gonderButon {
            background-color:#44c767;
            margin-top:10px;
            width:100%;
            border-radius:7px;
            border:1px solid #18ab29;
            display:inline-block;
            cursor:pointer;
            color:#ffffff;
            font-family:Arial;
            font-size:17px;
            padding:16px 31px;
            text-decoration:none;
            text-shadow:0px 1px 0px #2f6627;
        }
        .gonderButon:hover {
            background-color:#5cbf2a;
        }
        .gonderButon:active {
            position:relative;
            top:1px;
        }
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

            document.getElementById('linkEkle').style.backgroundColor = "cccccc";
            document.getElementById('linkSil').style.backgroundColor = "cccccc";
            yedek = intTextBox;
            while(yedek > 0){
                document.getElementById("tb_" + yedek).disabled = true;
                yedek--;
            }
        }
        function enable_link() {
            document.getElementById('linkEkle').setAttribute("onclick",link1);
            document.getElementById('linkSil').setAttribute("onclick",link2);

            document.getElementById('linkEkle').style.backgroundColor = "44c767";
            document.getElementById('linkSil').style.backgroundColor = "8B0000";
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
                <li><hr></li>
            <li><p> <%=tempSoruNesnesi.getKacinciSoru()%> / <%=tempSoruNesnesi.getSoruSayisi()%> Soru </p></li>
                <li><hr></li>
            </ul></div>
        <div id="Govde">
            <ul>
                <li><p>Soru <font color="red">*</font></p></li>
                <li><textarea name="soruGovdesi" rows="4" cols="50"></textarea></li>
                <li><p>Soru Tipi <font color="red">*</font></p>
                    <li><input type="radio" id="acik" name="soruTipiRadio" value="acikUclu" onchange="disable_link();" />Açık Uçlu</li>
                    <li><input type="radio" id="secenekli" name="soruTipiRadio" value="test" onchange="enable_link();"/> Test
                <li><hr></li>
                        <li>
                            <p id="butonlar">
                                <a href="javascript:void(0);" id="linkEkle" font-color="grey" >Seçenek Ekle</a>
                                <a href="javascript:void(0);" id="linkSil"  font-color="grey">Seçenek Sil</a>
                            </p>
                        </li>
                <li><hr></li>
                    </li>
                </li>
            </ul>
            <div id="content"></div>
        </div>
        <input type="submit" class="gonderButon" name="gonder" align="right" value="İleri"/>
    </div>
    <input type="hidden" value="" id="secenekSayisi" name="seceneksayisi"/>
    <input type="hidden" value="" name="kacinciSoru" />
</form>
</body>
</html>