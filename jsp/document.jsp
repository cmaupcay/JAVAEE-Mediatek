<c:if test="${ msg != null }">
    <div id="infos">
            <h5><c:out value="${ msg }"/></h5>
    </div>
</c:if>

<div id="doc">
    <h2>
        <c:if test="${ doc[ADULTE] == 'true'}">&#128286;</c:if>
        <c:out value="${ doc[TITRE] }"/>
    </h2>
    <h3>de <c:out value="${ doc[AUTEUR] }"/></h3>
</div>

<div id="infos">
    <c:choose>
        <c:when test="${ doc[EMPRUNTEUR] == null }">
            <c:choose>
                <c:when test="${ !u.isBibliothecaire() }">
                    <form method="post">
                        <input type="submit" id="${ ACTION_EMPRUNTER }" name="${ ACTION_EMPRUNTER }" value="EMPRUNTER">
                    </form>
                </c:when>
                <c:otherwise>
                    <h4>Disponible !</h4>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <h4>Indisponible.</h4>
            <c:if test="${ u.isBibliothecaire() }">
                <h5>Emprunté par <i><c:out value="${ doc[EMPRUNTEUR] }"/></i></h5>
            </c:if>
        </c:otherwise>
    </c:choose>
</div>