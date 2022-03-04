// TODO Page d'affichage d'un document

<h3>Document : </h3>
<p>${ doc }</p>

<c:choose>
    <c:when test="${ doc.disponible() && !u.isBibliothecaire() }">
        <form method="post">
            <input type="submit" id="${ ACTION_EMPRUNTER }" name="${ ACTION_EMPRUNTER }" value="Emprunter">
        </form>
    </c:when>
    <c:otherwise>
            <c:choose>
                <c:when test="">
                    // TODO Tester si l'emprunteur est l'utilisateur courant.
                </c:when>
                <c:otherwise>
                    <h4>Indisponible.</h4>
                </c:otherwise>
            </c:choose>
    </c:otherwise>
</c:choose>