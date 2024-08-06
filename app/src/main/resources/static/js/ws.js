var stompClient = null;

function connect() {
	var socket = new SockJS('/app/ws');
	stompClient = Stomp.over(socket);

	stompClient.connect({}, function (frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/app/receive', function (messageOutput) {
			showMessage(messageOutput.body);
		});
	});
}

function sendMessage() {
	var messageContent = document.getElementById('message').value;
	document.getElementById('char-count').textContent = `0/100`;
	document.getElementById('message').value = '';
	if(messageContent.trim() !== '') {
        stompClient.send("/app/chat", {}, messageContent);
    } else {
        alert('Message cannot be empty!');
    }
}

function showMessage(message) {
	var messagesBlock = document.getElementById('messages');
	var p = document.createElement('p');
	p.appendChild(document.createTextNode(message));
	messagesBlock.prepend(p);
}

window.onload = function() {
	connect();
}