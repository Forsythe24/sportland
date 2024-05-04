let stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }

    $("#messages").html("");
}

let sender = null
function connect(id) {
    console.log("Trying to connect");
    let socket = new SockJS("/message-websocket");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("Connected: " + frame);
        setConnected(true);
        stompClient.subscribe("/topic/message/" + id, function (message) {
            let mess = JSON.parse(message.body);
            showMessage(mess.sender + ': ' + mess.text);
        })
    })
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected")
}

function sendMessage(id) {
    sender = $("#name").val()
    showMessageToOthers($("#name").val() + ": " + $("#message").val(), sender);
    stompClient.send("/app/message/" + id, {}, JSON.stringify({'text': $("#message").val(), 'sender': $("#name").val()}));
}

function showMessageToOthers(message, currentSender) {
    if (sender !== currentSender) {
        showMessage(message)
    }
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>")
}

$(function () {

    let id = window.location.href.split("/").pop()

    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect(id);
    });
    $("#disconnect").click(function () {
        disconnect();
    });

    $("#send").click(function () {
        console.log(id)
        sendMessage(id);
    });
})