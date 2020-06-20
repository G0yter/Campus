<#import "../parts/common.ftl" as c>


<@c.page>

    <h1 style="color: chartreuse">Group Editor</h1>


    <a class="btn btn-primary" data-toggle="collapse" href="#messAdding" role="button" aria-expanded="false" aria-controls="collapseExample">
        Edit Group number ${group.getCipher()}
    </a>
    <div class="collapse" id="messAdding">
        <div class="form-group mt-3">
            <form method="post" action="/updateGroup" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="editGroupFile" id="editGroupFile">
                        <label class="custom-file-label" for="editGroupFile">Choose file</label>
                    </div>
                </div>

                <div class="form-group">
                    <input type="text" class="form-control" name="cipher" placeholder="Group cipher" value="${group.cipher}">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="facName" placeholder="Faculty Name" value="${group.faculty.name}">
                </div>
                <div>
                    <input type="hidden" value="${group.getId()}" name="id">
                </div>


                <a>
                    *Enter all forms
                </a>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Update</button>
                </div>

            </form>
        </div>
    </div>

    <a href="/groups" class="card-link">Back</a>

</@c.page>