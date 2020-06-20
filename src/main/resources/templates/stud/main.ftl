<#import "../parts/common.ftl" as c>
<#import "../parts/login.ftl" as l>
<#include "../parts/security.ftl">

<@c.page>


    <a class="btn btn-primary mt-2" data-toggle="collapse" href="#messAdding" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add Student card
    </a>
    <div class="collapse" id="messAdding">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="studentFile" id="studFile">
                        <label class="custom-file-label" for="studFile">Choose file</label>
                    </div>
                </div>

                <div class="form-group">
                    <input type="text" class="form-control" name="cNum" placeholder="Card number"/>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="fName" placeholder="Full name">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="doB"
                           placeholder="DD.MM.YYYY">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="pAddrs" placeholder="Parent's Address">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="facName" placeholder="Faculty name">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="grCipher" placeholder="Group cipher">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="dormNum" placeholder="Dormitory number">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="room" placeholder="Room number">
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
                <form method="get" action="filterStudents" class="form-inline">
                    <input type="text" name="filterStudents" class="form-control" placeholder="Search..."/>
                    <button type="submit" class="btn btn-primary ml-2">Search</button>
                </form>
            </div>
            <div>
                <form method="get" action="getAllStudents" class="form-inline">
                    <button type="submit" class="btn btn-primary mt-1">All</button>
                </form>
            </div>
        </div>
    </div>



    <div class="card-columns">
        <#list students as s>
            <div class="card text-center"
                 style="background-color: rgba(0,0,0,0.5) !important; height: auto; width: 21rem;">
                <#if s.filename??>
                    <img src="/img/${s.filename}" class="card-img-top" alt="No image yet">
                </#if>
                <div class="card-body">

                    <div class="card-title">
                        <h1 style="color:#0000ff">Card №${s.cardNumber}</h1>
                    </div>

                    <div class="card-subtitle mb-2 text-muted">
                        <h2 style="color:#00ffff">${s.fullName}</h2>
                    </div>

                    <div class="m-2">
                        <span><p style="color: #00ffff">Date of Birth: </p><p
                                    style="color: whitesmoke">${s.getDate(s.dateOfBirth)}</p> </span>
                    </div>

                    <div class="m-2">
                        <span><p style="color: #00ffff">Parent's address:</p><p
                                    style="color: whitesmoke">${s.addressOfParents}</p></span>
                    </div>

                    <div class="m-2">
                        <span><p style="color: #00ffff">Faculty name:</p> <p
                                    style="color: whitesmoke">${s.faculty.name}</p></span>
                    </div>

                    <div class="m-2">
                        <span><p style="color: #00ffff">Group name:</p> <p
                                    style="color: whitesmoke">${s.groupOfStuds.cipher}</p></span>
                    </div>

                    <div class="m-2">
                        <span><p style="color: #00ffff">Dormitory number:</p> <p
                                    style="color: whitesmoke">${s.dormitory.dormNumber}</p></span>
                    </div>

                    <div class="m-2">
                        <span><p style="color: #00ffff">Room number:</p> <p
                                    style="color: whitesmoke">${s.room}</p></span>
                    </div>

                    <#if isAdmin>
                        <a href="/edit/${s.getId()}" class="card-link">Edit</a>
                        <a href="/del/${s.getId()}" class="card-link">Delete</a>
                    </#if>
                </div>
            </div>
        </#list>
    </div>



</@c.page>