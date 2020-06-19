<#macro login path butName title errorDescription footerString backPath backPathTitle>
    <div id="form-wrapper">
        <div class="container">
            <div class="d-flex justify-content-center h-100">
                <div class="card">
                    <div class="card-header">
                        <h3>${title}</h3>
                        <h6 style="color: red ;">${errorDescription}</h6>
                    </div>
                    <div class="card-body">
                        <form action="${path}" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <div class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                                </div>
                                <input type="text" name="username" class="form-control" placeholder="username">
                            </div>
                            <div class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-key"></i></span>
                                </div>
                                <input type="password" name="password" class="form-control" placeholder="password">
                            </div>
                            <div class="form-group">
                                <input type="submit" value="${butName}" class="btn float-right login_btn">
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <div class="d-flex justify-content-center links">
                            ${footerString} <a href="${backPath}">${backPathTitle}</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</#macro>


<#macro logout>
    <form class="form-inline" action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-outline-dark my-2 my-sm-0" type="submit">Log out</button>
    </form>
</#macro>