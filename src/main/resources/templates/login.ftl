<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    Login page


    <@l.login "/login" "Sign in" />
    <a href="/registration">You are not on the board yet?
        Sign up!</a>

</@c.page>