<#import "../parts/common.ftl" as c>
<@c.page>
    <div class="card text-center"
         style="background-color: rgba(0,0,0,0.5) !important; height: auto; width: 67rem;">
        <div class="card-body">
            <div class="card-title">
                <h1 style="color:#007bff">Are you sure that you want to delete the group ${group.cipher} ?</h1>
            </div>

            <span style="float: left;">
                <form method="get" action="/groups">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <input type="hidden" value="${group.getId()}" name="id">
                    <button type="submit" class="btn btn-primary">Cancel</button>
                </form>
            </span>

            <span style="float: right;">
                <form method="post" action="/deleteGroup">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <input type="hidden" value="${group.getId()}" name="id">
                    <button type="submit" class="btn btn-primary" style="background-color: red">Delete</button>
                </form>
            </span>

        </div>
    </div>


</@c.page>