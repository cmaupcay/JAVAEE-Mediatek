<div id="docs">
    <h2>Documents</h2>
    <table>
        <tr>
            <th id="TYPE">Type</th>
            <th id="TITRE">Titre</th>
            <th id="AUTEUR">Auteur</th>
            <th id="ACCES">Accès</th>
        </tr>
        <c:forEach var="doc" items="${ docs }">
            <tr id="doc" onclick="window.location.href = '${ RACINE }/doc?id=${ doc[ID] }'">
                <td id="TYPE">
                    <c:out value="${ TYPES[doc[TYPE]] }"/>
                </td>
                <td id="TITRE">
                        <c:out value="${ doc[TITRE] }"/>
                </td>
                <td id="AUTEUR">
                    <c:out value="${ doc[AUTEUR] }"/>
                </td>
                <td id="ACCES">
                    <c:choose>
                        <c:when test="${ doc[EMPRUNTEUR] == null }">&#128994;</c:when>
                        <c:otherwise>&#128308;</c:otherwise>
                    </c:choose>
                    <c:if test="${ doc[ADULTE] == 'true'}">&#128286;</c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<form id="retour" method="post">
    <input type="submit" id="${ ACTION_RETOUR }" name="${ ACTION_RETOUR }" value="Retour">
</form>