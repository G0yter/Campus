<#import "../parts/common.ftl" as c>
<@c.page>
    <h1 style="color: chartreuse">Faculty Editor</h1>


    <a class="btn btn-primary" data-toggle="collapse" href="#messAdding" role="button" aria-expanded="false" aria-controls="collapseExample">
        Update Faculty ${faculty.getName()}
    </a>
    <div class="collapse" id="messAdding">
        <div class="form-group mt-3">
            <form method="post" action="/updateFaculty" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <div class="form-group">
                    <input type="text" class="form-control" name="fNum" placeholder="Faculty name" value="${faculty.name}"/>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="phone" placeholder="Phone" value="${faculty.phone}">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="dName" placeholder="Dean name" value="${faculty.decanName}">
                </div>
                <div>
                    <input type="hidden" value="${faculty.getId()}" name="id">
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

    <a href="/faculties" class="card-link">Back</a>

    <h5>You delete every Student and Group by updating his faculty! Be careful</h5>

</@c.page>
