<#assign
known=Session.SPRING_SECURITY_CONTEXT??>
<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    isAdmin = user.getRoles()?seq_contains("ADMIN")
    isUser = user.getRoles()?seq_contains("USER")
    >
<#else>
    <#assign
    name="Guest"
    isAdmin = false
    isUser = false>
</#if>