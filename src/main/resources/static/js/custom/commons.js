$("#btlogin").click(loginin);

function loginin() {
    $.ajax({
        url:"/Index/login/do",
        data:{
            "email":$("input[name='email']").val(),
            "password":$("input[name='password']").val()
        },
        type:"POST",
        dataType:"json",
        timeout:3000,
        async:true,
        success:function (result) {
            if(result.message === "success"){
                alert("登陆成功！");
                window.location.herf="/Index/index";
            }
            else
                alert("登陆失败！");
        },
        error:function () {
            alert("登陆失败！");
        }
    });
}

function register(){
    if(!checkPasswordValue || !checkEmailValue || !checkUserNameValue){
        alert("请检查您的注册信息！");
    }else {
        $.ajax({
            url: "/Index/register/do",
            data: {
                "email": $("input[name='email']").val(),
                "password": $("input[name='password']").val(),
                "userName": $("input[name='userName']").val()
            },
            type: "POST",
            dataType: "json",
            timeout: 3000,
            async: true,
            success: function (result) {
                if (result.message === "success") {
                    alert("注册成功！");
                    window.location.href = "/Index/login";
                }
                else {
                    alert("注册失败！");
                }
            },
            error: function () {
                alert("注册失败！");
            }
        });
    }
}

function checkUserName() {
    $.ajax({
        url:"/Index/register/checkUserName",
        data:{
            "userName":$("input[name='userName']").val(),
        },
        type:"POST",
        dataType:"json",
        timeout:3000,
        async:true,
        success:function (result) {
            if(result.message === "isNotUsed") {
                $("#userNameNotice").text("");
                checkEmailValue = true;
            }
            else{
                $("#userNameNotice").text("该用户名已经被占用！");
                checkEmailValue = false;
            }
        },
        error:function () {
            $("#userNameNotice").text("服务器出错！");
            checkEmailValue = false;
        }
    });
}

function checkEmail() {
    $.ajax({
        url:"/Index/register/checkEmail",
        data:{
            "email":$("input[name='email']").val(),
        },
        type:"POST",
        dataType:"json",
        timeout:3000,
        async:true,
        success:function (result) {
            if(result.message === "isNotUsed"){
                $("#emailNotice").text("");
                checkUserNameValue = true;
            }
            else{
                $("#emailNotice").text("该邮箱已经被占用");
                checkUserNameValue = false;
            }
        },
        error:function () {
            $("#emailNotice").text("服务器出错！");
            checkUserNameValue = false;
        }
    });
}

function checkPassword() {
    if($("input[name='password']").val() == $("input[name='password2']").val()){
        checkPasswordValue = true;
        $("#passwordNotice").text("");
    }
    else{
        checkPasswordValue = false;
        $("#passwordNotice").text("两次输入密码不一样！");
    }
}