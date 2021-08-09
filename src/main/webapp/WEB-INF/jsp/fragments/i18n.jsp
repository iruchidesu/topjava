<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    let i18n = [];
    i18n["addTitle"] = '<spring:message code="${param.entity}.add"/>';
    i18n["editTitle"] = '<spring:message code="${param.entity}.edit"/>';
    i18n["localCode"] = '<spring:message code="common.locale"/>';

    <c:forEach var="key" items='<%=new String[]{"common.deleted","common.saved","common.enabled","common.disabled","common.errorStatus","common.confirm"}%>'>
    i18n["${key}"] = "<spring:message code="${key}"/>";
    </c:forEach>
</script>
