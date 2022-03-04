// TODO Page d'affichage d'un document

<h3>Document : </h3>
<p>${ doc }</p>

<div id="infos">
    <c:choose>
        <c:when test="${ doc.disponible() }">
            <c:if test="${ !u.isBibliothecaire() }">
                <form method="post">
                    <input type="submit" id="${ ACTION_EMPRUNTER }" name="${ ACTION_EMPRUNTER }" value="EMPRUNTER">
                </form>
            </c:if>
        </c:when>
        <c:otherwise>
                <c:choose>
                    <c:when test="">
                        // TODO Tester si l'emprunteur est l'utilisateur courant.
                        <form method="post">
                            <input type="submit" id="${ ACTION_RETOUR }" name="${ ACTION_RETOUR }" value="RETOURNER">
                        </form>
                    </c:when>
                    <c:otherwise>
                        <h4>Indisponible.</h4>
                    </c:otherwise>
                </c:choose>
        </c:otherwise>
    </c:choose>

    <c:if test="${ msg != null }"><p><c:out value="${ msg }"/></p></c:if>
</div>