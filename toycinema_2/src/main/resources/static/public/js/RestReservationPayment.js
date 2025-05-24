// 전체 렌더링
window.addEventListener("DOMContentLoaded", () => {


    const offcanvasElement = document.querySelector('#offcanvasBottom');
    if (offcanvasElement) {
        bsOffcanvas = bootstrap.Offcanvas.getOrCreateInstance(offcanvasElement);
    }
    renderingPage();
    selectedSeatCount()
    confirm();
});


let seatCount;

const urlParam = new URL(window.location.href).searchParams;
const filmPlayingTableId = urlParam.get("id");

const renderingPage = function () {

    fetch(`/api/main/playingTableInfoCanvas?id=${filmPlayingTableId}`)
        .then(response => {
            return response.json();
        }).then(json => {
        // console.log(json);
        const colFilmNameInCanvas = document.getElementById("colFilmNameInCanvas");
        //
        const colFilmRatingInCanvas = document.getElementById("colFilmRatingInCanvas");
        //
        colFilmNameInCanvas.innerText = '';
        colFilmNameInCanvas.innerText = json['filmDto'].filmName;
        colFilmRatingInCanvas.innerText = '';
        colFilmRatingInCanvas.innerText = json['filmDto'].filmRating + '세 이상 관람가';
        //
        const colTheaterNameInCanvas = document.getElementById("colTheaterNameInCanvas");
        colTheaterNameInCanvas.innerText = '';
        colTheaterNameInCanvas.innerText = json['theaterDto'].name;
        //
        const colDateInCanvas = document.getElementById("colDateInCanvas");
        colDateInCanvas.innerText = '';
        colDateInCanvas.innerText = json['filmPlayingTableDto']['date'].substring(5);
        //
        const colFilmTypeInCanvas = document.getElementById("colFilmTypeInCanvas");
        const colBoxNameInCanvas = document.getElementById("colBoxNameInCanvas");
        const colTimeInCanvas = document.getElementById("colTimeInCanvas");
        //
        colFilmTypeInCanvas.innerText = '';
        colFilmTypeInCanvas.innerText = json['typeDto']['typeName'];
        //
        colBoxNameInCanvas.innerText = '';
        colBoxNameInCanvas.innerText = json['boxDto']['name'];
        //
        colTimeInCanvas.innerText = ''
        colTimeInCanvas.innerText = json['filmPlayingTableDto']['time'].substring(0, 5);
        //

        const colTheaterName = document.getElementById("colTheaterName");
        colTheaterName.innerHTML = '';
        colTheaterName.innerText = json['theaterDto']['name'];

        const colBoxNameAndLocation = document.getElementById("colBoxNameAndLocation");
        colBoxNameAndLocation.innerHTML = '';
        colBoxNameAndLocation.innerText = json['boxDto']['name'] + ' (' + json['boxDto']['location'] + ')';

        const remainSeat = document.getElementById("remainSeat");
        remainSeat.innerHTML = '';
        remainSeat.innerText = '남은 좌석 : ' + (Number(json['boxDto']['capacity']) - Number(json['bookedSeatCount'])) + '/' + json['boxDto']['capacity'] + '석';

        const date_time = document.getElementById("date_time");
        date_time.innerHTML = '';


        const parts = json['filmPlayingTableDto']['date'].split('-');
        const year = parseInt(parts[0]);
        const month = parseInt(parts[1]); // - 1; // 월은 0부터 시작
        const day = parseInt(parts[2]);
        const localDate = new Date(year, month, day);

        const weekdays = ['일', '월', '화', '수', '목', '금', '토'];
        const monthFormatted = String(localDate.getMonth() + 1).padStart(2, '0'); // getMonth()는 0부터 시작
        const dayFormatted = String(localDate.getDate()).padStart(2, '0');
        const dayOfWeek = weekdays[localDate.getDay()]; // getDay()는 0(일요일)부터 6(토요일)까지 반환


        // const time = json['filmPlayingTableDto']['time'].split(5);

        const timeString = `${json['filmPlayingTableDto']['time']}`
        const time = timeString.substring(0, 5);


        const [hours, minutes] = time.split(':').map(Number);
        const date = new Date(); // 현재 날짜와 시간으로 Date 객체 생성
        date.setHours(hours);    // 시간을 12로 설정
        date.setMinutes(minutes); // 분을 00으로 설정
        date.setSeconds(0);      // 초를 0으로 설정 (정확성을 위해)
        // date.setMilliseconds(0); // 밀리초를 0으로 설정

        date.setMinutes(date.getMinutes() + json['filmDto']['runningTime']);
        const resultHours = date.getHours();
        const resultMinutes = date.getMinutes();

        const formattedHours = String(resultHours).padStart(2, '0');
        const formattedMinutes = String(resultMinutes).padStart(2, '0');

        // date_time.innerText = `${year}.${month}.${day} (${dayOfWeek}) ${time}`;
        date_time.innerText = `${year}.${month}.${day} (${dayOfWeek}) ${time} ~ ${formattedHours}:${formattedMinutes}`;


        bsOffcanvas?.show();
    });
};

const selectedSeatCount = function (count) {

    const allCountPeopleDivs = document.querySelectorAll('[id^="countPeople_"]'); // ID가 "countPeople_"로 시작하는 모든 요소

    // 2. 이전에 선택된 모든 항목의 'selected-item' 클래스를 제거하여 스타일을 초기화합니다.
    allCountPeopleDivs.forEach(div => {
        div.classList.remove('selected-item');
    });

    // 3. 클릭된 현재 항목에 'selected-item' 클래스를 추가합니다.
    const clickedDiv = document.getElementById(`countPeople_${count}`);
    if (clickedDiv) {
        seatCount = count;
        clickedDiv.classList.add('selected-item');
    }

    if (seatCount !== undefined) {
        const colSelectedSeatCount = document.getElementById("colSelectedSeatCount");
        colSelectedSeatCount.innerHTML = '';
        colSelectedSeatCount.innerText = count;

        const totalPrice = document.getElementById("totalPrice");
        let price;

        fetch(`/api/main/filmPlayingTableDtoForPrice?id=${filmPlayingTableId}`)
            .then(response => {
                return response.json();
            }).then(json => {
            totalPrice.innerHTML = '';
            price = Number(count) * Number(json['filmPlayingTableDto']['price']);
            totalPrice.innerText = price.toString();
        });


        const selectConfirm = document.getElementById("selectConfirm");
        selectConfirm.onclick = () => {
            if (memberDto === null) {
                if (confirm("세션이 끊어졌습니다")) {
                    location.href = `/member/loginPage`;
                }
            } else {
                popupModal();
                modalPayment(price);
            }
        };
    }

};

const popupModal = function () {
    // const paymentModalElement = document.getElementById("paymentModal");
    const paymentModal = bootstrap.Modal.getOrCreateInstance("#paymentModal");
    paymentModal.show();
}

const modalPayment = function (price) {
    fetch(`/api/main/playingTableInfoCanvas?id=${filmPlayingTableId}`)
        .then(response => {
            return response.json();
        }).then(json => {
        // console.log(json);
        const colFilmNameInModal = document.getElementById("colFilmNameInModal");
        //
        const colFilmRatingInModal = document.getElementById("colFilmRatingInModal");
        //
        colFilmNameInModal.innerText = '';
        colFilmNameInModal.innerText = json['filmDto'].filmName;
        colFilmRatingInModal.innerText = '';
        colFilmRatingInModal.innerText = json['filmDto'].filmRating + '세 이상 관람가';
        //
        const colTheaterNameInModal = document.getElementById("colTheaterNameInModal");
        colTheaterNameInModal.innerText = '';
        colTheaterNameInModal.innerText = json['theaterDto'].name;
        //
        const colDateInModal = document.getElementById("colDateInModal");
        colDateInModal.innerText = '';
        colDateInModal.innerText = json['filmPlayingTableDto']['date'].substring(5);
        //
        const colFilmTypeInModal = document.getElementById("colFilmTypeInModal");
        // const colBoxNameInModal = document.getElementById("colBoxNameInModal");
        const colTimeInModal = document.getElementById("colTimeInModal");
        //
        colFilmTypeInModal.innerText = '';
        colFilmTypeInModal.innerText = json['typeDto']['typeName'];
        //
        // colBoxNameInModal.innerText = '';
        // colBoxNameInModal.innerText = json['boxDto']['name'];
        //
        // colTimeInModal.innerText = ''
        // colTimeInModal.innerText = json['filmPlayingTableDto']['time'].substring(0, 5);
        //

        // const colTheaterName = document.getElementById("colTheaterName");
        // colTheaterName.innerHTML = '';
        // colTheaterName.innerText = json['theaterDto']['name'];

        const colBoxNameInModal = document.getElementById("colBoxNameInModal");
        colBoxNameInModal.innerHTML = '';
        colBoxNameInModal.innerText = json['boxDto']['name'] + ' (' + json['boxDto']['location'] + ')';

        // const remainSeat = document.getElementById("remainSeat");
        // remainSeat.innerHTML = '';
        // remainSeat.innerText = '남은 좌석 : ' + (Number(json['boxDto']['capacity']) - Number(json['bookedSeatCount'])) + '/' + json['boxDto']['capacity'] + '석';

        // const date_time = document.getElementById("date_time");
        // date_time.innerHTML = '';


        const parts = json['filmPlayingTableDto']['date'].split('-');
        const year = parseInt(parts[0]);
        const month = parseInt(parts[1]); // - 1; // 월은 0부터 시작
        const day = parseInt(parts[2]);
        const localDate = new Date(year, month, day);

        const weekdays = ['일', '월', '화', '수', '목', '금', '토'];
        const monthFormatted = String(localDate.getMonth() + 1).padStart(2, '0'); // getMonth()는 0부터 시작
        const dayFormatted = String(localDate.getDate()).padStart(2, '0');
        const dayOfWeek = weekdays[localDate.getDay()]; // getDay()는 0(일요일)부터 6(토요일)까지 반환


        // const time = json['filmPlayingTableDto']['time'].split(5);

        const timeString = `${json['filmPlayingTableDto']['time']}`
        const time = timeString.substring(0, 5);


        const [hours, minutes] = time.split(':').map(Number);
        const date = new Date(); // 현재 날짜와 시간으로 Date 객체 생성
        date.setHours(hours);    // 시간을 12로 설정
        date.setMinutes(minutes); // 분을 00으로 설정
        date.setSeconds(0);      // 초를 0으로 설정 (정확성을 위해)
        // date.setMilliseconds(0); // 밀리초를 0으로 설정

        date.setMinutes(date.getMinutes() + json['filmDto']['runningTime']);
        const resultHours = date.getHours();
        const resultMinutes = date.getMinutes();

        const formattedHours = String(resultHours).padStart(2, '0');
        const formattedMinutes = String(resultMinutes).padStart(2, '0');

        // date_time.innerText = `${year}.${month}.${day} (${dayOfWeek}) ${time}`;
        colTimeInModal.innerText = `${time} ~ ${formattedHours}:${formattedMinutes}`;

        const colSelectedSeatCountInModal = document.getElementById("colSelectedSeatCountInModal");
        colSelectedSeatCountInModal.innerHTML = '';
        colSelectedSeatCountInModal.innerText = seatCount;

        const totalPriceInModal = document.getElementById("totalPriceInModal");
        totalPriceInModal.innerHTML = '';
        totalPriceInModal.innerText = price;

    });
}

const confirm = function () {
    const payConfirm = document.getElementById("payConfirm");
    payConfirm.onclick = () => {

        location.href = `/main/paymentCheck?memberId=${memberDto.id}&filmPlayingTableId=${filmPlayingTableId}&seatCount=${seatCount}`;
    };
}