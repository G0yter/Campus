<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <@l.login "/registration" "Sign up!" "Registration" "${message!}" "You are already here?" "/login" "Sign in"/>
</@c.page>