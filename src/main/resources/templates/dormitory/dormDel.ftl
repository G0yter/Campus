<#import "../parts/common.ftl" as c>
<@c.page>
    <h1 style="color: chartreuse">Delete dormitory.</h1>
    <form method="post" action="/deleteDormitory">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="hidden" value="${dormitory.getId()}" name="id">
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Delete dormitory № ${dormitory.getDormNumber()}</button>
        </div>
    </form>
    <a href="/dormitories" class="card-link">Back</a>

    <h5>You delete every Student by deleting his dormitory! Be careful</h5>
</@c.page>