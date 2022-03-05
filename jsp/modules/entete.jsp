<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;encoding=UTF-8" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Mediatek</title>
    
    <style>
        /* Inclusion manuelle du CSS car inclusion par HTTP impossible. */
        <%@include file="../style/style.css"%>
    </style>

</head>
<body>
    <header>
        <div>
            <h1>Mediatek</h1>
            <c:if test="${ u != null }">
                <h5>Connecté en tant que <i><c:out value="${ u.name() }"/></i></h5>
            </c:if>
        </div>
        <nav>
            <ul>
                <li>
                    <a href="${ RACINE }/">Accueil</a>
                </li>
                <c:choose>
                    <c:when test="${ u == null }">
                        <li>
                            <a href="${ RACINE }/connexion?redirect=${ PAGE }">Connexion</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${ u.isBibliothecaire() }">
                                <li>
                                    <a href="${ RACINE }/bib/">Espace bibliothécaire</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="${ RACINE }/emprunts">Emprunts</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        <li>
                            <a href="${ RACINE }/deconnexion">Déconnexion</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
    </header>

    <section>