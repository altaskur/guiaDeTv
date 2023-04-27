<%-- 
    Document   : index
    Created on : 5 abr 2023, 0:34:53
    Author     : Altaskur
--%>

<%@page import="dao.Conexion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="inc.tanuki.javacrud.ProgramaTv"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <%
   
       // Se debe evitar ejecuar codigo java en JSP   
        List<ProgramaTv> listaTv = session.getAttribute("ProgramaTv") == null ? new ArrayList<>() : (ArrayList<ProgramaTv>) session.getAttribute("ProgramaTv");
       
    %>
<!DOCTYPE html>

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <title>TP - Tu guía de la tele</title>
    <style>
        body{
            background-color:#879374;
            color: white;
            display:flex;
            flex-direction: column;
            align-items:  center;
            justify-content: center;
            font-family: "Roboto mono";
        }
        
        table{
            width: 500px;
            border: 0.25px solid #4c5340;
            
            border-radius: 10px;
        }
        td, th{
            margin:  0 15px;
            
            border: 10px solid transparent;
        }
        input[type="text"]{
            outline: none;
            border: none;
            border-radius: 2px;
            padding: 8px 10px;
            height: 20px;
        }
        
        input[type="submit"]{
            border-radius: 80px;
            height: 30px;
            padding: 0 20px;
            border: solid #4c5340 2px;
            border-bottom: 4px solid #4c5340;
        }
        a:link,
        a:active,
        a:visited{
            color:white;
            padding: 0 10px;
        }
    </style>
</head>

<body>
    <!-- <img src="http://www.homerswebpage.com/images/jebusDance.gif" alt="alt"/> -->
    <h1>TP - Tu guía de la tele</h1>
    <table>
        <thead align="center">
            <tr>
                <th></th>
                <th>Canal</th> 
                <th>Titulo</th> 
                <th>Horario</th>
                <th>
                    <form action="crudServelet" method="get">
                        <input type="hidden"name="deleteList" value="delete">
                        <input type="submit"value="Eliminar datos">
                    </form>
                </th>
            </tr>
        </thead>
        <tbody align="center">
            <tr>
        <%            
            if(session.getAttribute("Editar") == null){
        %>
        <form action="crudServelet" method="POST" >
            <th>Nuevo elemento</th>
            <input type="hidden" name="nuevoPrograma" value="nuevo">
            <td> <input type="text" placeholder="Canal" name="canal" required> </td>
            <td> <input type="text" placeholder="Titulo" name="titulo" required> </td>
            <td> <input type="text" placeholder="23:50" name="hora" required> </td>
            <td> <input type="submit" value="Crear nuevo programa"> </td>
        </form>
        </tr>
        <% 
            } else {
                ProgramaTv editarPrograma = (ProgramaTv) session.getAttribute("Editar");
        %>
        
         <form action="crudServelet" method="POST" >
            <th>Editar elemento</th>
            <input type="hidden" name="editPrograma" value="<%= editarPrograma.getId()%>">
            <td> <input type="text" placeholder="Canal" name="canal" value="<%= editarPrograma.getCanal()%>" required> </td>
            <td> <input type="text" placeholder="Titulo" name="titulo" value="<%= editarPrograma.getTitulo()%>" required> </td>
            <td> <input type="text" placeholder="23:50" name="hora" value="<%= editarPrograma.getHorario()%>" required> </td>
            <td> <input type="submit" value="Editar">  <a href="./crudServelet?cancelEdit=cancel"><i class="bi bi-x-circle"></i></a>  </td>
            
        </form>
        </tr>
        <%  
            }        

            for (ProgramaTv elem : listaTv) {   
        %>
        <tr>
            <th>&nbsp;<%= elem.getId()%></th><td><%= elem.getCanal()%></td> 
            <td><%= elem.getTitulo()%></td> <td><%= elem.getHorario()%></td> 
            <th>
                <a href="./crudServelet?edit=<%= elem.getId()%>"><i class="bi bi-pencil-square"></i></a> 
                <a href="./crudServelet?erase=<%= elem.getId()%>"><i class="bi bi-trash"></i></a> 
            </th>
        </tr>
        <%
            }
        %>

        </tbody>
    </table>
</body>

