<% // Affichage d'une liste de documents. %>
<div id="docs">
    <h3>Documents</h3>
    <ul>
        <c:forEach var="doc" items="${ docs }">
            <li>
                <a href="${ RACINE }/doc?id=${ doc[ID] }"><c:out value="${ doc[TITRE] }"/></a>
            </li>
        </c:forEach>
    </ul>
</div>