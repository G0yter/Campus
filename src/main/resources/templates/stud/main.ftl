<#import "../parts/common.ftl" as c>
<#import "../parts/login.ftl" as l>

<@c.page>


    <div>
        <@l.logout />
    </div>

    <a class="btn btn-primary" data-toggle="collapse" href="#messAdding" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add Student card
    </a>
    <div class="collapse" id="messAdding">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <div class="form-group">
                    <input type="text" class="form-control" name="cNum" placeholder="Card number" />
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="fName" placeholder="Full name">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="doB" placeholder="Date of Birth(FORMAT - DD.MM.YYYY ONLY!!!)">
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
            <form method="get" action="filterStudents" class="form-inline">
                <input type="text" name="filterStudents" class="form-control" placeholder="Search..."/>
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>



    <div class="card-columns" >
        <#list students as s>
            <div class="card my-3" style="width: 18rem;">
                <div class="card-body">

                    <div class="card-title">
                        <h1 style="color:#0000ff">Card №${s.cardNumber}</h1>
                    </div>

                    <div class="card-subtitle mb-2 text-muted">
                        <h2 style="color:#00ffff">${s.fullName}</h2>
                    </div>

                    <div class="m-2">
                        <span><p style="color: #00ffff">Date of Birth: </p> ${s.getDate(s.dateOfBirth)}</span>
                    </div>

                    <div class="m-2">
                        <span><p style="color: #00ffff">Parent's address:</p> ${s.addressOfParents}</span>
                    </div>

                    <div class="m-2">
                        <span><p style="color: #00ffff">Faculty name:</p> ${s.faculty.name}</span>
                    </div>

                    <div class="m-2">
                        <span><p style="color: #00ffff">Group name:</p> ${s.groupOfStuds.cipher}</span>
                    </div>

                    <div class="m-2">
                        <span><p style="color: #00ffff">Dormitory number:</p> ${s.dormitory.dormNumber}</span>
                    </div>

                    <div class="m-2">
                        <span><p style="color: #00ffff">Room number:</p> ${s.room}</span>
                    </div>
                    <a href="/edit/${s.getId()}" class="card-link">Edit</a>
                    <a href="/del/${s.getId()}" class="card-link">Delete</a>

                </div>
            </div>
        </#list>
    </div>



</@c.page>