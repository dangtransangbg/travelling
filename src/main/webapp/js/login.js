
$("#loginForm").submit(function (e) {
    e.preventDefault();
    var formData = $("#loginForm").serializeArray();
    var data = {};
    $.each(formData, function (i, v) {
        data["" + v.name + ""] = v.value;
    });
    console.log(data);
    $.ajax({
        url: "/admin/auth",
        method: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (result) {
            localStorage.setItem("access-token", result.data);
            window.location.href = "/admin";
            // console.log(localStorage.getItem("access-token"));
        },
        error: function (err) {
            alert("Sai tài khoản hoặc mật khẩu")
            console.log(JSON.stringify(err))
        }

    })
});
