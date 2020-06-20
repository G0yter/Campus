<#import "parts/common.ftl" as c>

<@c.page>
    <div class="card text-center mt-5"
         style="background-color: rgba(210,210,210,0.5) !important; height: 325px; width: 67rem;">
        <div class="card-body">
            <div class="card-title">
                <h1 style="color:rgba(53,72,60,0.87)">User Editor</h1>
            </div>

            <form action="/user" method="post">
                <div class="form-group">
                    <input type="text" name="username" value="${user.username}">
                </div>

                <div class="form-group">
                    <#list roles as role>
                        <div>
                            <label><input type="checkbox" class="form-check-input"
                                          name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}
                            </label>
                        </div>
                    </#list>
                </div>

                <input type="hidden" value="${user.id}" name="userId"/>
                <input type="hidden" value="${_csrf.token}" name="_csrf"/>
                <button type="submit" class="btn btn-primary">Save</button>
            </form>
        </div>
    </div>
</@c.page>

