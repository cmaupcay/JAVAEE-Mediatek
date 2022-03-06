<form id="ajout" method="post">
    <label for="${ PARAM_POST_TYPE }">Type</label>
    <select name="${ PARAM_POST_TYPE }" id="${ PARAM_POST_TYPE }">
        <c:forEach var="type" begin="0" end="${ N_TYPES - 1 }">
            <option value="${ type }"><c:out value="${ TYPES[type] }"/></option>
        </c:forEach>
    </select>
    <label for="${ PARAM_POST_TITRE }">Titre</label>
    <input type="text" name="${ PARAM_POST_TITRE }" id="${ PARAM_POST_TITRE }" required>
    <label for="${ PARAM_POST_AUTEUR }">Auteur</label>
    <input type="text" name="${ PARAM_POST_AUTEUR }" id="${ PARAM_POST_AUTEUR }" required>
    <label for="${ PARAM_POST_ADULTE }">Réservé aux adultes</label>
    <input type="checkbox" name="${ PARAM_POST_ADULTE }" id="${ PARAM_POST_ADULTE }">

    <c:if test="${ msg != null }">
        <h5><c:out value="${ msg }"/></h5>
    </c:if>
    <input type="submit" id="${ ACTION_AJOUT }" name="${ ACTION_AJOUT }" value="Ajouter le document">
    <input type="submit" id="${ ACTION_RETOUR }" name="${ ACTION_RETOUR }" value="Retour">
</form>