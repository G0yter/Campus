<#import "login.ftl" as l>
<#include "security.ftl">

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="/">Campus</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/main"> Students </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/faculties"> Faculties </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/groups"> Groups </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/dormitories"> Dormitories </a>
            </li>


            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/user">Users</a>
            </li>
            </#if>

        </ul>


        <div class="navbar-text mr-2">${name}</div>

        <#if isAdmin>
            <@l.logout/>
            <#elseif isUser>
                <@l.logout/>
        </#if>

    </div>
</nav>