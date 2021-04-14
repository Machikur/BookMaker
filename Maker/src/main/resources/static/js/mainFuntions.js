function timerJob() {
    var today = new Date();
    var hour = today.getHours();
    var minute = today.getMinutes();
    if (minute < 10) minute = "0" + minute;
    var second = today.getSeconds();
    if (second < 10) second = "0" + second;
    document.getElementById("timer").innerHTML = +hour + ":" + minute + ":" + second;
    setTimeout(timerJob, 1000);
}

function setTimer() {
    var seconds = document.getElementById("seconds").textContent;
    var hours = document.getElementById("hours").textContent;
    var minutes = document.getElementById("minutes").textContent;

    if (seconds > 0) {
        seconds -= 1;
        if (seconds < 10) {
            seconds = "0" + seconds;
        }
        document.getElementById("seconds").innerHTML = seconds;
    } else {
        document.getElementById("seconds").innerHTML = "59";
        if (minutes > 0) {
            minuteDown();
        } else {
            if (hours > 0) {
                document.getElementById("minutes").innerHTML = "59";
                hourDown();
            } else {
                clearTimeout(0);
                document.getElementById("timeCounter").style = "color:red";
                document.getElementById("timeCounter").innerHTML = "Mecz zakończony";
                const button = document.getElementById("ticketButton");
                button.disabled = true;
            }
        }
    }
    setTimeout(setTimer, 1000);
}

function hourDown() {
    var hour = document.getElementById("hours").textContent;
    document.getElementById("hours").innerHTML = String(hour - 1);
}

function minuteDown() {
    var minute = document.getElementById("minutes").textContent;
    minute = minute - 1;
    if (minute < 10) minute = "0" + minute;
    document.getElementById("minutes").innerHTML = String(minute);
}






