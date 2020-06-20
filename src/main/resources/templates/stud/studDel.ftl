<#import "../parts/common.ftl" as c>
<@c.page>
    <div class="card text-center"
         style="background-color: rgba(0,0,0,0.5) !important; height: auto; width: 67rem;">
        <div class="card-body">
            <div class="card-title">
                <h1 style="color:#acffee">Are you sure that you want to delete the card № ${student.cardNumber} ?</h1>
            </div>

            <span style="float: left;">
                <form method="get" action="/main">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <input type="hidden" value="${student.getId()}" name="id">
                    <button type="submit" class="btn btn-primary">Cancel</button>
                </form>
            </span>

            <span style="float: right;">
                <form method="post" action="/delete">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <input type="hidden" value="${student.getId()}" name="id">
                    <button type="submit" class="btn btn-primary" style="background-color: red">Delete</button>
                </form>
            </span>

        </div>
    </div>



</@c.page>