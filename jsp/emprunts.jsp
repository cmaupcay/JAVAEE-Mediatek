// TODO Liste des emprunts de l'abonné.
<div id="docs">
    <h3>Documents empruntés</h3>
    <ul>
        <c:forEach var="doc" items="${ docs }">
            <li>
                <c:out value="${ doc[TITRE] }"/>
                <form method="post">
                    <input type="hidden" name="doc" value="${ doc[ID] }">
                    <input type="submit" id="${ ACTION_RETOURNER }" name="${ ACTION_RETOURNER }" value="Retourner">
                </form>
            </li>
        </c:forEach>
    </ul>
</div>

<div id="infos">
        <c:if test="${ msg != null }">
            <h5><c:out value="${ msg }"/></h5>
        </c:if>
</div>