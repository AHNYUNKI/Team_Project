const updateBtn = document.querySelector('#updateBtn');
updateBtn.addEventListener('click', updateFn);

function updateFn(e) {
    e.preventDefault();

    const data = {

        id: document.querySelector('#id').value,
        title: document.querySelector('#title').value,
        content: document.querySelector('#content').value,
        writer: document.querySelector('#writer').value,
    };

    const url = "/post/postUpdate"
    const method = "POST";

    fetch(url, {
        method: method,
        headers: {
            "Content-Type": "application/json"
        }, body: JSON.stringify(data)
    }).then((response) => response.json())
        .then((data) => {
                alert("회원수정 Success")
                document.querySelector('#id').value = data.id;
                document.querySelector('#title').value = data.title;
                document.querySelector("#content").value = data.content;
                document.querySelector("#writer").value = data.writer;
            }
        );

}