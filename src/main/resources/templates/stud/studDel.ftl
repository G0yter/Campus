<#import "../parts/common.ftl" as c>
<@c.page>
    <h1 style="color: chartreuse">Delete student card</h1>
    <form method="post" action="/delete">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="hidden" value="${student.getId()}" name="id">
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Delete card № ${student.cardNumber}</button>
        </div>
    </form>
    <a href="/main" class="card-link">Back</a>

</@c.page>