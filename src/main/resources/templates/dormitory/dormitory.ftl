<#import "../parts/common.ftl" as c>
<#import "../parts/login.ftl" as l>




<@c.page>

    <@l.logout />
    <a class="btn btn-primary" data-toggle="collapse" href="#messAdding" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add Dormitory
    </a>
    <div class="collapse" id="messAdding">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <div class="form-group">
                    <input type="text" class="form-control" name="dNum" placeholder="Dorm number" />
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="dAddr" placeholder="Dormitory address">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="phone" placeholder="Phone">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="conName" placeholder="Controller Name">
                </div>

                <a>
                    *Enter all forms
                </a>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Add</button>
                </div>


            </form>
        </div>
    </div>


    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="filterDormitories" class="form-inline">
                <input type="text" name="filterDormitories" class="form-control" placeholder="Search...">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>



    <div class="card-columns" >
        <#list dormitories as d>
            <div class="card my-3" style="width: 18rem;">
                <div class="card-body">

                    <div class="card-title">
                        <h1 style="color:#0000ff">${d.dormNumber}</h1>
                    </div>

                    <div class="m-2">
                        <span><p style="color: #00ffff"> Address:  </p> ${d.address}</span>
                    </div>
                    <div class="m-2">
                        <span><p style="color: #00ffff"> Phone: </p> ${d.phone}</span>
                    </div>
                    <div class="m-2">
                        <span><p style="color: #00ffff"> Controller name: </p> ${d.commFullName}</span>
                    </div>

                    <a href="/editDormitory/${d.getId()}" class="card-link">Edit</a>
                    <a href="/delDormitory/${d.getId()}"  class="card-link">Delete</a>

                </div>
            </div>
        </#list>
    </div>
</@c.page>