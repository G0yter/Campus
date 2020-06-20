<#import "../parts/common.ftl" as c>


<@c.page>
    <div class="card text-center"
         style="background-color: rgba(0,0,0,0.5) !important; height: auto; width: 67rem;">
        <div class="card-body">
            <div class="card-title">
                <h1 style="color:#acffee">Edit Group number ${group.getCipher()}</h1>
            </div>
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
                                <input type="text" class="form-control" name="cipher" placeholder="Group cipher"
                                       value="${group.cipher}">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" name="facName" placeholder="Faculty Name"
                                       value="${group.faculty.name}">
                            </div>
                            <div>
                                <input type="hidden" value="${group.getId()}" name="id">
                            </div>

                            <div class="form-group">
                                <button type="submit" class="btn btn-primary">Update</button>
                            </div>

                        </form>
                    </div>
                </form>
            </div>
        </div>
    </div>

</@c.page>