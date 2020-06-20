<#import "../parts/common.ftl" as c>
<@c.page>
    <h1 style="color: chartreuse">Dormitory Editor</h1>


    <a class="btn btn-primary" data-toggle="collapse" href="#messAdding" role="button" aria-expanded="false" aria-controls="collapseExample">
        Edit Dormitory number ${dormitory.getDormNumber()}
    </a>
    <div class="collapse" id="messAdding">
        <div class="form-group mt-3">
            <form method="post" action="/updateDormitory" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="editDormitoryFile" id="editDormFile">
                        <label class="custom-file-label" for="editDormFile">Choose file</label>
                    </div>
                </div>

                <div class="form-group">
                    <input type="text" class="form-control" name="dNum" placeholder="Dorm number" value="${dormitory.dormNumber}" />
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="dAddr" placeholder="Dormitory address" value="${dormitory.address}">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="phone" placeholder="Phone" value="${dormitory.phone}">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="conName" placeholder="Controller Name" value="${dormitory.commFullName}">
                </div>
                <div>
                    <input type="hidden" value="${dormitory.getId()}" name="id">
                </div>

                <a>
                    *Enter all forms
                </a>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Update </button>
                </div>


            </form>
        </div>
    </div>

    <a href="/dormitories" class="card-link">Back</a>

</@c.page>