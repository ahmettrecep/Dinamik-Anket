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
      <link href='http://https://fonts.google.com/specimen/Raleway?subset=latin-ext' rel='stylesheet' type='text/css'>
    <style type="text/css">
        body{
            background-color :#00BFFF;
        }
        #wrapper{
            position:absolute;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            margin: auto;
            width: 600px;
            height: 270px;
            background-color: #ffffff;
            border-bottom-left-radius: 20px;
            border-bottom-right-radius: 20px;
            border-top-right-radius: 20px;
            padding:10px;
            box-shadow: 4px 4px 2px 2px #4169E1;
        }
        #wrapper input[type="text"]:focus{
            outline:none;
            border-bottom-color:#00BFFF;
            color:#666666;
        }
        #wrapper input[type="text"]{
            width:100%;
            border-top:none;
            border-left:none;
            border-right:none;
            border-bottom-color:#cccccc;
            font-size:13pt;
            padding-left:5px;
            color:#7f7f7f;
        }

        #wrapper ul{
            list-style:none;
            padding:3px;
        }
        #wrapper p{
            font-size:15pt;
            font-family: 'Raleway', sans-serif;
        }
        #wrapper ul li {
            padding:0px;
            margin:0;
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
      <div id="wrapper">
      <ul>
        <li><p>>-- Anketin Adı  <font color="red">*</font></p></li>
        <li><input type="text" name="anketIsim" /></li>
        <li><p>>-- Ankette Bulunacak Soru Sayısı  <font color="red">*</font></p></li>
        <li><input type="text" name="soruSayisi" onkeypress="return isNumber(event)" placeholder="Sadece Rakam Girilir..." /></li>
        <li><input type="submit" class="gonderButon" value="İleri >" name="gonder"/></li>
        <input type="hidden" value="1" name="indis"/>
      </ul>
      </div>
    </form>

  </body>
</html>