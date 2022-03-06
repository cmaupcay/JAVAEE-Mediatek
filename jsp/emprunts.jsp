<div id="docs">
    <h2>Documents empruntés</h2>
    <c:if test="${ msg != null }">
        <h5><c:out value="${ msg }"/></h5>
    </c:if>
    <c:choose>
        <c:when test="${ docs.size() == 0 }">
            <h5>Aucun document emprunté.</h5>
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th id="TYPE">Type</th>
                    <th id="TITRE">Titre</th>
                    <th id="ACTION">Action</th>
                </tr>
                <c:forEach var="doc" items="${ docs }">
                    <tr id="doc">
                        <td id="TYPE">
                            <c:out value="${ TYPES[doc[TYPE]] }"/>
                        </td>
                        <td id="TITRE">
                                <c:out value="${ doc[TITRE] }"/>
                        </td>
                        <td id="ACTION">
                            <form method="post">
                                <input type="hidden" name="doc" value="${ doc[ID] }">
                                <input type="submit" id="${ ACTION_RETOURNER }" name="${ ACTION_RETOURNER }" value="Retourner">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</div>