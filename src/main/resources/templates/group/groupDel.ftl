<#import "../parts/common.ftl" as c>
<@c.page>
    <h1 style="color: chartreuse">Delete dormitory.</h1>
    <form method="post" action="/deleteGroup">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="hidden" value="${group.getId()}" name="id">
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Delete group ${group.getCipher()}</button>
        </div>
    </form>
    <a href="/groups" class="card-link">Back</a>

    <h5>You delete every Student by deleting his group! Be careful</h5>
</@c.page>