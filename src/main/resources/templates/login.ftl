<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <@l.login "/login" "Sign in" "Log in" "${errorLogin!}" "You are not on the board yet?" "/registration" "Sign up"/>
</@c.page>