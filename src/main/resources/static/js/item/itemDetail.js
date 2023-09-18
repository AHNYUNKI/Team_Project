
    // 수량 입력 필드와 합계 표시 요소를 가져옵니다.
    var quantityInput = document.getElementById("quantity");
    var totalPriceElement = document.getElementById("totalPrice");
    var increaseButton = document.getElementById("increaseQuantity");
    var decreaseButton = document.getElementById("decreaseQuantity");
    var productId = "상품ID를 여기에 할당"; // 실제 상품 ID로 변경하세요.

    // 수량 변경 이벤트를 감지하여 합계를 업데이트하는 함수를 정의합니다.
    function updateTotalPrice() {
        var quantity = parseInt(quantityInput.value); // 입력된 수량을 정수로 변환합니다.
        var pricePerItem = 10000; // 상품 1개의 가격을 설정합니다. 필요에 따라 수정하세요.
        var totalPrice = quantity * pricePerItem; // 합계를 계산합니다.

        // 합계를 천 단위 구분 기호(,)를 포함한 문자열로 표시합니다.
        totalPriceElement.innerText = totalPrice.toLocaleString() + "원";
    }

    // + 버튼을 클릭했을 때 수량을 증가시키고 합계를 업데이트합니다.
    increaseButton.addEventListener("click", function () {
        quantityInput.value = parseInt(quantityInput.value) + 1;
        updateTotalPrice();
    });

    // - 버튼을 클릭했을 때 수량을 감소시키고 합계를 업데이트합니다.
    decreaseButton.addEventListener("click", function () {
        var currentQuantity = parseInt(quantityInput.value);
        if (currentQuantity > 1) {
            quantityInput.value = currentQuantity - 1;
            updateTotalPrice();
        }
    });

    // 장바구니에 담기 버튼 클릭 시 실행되는 함수
    function addToCart() {
        var quantity = parseInt(quantityInput.value);
        var totalPrice = parseInt(totalPriceElement.innerText.replace(/[^0-9]/g, '')); // "원" 및 천 단위 구분 기호(,) 제거

        if (quantity > 0 && !isNaN(totalPrice)) {
            var cartItem = {
                productId: productId,
                quantity: quantity,
                totalPrice: totalPrice
            };

            // 장바구니 데이터를 관리하는 배열 또는 객체에 cartItem을 추가합니다.
            // 이 배열 또는 객체는 장바구니 정보를 저장하고 관리하는 데 사용됩니다.
            // 예를 들면:
            // var shoppingCart = [];
            // shoppingCart.push(cartItem);

            // 장바구니에 상품을 추가한 후, 필요한 동작을 수행합니다.
            // 예를 들면, 장바구니 아이콘 업데이트, 장바구니 목록 표시 등을 수행할 수 있습니다.
            alert("장바구니에 상품이 추가되었습니다. 장바구니 페이지로 이동합니다.");

            // 장바구니 페이지로 이동합니다. 이동할 페이지 URL을 지정하세요.
            window.location.href = "/장바구니페이지URL"; // 실제 페이지 URL로 변경하세요.
        } else {
            alert("상품을 올바르게 선택하세요.");
        }
    }

    // 초기에도 합계를 계산하여 표시합니다.
    updateTotalPrice();