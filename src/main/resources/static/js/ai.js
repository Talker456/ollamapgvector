//const token = document.querySelector('meta[name="_csrf"]').content;
//const header = document.querySelector('meta[name="_csrf_header"]').content;


//USING SOCKET EXAMPLE
<!--    let socket = new WebSocket("ws://localhost:8080/chat");-->

<!--    // When the connection is established, listen for messages-->
<!--    socket.onmessage = function (event) {-->
<!--        let messagesDiv = document.getElementById('messages');-->
<!--        let newMessage = document.createElement("p");-->
<!--        newMessage.textContent = "AI: " + event.data;-->
<!--        messagesDiv.appendChild(newMessage);-->
<!--    };-->

<!--    // Send the user input to the server-->
<!--    function sendMessage() {-->
<!--        let messageInput = document.getElementById('message');-->
<!--        let message = messageInput.value;-->
<!--        socket.send(message); // Send the message to WebSocket server-->

<!--        // Display the user's message-->
<!--        let messagesDiv = document.getElementById('messages');-->
<!--        let userMessage = document.createElement("p");-->
<!--        userMessage.textContent = "You: " + message;-->
<!--        messagesDiv.appendChild(userMessage);-->
<!--        messageInput.value = ''; // Clear the input field-->
<!--    }-->

