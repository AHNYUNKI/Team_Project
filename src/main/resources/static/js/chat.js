let stompClient = null;
$(function (){
    $("#question").keyup(qKeyupFn); // window load 시 처음 실행
});

function openChat(){
    setConnectStated(true); // 접속 버튼 클릭시
    connect();
}
function showMessage(message){
    $("#chat-content").append();
    // 대화창 스크롤을 항상 최하위에 배치
    $("#chat-content").scrollTop($("#chat-content").prop("scrollHeight"));
}

// 접속 버튼 클릭시
function setConnectStated(isTrue){
    if(isTrue){
        $("#btn-chat-open").hide();
        $("#chat-disp").show();
    }else{
        $("#btn-chat-open").show();
        $("#chat-disp").hide();
    }
    $("#chat-content").html("");

}

// 연결해제
function disconnect(){
    if(stompClient !== null){
        stompClient.disconnect();
    }
    setConnectStated(false);
    console.log("Disconnected");
}

function connect() {
    // 웹소켓 연결
    let socket = new SockJS('/chatEndpoint');
    // Stomp 연결
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame){
        console.log('Connected: ' + frame);
        // 브라우저에서 메시지를 수신하려면 STOMP 클라이언트가 먼저 대상을 구독
        // subcribe()방법을 사용하여 대상에 가입
        // 2개의 필수 인수를 사용. destination 목적지에 해당하는 문자열, callback,
        stompClient.subscribe('/topic/greetings', function (botMessage){
            // 클라이언트에서 메시지 보낼때
            showMessage(JSON.parse(botMessage.body).message);
        });
        // @MessageMappping("/hello") -> 처음 연결시
        stompClient.send("/app/hello", {}, JSON.stringify({'content': 'guest'}));
    });
}

function inputTagString(text){
    let now = new Data();
    let ampm = (now.getHours() > 11) ? "오후":"오전";
    let time = now.getHours()%12+":"+now.getMinutes();
    let message=`
        <div class="msg user flex end" style="justify-content: right">
            <div class="message">
                <div class="part" style="text-align: right">
                    <p style="margin:0">${text}</p>
                </div>
                <div class="time" style="text-align: right">${time}</div>
            </div>
        </div>
    `;
    return message;
}

// <div class='menu-item'><span onclick='menuclickFn(event)'>상품문의</span></div>
//메뉴클릭시 메뉴 텍스트 화면에 표시 ->  메시지 전송
function  menuclickFn(event){
    let text=event.target.innerText.trim();
    let  message = inputTagString(text);
    showMessage(message);
    stompClient.send("/app/message", {}, JSON.stringify({'content': text}));
}

function qKeyupFn(event){
    if(event.keyCode!=13) return // 엔터키 제외
    msgSendClickFn() // 실행
}


// 전송버튼 클릭되면 질문을 화면에 표현
function msgSendClickFn(){
    let question = $("#question").val().trim();
    if (question=="" || question.length < 2) return;
    let message = inputTagString(question);
    showMessage(message);
    $("#question").val("");
    stompClient.send("/app/message", {}, JSON.stringify({'content':question}));

}
