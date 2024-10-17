importScripts("https://www.gstatic.com/firebasejs/8.10.0/firebase-app.js");
importScripts("https://www.gstatic.com/firebasejs/8.10.0/firebase-messaging.js");

const firebaseConfig = {
    apiKey: "AIzaSyBVu5U46BmdTD6avNcq9JQDzyFMce5USRA",
    authDomain: "toy-project-2024-621cb.firebaseapp.com",
    projectId: "toy-project-2024-621cb",
    storageBucket: "toy-project-2024-621cb.appspot.com",
    messagingSenderId: "258677921235",
    appId: "1:258677921235:web:97183416ef7d4818e78b26",
    measurementId: "G-1BTZNVVFFD"
};

const app = firebase.initializeApp(firebaseConfig);
const messaging = firebase.messaging();
