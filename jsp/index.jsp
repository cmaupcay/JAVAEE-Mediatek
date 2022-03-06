<div id="docs">
    <h2>Documents diponibles</h2>
    <c:choose>
        <c:when test="${ docs.size() == 0 }">
            <h5>Aucun document disponible.</h5>
        </c:when>
        <c:otherwise>
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
                            <c:if test="${ doc[ADULTE] == 'true'}">&#128286;</c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</div>