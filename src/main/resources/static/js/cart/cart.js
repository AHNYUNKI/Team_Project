function deleteItem() {
    // 여기에 삭제 동작에 필요한 데이터를 준비합니다.
    var itemId = document.getElementById('item_id').value;  // 삭제할 아이템의 ID라고 가정하고 예시로 지정

    // AJAX 호출을 생성합니다.
    var xhr = new XMLHttpRequest();

    // AJAX 요청이 완료되었을 때 호출될 콜백 함수를 정의합니다.
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // 성공적으로 처리될 때 실행할 코드를 여기에 작성합니다.
                console.log('삭제 성공!');
            } else {
                // 오류가 발생했을 때 실행할 코드를 여기에 작성합니다.
                console.error('삭제 실패: ' + xhr.status);
            }
        }
    };

    // 삭제할 아이템의 ID를 URL 파라미터로 포함하여 전송합니다.
    var url = '/carts/' + itemId;  // 여기에 컨트롤러의 URL을 넣어주세요

    // DELETE 메서드를 사용하여 AJAX 요청을 전송합니다.
    xhr.open('POST', url);

    // AJAX 요청을 전송합니다.
    xhr.send();
}

// 숫자 3자리 콤마찍기
Number.prototype.formatNumber = function(){
    if(this==0) return 0;
    let regex = /(^[+-]?\d+)(\d{3})/;
    let nstr = (this + '');
    while (regex.test(nstr)) nstr = nstr.replace(regex, '$1' + ',' + '$2');
    return nstr;
};