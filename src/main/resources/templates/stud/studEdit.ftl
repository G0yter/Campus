<#import "../parts/common.ftl" as c>
<@c.page>
    <h1 style="color: chartreuse">Student Card Editor</h1>
    <a class="btn btn-primary" data-toggle="collapse" href="#messAdding" role="button" aria-expanded="false" aria-controls="collapseExample">
        Edit Student card № ${student.cardNumber}
    </a>
    <div class="collapse" id="messAdding">
        <div class="form-group mt-3">
            <form method="post" action="/update" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="editStudentFile" id="editStudFile">
                        <label class="custom-file-label" for="editStudFile">Choose file</label>
                    </div>
                </div>


                <div class="form-group">
                    <input type="text" class="form-control" name="cNum" placeholder="Card number" value="${cardNum}" />
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="fName" placeholder="Full name" value="${student.fullName}">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="doB" placeholder="Date of Birth(FORMAT - DD.MM.YYYY ONLY!!!)" value="${dateOfBirth}">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="pAddrs" placeholder="Parent's Address" value="${student.addressOfParents}">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="facName" placeholder="Faculty name" value="${student.faculty.getName()}">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="grCipher" placeholder="Group cipher" value="${student.groupOfStuds.cipher}">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="dormNum" placeholder="Dormitory number" value="${student.dormitory.dormNumber}">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="room" placeholder="Room number" value="${student.room}">
                </div>

                <div>
                    <input type="hidden" value="${student.getId()}" name="id">
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
    <a href="/main" class="card-link">Back</a>

</@c.page>