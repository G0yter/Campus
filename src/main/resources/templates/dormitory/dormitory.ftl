<#import "../parts/common.ftl" as c>
<#import "../parts/login.ftl" as l>
<#include "../parts/security.ftl">




<@c.page>

    <a class="btn btn-primary mt-2" data-toggle="collapse" href="#messAdding" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add Dormitory
    </a>
    <div class="collapse" id="messAdding">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="dormitoryFile" id="dormFile">
                        <label class="custom-file-label" for="dormFile">Choose file</label>
                    </div>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="dNum" placeholder="Dorm number"/>
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

                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Add</button>
                </div>


            </form>
        </div>
    </div>


    <div class="form-row">
        <div class="form-group col-md-6">
            <div>
                <form method="get" action="filterDormitories" class="form-inline">
                    <input type="text" name="filterDormitories" class="form-control" placeholder="Search...">
                    <button type="submit" class="btn btn-primary ml-2">Search</button>
                </form>
            </div>
            <div>
                <form method="get" action="getAllDormitories" class="form-inline">
                    <button type="submit" class="btn btn-primary mt-1">All</button>
                </form>
            </div>
        </div>
    </div>



    <div class="card-columns">
        <#list dormitories as d>
            <div class="card text-center"
                 style="background-color: rgba(0,0,0,0.5) !important; height: auto; width: 21rem;">
                <#if d.filename??>
                    <img src="/img/${d.filename}" class="card-img-top" alt="No image yet">
                </#if>
                <div class="card-body">

                    <div class="card-title">
                        <h1 style="color:#00ffff">${d.dormNumber}</h1>
                    </div>

                    <div class="m-2">
                        <span><p style="color: #00ffff"> Address:  </p> <p
                                    style="color: whitesmoke">${d.address}</p> </span>
                    </div>
                    <div class="m-2">
                        <span><p style="color: #00ffff"> Phone: </p> <p style="color: whitesmoke">${d.phone}</p> </span>
                    </div>
                    <div class="m-2">
                        <span><p style="color: #00ffff"> Controller name: </p> <p
                                    style="color: whitesmoke">${d.commFullName}</p> </span>
                    </div>

                    <#if isAdmin>
                        <a href="/editDormitory/${d.getId()}" class="card-link">Edit</a>
                        <a href="/delDormitory/${d.getId()}" class="card-link">Delete</a>
                    </#if>

                </div>
            </div>
        </#list>
    </div>
</@c.page>