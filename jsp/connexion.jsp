<form method="post">
    <label for="${ PARAM_POST_NOM }">Nom</label>
    <input type="text" name="${ PARAM_POST_NOM }" id="${ PARAM_POST_NOM }" required autofocus>
    <label for="${ PARAM_POST_MDP }">Mot de passe</label>
    <input type="password" name="${ PARAM_POST_MDP }" id="${ PARAM_POST_MDP }" required autocomplete="false">
    <input type="submit" value="SE CONNECTER">
</form>

<div id="infos">
    <c:if test="${ msg != null }">
        <h4><c:out value="${ msg }"/></h4>
    </c:if>
</div>