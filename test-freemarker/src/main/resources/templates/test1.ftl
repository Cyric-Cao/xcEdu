<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hello World!</title>
</head>
<body>
Hello ${name}!
<br/>
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
        <td>日期</td>
    </tr>
    <#if stus??> <#--'??' 判断是否为空 不为空为 true，为空则为 false -->
        <#list stus as stu>
            <tr>
                <td>${stu_index + 1}</td>
                <td <#if stu.name == '小明'>style="background: cornflowerblue" </#if>>${stu.name}</td>
                <td>${stu.age}</td>
                <td <#if (stu.money > 300)>style="background: cornflowerblue" </#if>>${stu.money}</td>
                <td>年月日：${stu.birthday?date}  年月日时分秒：${stu.birthday?datetime}  自定义时间格式化：${stu.birthday?string("YYYY年MM月dd日 HH时mm分ss秒")}</td>
            </tr>
        </#list>
    </#if>
</table>
遍历 stuMap（map）中的数据,第一种方法：在中括号中填写 map 的 key，第二种方法：在 map 后年直接 加 “点 key”
<br/>
姓名: ${(stuMap['stu1'].name)!''}<br/>
年龄：${(stuMap['stu1'].age)!''}<br/>
姓名: ${(stuMap.stu1.name)!''}<br/>
年龄：${(stuMap.stu1.age)!''}<br/>

姓名: ${(stuMap['stu2'].name)!''}<br/>
年龄：${(stuMap['stu2'].age)!''}<br/>
姓名: ${(stuMap.stu2.name)!''}<br/>
年龄：${(stuMap.stu2.age)!''}<br/>
<br/>
使用 list 标签遍历 map
<br/>
<#list stuMap?keys as k>
    姓名: ${stuMap[k].name}<br/>
    年龄：${stuMap[k].age}<br/>
</#list>

</body>
</html>