<#import "parts/common.ftl" as c>

<@c.page>
    <table class="table table-bordered table-dark mt-5">
        <thead>
        <tr>
            <th scope="col">Monday</th>
            <th scope="col">Tuesday</th>
            <th scope="col">Wednesday</th>
            <th scope="col">Thursday</th>
            <th scope="col">Friday</th>
        </tr>
        </thead>
        <tbody>

        <#list lessons as lesson>

            <tr class="table-secondary">
                <td>
                    <#if lesson.getWeekDay().toString() == "MONDAY">
                        <a style="color:#000000; font-weight: bold">${lesson.title} </a> <br/>
                        <a style="color:#000000">${lesson.description} </a>
                    <#else>
                        <a style="color:#000000; font-weight: bold">-</a>
                    </#if>
                </td>
                <td>
                    <#if lesson.getWeekDay().toString() == "TUESDAY">
                        <a style="color:#000000; font-weight: bold">${lesson.title} </a> <br/>
                        <a style="color:#000000">${lesson.description} </a>
                    <#else>
                        <a style="color:#000000; font-weight: bold">-</a>
                    </#if>
                </td>
                <td>
                    <#if lesson.getWeekDay().toString() == "WEDNESDAY">
                        <a style="color:#000000; font-weight: bold">${lesson.title} </a> <br/>
                        <a style="color:#000000">${lesson.description} </a>
                    <#else>
                        <a style="color:#000000; font-weight: bold">-</a>
                    </#if>
                </td>
                <td>
                    <#if lesson.getWeekDay().toString() == "THURSDAY">
                        <a style="color:#000000; font-weight: bold">${lesson.title} </a> <br/>
                        <a style="color:#000000">${lesson.description} </a>
                    <#else>
                        <a style="color:#000000; font-weight: bold">-</a>
                    </#if>
                </td>
                <td>
                    <#if lesson.getWeekDay().toString() == "FRIDAY">
                        <a style="color:#000000; font-weight: bold">${lesson.title} </a> <br/>
                        <a style="color:#000000">${lesson.description} </a>
                    <#else>
                        <a style="color:#000000; font-weight: bold">-</a>
                    </#if>
                </td>
            </tr>
        </#list>

        </tbody>
    </table>

</@c.page>