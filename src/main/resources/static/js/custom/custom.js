function wait(href, flag) {
    waitTmp(href, flag);
    return;
    var time = 1;
    var timer = setInterval(function () {
        if (time > 0) {
            time--;
        } else {
            if (flag === 0)
                window.location.href = href;
            else if (flag === -1)
                top.location.href = href;
            timer.clearInterval();
        }
    }, 500);
}

function waitTmp(href, flag) {
    if (flag === 0)
        window.location.href = href;
    else if (flag === -1)
        top.location.href = href;
}

function judgeTime(date1, date2) {
    var oDate1 = new Date(Date.parse(date1));
    var oDate2 = new Date(Date.parse(date2));

    return oDate1 > oDate2;
}

function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] === variable) {
            return pair[1];
        }
    }
    return false;
}

function makeCode(id, text) {
    var qrcode = new QRCode(document.getElementById(id), {
        width: 100,
        height: 100
    });
    qrcode.makeCode(text);
}
