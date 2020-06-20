<#import "../parts/common.ftl" as c>
<@c.page>
    <div class="card text-center"
         style="background-color: rgba(0,0,0,0.5) !important; height: auto; width: 67rem;">
        <div class="card-body">
            <div class="card-title">
                <h1 style="color:#acffee">Update Faculty ${faculty.getName()}</h1>
            </div>

            <div class="form-group mt-3">
                <form method="post" action="/updateFaculty" enctype="multipart/form-data">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                    <div class="form-group">
                        <div class="custom-file">
                            <input type="file" name="editFacultyFile" id="editFacFile">
                            <label class="custom-file-label" for="editFacFile">Choose file</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <input type="text" class="form-control" name="fNum" placeholder="Faculty name"
                               value="${faculty.name}"/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="phone" placeholder="Phone"
                               value="${faculty.phone}">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="dName" placeholder="Dean name"
                               value="${faculty.decanName}">
                    </div>
                    <div>
                        <input type="hidden" value="${faculty.getId()}" name="id">
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Update</button>
                    </div>
                </form>
            </div>
        </div>
    </div>


</@c.page>
