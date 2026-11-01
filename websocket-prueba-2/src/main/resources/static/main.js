'use strict';
const url = "ws://localhost:8080/chat";
const client = new StompJs.Client({
    brokerURL: url,
});

let buttonConnect;
let buttonDisConnect;
let buttonSendMessage;
let clearMessages
let messages;

window.addEventListener('load', () => {
    buttonConnect = document.getElementById("connect");
    buttonDisConnect = document.getElementById("disconnect");
    buttonSendMessage = document.getElementById("sendMessage");
    clearMessages = document.getElementById("clearMessages");
    messages = document.getElementById("messages");

    buttonDisConnect.disabled = true;
    buttonSendMessage.disabled = true;
})

client.onConnect = () => {
    client.subscribe('/topic/greetings', (greeting) => {
        printMessage(messages, greeting.body);
    });
};

client.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

client.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function connect() {
    client.activate();
    buttonConnect.disabled = true;
    buttonDisConnect.disabled = false;
    buttonSendMessage.disabled = false;
    clearMessage(messages);
}

function disconnect() {
    client.deactivate();
    buttonConnect.disabled = false;
    buttonDisConnect.disabled = true;
    buttonSendMessage.disabled = true;
    clearMessage(messages);
}

function sendMessage() {
    client.publish({
        destination: '/app/message',
        body: JSON.stringify({name: "Spring Boot Tutorial", sessionId:''})
    });
}

function printMessage(textId, data) {
    textId.innerHTML += data + "<br/>";
}

function clearMessage(textId) {
    textId.innerHTML = "";
}