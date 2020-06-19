<#import "../parts/common.ftl" as c>
<#import "../parts/login.ftl" as l>


<@c.page>

    <@l.logout />

    <a class="btn btn-primary" data-toggle="collapse" href="#messAdding" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add Faculty
    </a>
    <div class="collapse" id="messAdding">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <div class="form-group">
                    <input type="text" class="form-control" name="fNum" placeholder="Faculty name" />
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="phone" placeholder="Phone" >
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="dName" placeholder="Dean name">
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
            <form method="get" action="filterFaculties" class="form-inline">
                <input type="text" name="filterFaculties" class="form-control" placeholder="Search...">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>


    <div class="card-columns" >
        <#list faculties as f>
            <div class="card my-3" style="width: 18rem;">
                <div class="card-body">

                    <div class="card-title">
                        <h1 style="color:#0000ff">${f.name}</h1>
                    </div>

                    <div class="m-2">
                        <span><p style="color: #00ffff"> Phone:  </p> ${f.phone}</span>
                    </div>
                    <div class="m-2">
                        <span><p style="color: #00ffff"> Dean name : </p> ${f.decanName}</span>
                    </div>

                    <a href="/editFaculty/${f.getId()}" class="card-link">Edit</a>
                    <a href="/delFaculty/${f.getId()}"  class="card-link">Delete</a>


                </div>
            </div>
        </#list>
    </div>
</@c.page>