<#import "../parts/common.ftl" as c>
<@c.page>
    <h1 style="color: chartreuse">Delete Faculty.</h1>
    <form method="post" action="/deleteFaculty">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="hidden" value="${faculty.getId()}" name="id">
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Delete Faculty  ${faculty.getName()}</button>
        </div>
    </form>
    <a href="/faculties" class="card-link">Back</a>

    <h5>You delete every Student and Group by deleting his faculty! Be careful</h5>
</@c.page>