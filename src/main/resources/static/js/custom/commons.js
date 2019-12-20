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
                toastr.success("登陆成功!","正在跳转至主页");
                window.location.href="/Index/index";

            }
            else
                toastr.error("登陆失败！","请检查您的邮箱和密码是否正确");
        },
        error:function () {
            toastr.error("系统繁忙！","请稍后再试");
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
                    toastr.success("注册成功!","正在跳转至登陆");
                    window.location.href = "/Index/login";
                }
                else {
                    toastr.error("注册失败！","请检查您信息是否正确");
                }
            },
            error: function () {
                toastr.error("系统繁忙！","请稍后再试");
            }
        });
    }
}

function checkUserName() {
    if ($("input[name='userName']").val() === "") {
        $("#userNameNotice").text("用户名不能为空！");
        checkUserNameValue = false;
    }
    else {
        $.ajax({
            url: "/Index/register/checkUserName",
            data: {
                "userName": $("input[name='userName']").val(),
            },
            type: "POST",
            dataType: "json",
            timeout: 3000,
            async: true,
            success: function (result) {
                if (result.message === "isNotUsed") {
                    $("#userNameNotice").text("");
                    checkUserNameValue = true;
                }
                else {
                    $("#userNameNotice").text("该用户名已经被占用！");
                    checkUserNameValue = false;
                }
            },
            error: function () {
                $("#userNameNotice").text("服务器出错！");
                checkUserNameValue = false;
            }
        });
    }
}

function checkEmail() {
    if($("input[name='email']").val() === ""){
        $("#emailNotice").text("邮箱不能为空！");
        checkEmailValue = false;
    }
    else {
        $.ajax({
            url: "/Index/register/checkEmail",
            data: {
                "email": $("input[name='email']").val(),
            },
            type: "POST",
            dataType: "json",
            timeout: 3000,
            async: true,
            success: function (result) {
                if (result.message === "isNotUsed") {
                    $("#emailNotice").text("");
                    checkEmailValue = true;
                }
                else {
                    $("#emailNotice").text("该邮箱已经被占用！");
                    checkEmailValue = false;
                }
            },
            error: function () {
                $("#emailNotice").text("服务器出错！");
                checkEmailValue = false;
            }
        });
    }
}

function checkPassword() {
    if($("input[name='password']").val() === ""){
        checkPasswordValue = false;
        $("#passwordNotice").text("密码不能为空！");
    }else if($("input[name='password']").val() === $("input[name='password2']").val()){
        checkPasswordValue = true;
        $("#passwordNotice").text("");
    }
    else{
        checkPasswordValue = false;
        $("#passwordNotice").text("两次输入密码不一样！");
    }
}