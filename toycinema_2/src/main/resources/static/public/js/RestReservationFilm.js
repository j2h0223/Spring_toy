let selectedFilmId;
let selectedTheaterId;
let selectedDate;
let selectedFilmPlayingTableId;

let bsOffcanvas;

// 전체 렌더링
window.addEventListener("DOMContentLoaded", () => {
    resetSelection();
    const offcanvasElement = document.querySelector('#offcanvasBottom');
    if (offcanvasElement) {
        bsOffcanvas = bootstrap.Offcanvas.getOrCreateInstance(offcanvasElement);
    }
    makeConfirm();
});


const resetSelection = function () {
    selectedFilmId = 0;
    selectedTheaterId = 0;
    selectedDate = '';
    updateSelection();
};

const selectFilm = function (id) {
    updateSelection('filmId', id);
}

const selectTheater = function (id) {
    updateSelection('theaterId', id);
}

const selectDate = function (date) {
    updateSelection('date', date);
}

const selectFilmPlayingTable = function (id) {
    updateSelection('filmPlayingTableId', id);
}


const updateSelection = function (type, value) {
    // 중복 선택 시 해제
    if (type === 'filmId') {
        selectedFilmId = (selectedFilmId === value) ? 0 : value;
        selectedFilmPlayingTableId = 0
    } else if (type === 'theaterId') {
        selectedTheaterId = (selectedTheaterId === value) ? 0 : value;
        selectedFilmPlayingTableId = 0
    } else if (type === 'date') {
        selectedDate = (selectedDate === value) ? '' : value;
        selectedFilmPlayingTableId = 0
    } else if (type === 'filmPlayingTableId') {
        selectedFilmPlayingTableId = (selectedFilmPlayingTableId === value) ? 0 : value;
    }

    const BASE_URL = `/api/main/restReservationPage`;
    // const params = new URLSearchParams();
    //
    // if (selectedFilmId !== undefined) params.append('filmId', selectedFilmId);
    // if (selectedTheaterId !== undefined) params.append('theaterId', selectedTheaterId);
    // if (selectedDate !== undefined) params.append('date', selectedDate);
    const params = makeUrl();

    const requestUrl = `${BASE_URL}?${params.toString()}`;

    renderPage(requestUrl);

    renderCanvas();
}

const makeUrl = function () {
    const params = new URLSearchParams();

    if (selectedFilmId !== undefined) params.append('filmId', selectedFilmId);
    if (selectedTheaterId !== undefined) params.append('theaterId', selectedTheaterId);
    if (selectedDate !== undefined) params.append('date', selectedDate);

    return params;
};


const renderCanvas = function () {

    if (!(selectedTheaterId > 0 || selectedFilmId > 0 || selectedDate !== '')) {
        bsOffcanvas?.hide();  // optional chaining
    } else {

        const colFilmNameInCanvas = document.getElementById("colFilmNameInCanvas");
        // colFilmNameInCanvas.innerHTML = '';

        const colFilmRatingInCanvas = document.getElementById("colFilmRatingInCanvas");
        // colFilmRatingInCanvas.innerHTML = '';

        if (selectedFilmId > 0) {
            fetch(`/api/main/selectedFilmInfo?id=${selectedFilmId}`)
                .then(response => {
                    return response.json();
                }).then(json => {
                colFilmNameInCanvas.innerText = '';
                colFilmNameInCanvas.innerText = json['filmDto'].filmName;
                colFilmRatingInCanvas.innerText = json['filmDto'].filmRating + '세 이상 관람가';
            });
        } else {
            colFilmNameInCanvas.innerText = '';
            colFilmRatingInCanvas.innerText = '';
        }

        const colTheaterNameInCanvas = document.getElementById("colTheaterNameInCanvas");
        // colTheaterNameInCanvas.innerHTML = '';
        if (selectedTheaterId > 0) {
            fetch(`/api/main/selectedTheaterInfo?id=${selectedTheaterId}`)
                .then(response => {
                    return response.json()
                }).then(json => {
                colTheaterNameInCanvas.innerText = '';
                colTheaterNameInCanvas.innerText = json['theaterDto'].name;
            });
        } else {
            colTheaterNameInCanvas.innerText = '';
        }

        const colDateInCanvas = document.getElementById("colDateInCanvas");
        // colDateInCanvas.innerHTML = '';
        if (selectedDate !== '') {
            colDateInCanvas.innerText = '';
            colDateInCanvas.innerText = selectedDate.substring(5);
        } else {
            colDateInCanvas.innerText = '';
        }

        const colFilmTypeInCanvas = document.getElementById("colFilmTypeInCanvas");
        const colBoxNameInCanvas = document.getElementById("colBoxNameInCanvas");
        const colTimeInCanvas = document.getElementById("colTimeInCanvas");

        // selectedFilmPlayingTableId = 0

        // if (selectedTheaterId > 0 && selectedFilmId > 0 && selectedDate !== '' && selectedFilmPlayingTableId > 0) {
        if (selectedFilmPlayingTableId > 0) {
            fetch(`/api/main/selectedBoxAndTypeInfo?id=${selectedFilmPlayingTableId}`)
                .then(response => response.json())
                .then(json => {
                    colFilmTypeInCanvas.innerText = '';
                    colFilmTypeInCanvas.innerText = json['typeDto']['typeName'];

                    colBoxNameInCanvas.innerText = '';
                    colBoxNameInCanvas.innerText = json['boxDto']['name'];

                    colTimeInCanvas.innerText = ''
                    colTimeInCanvas.innerText = json['filmPlayingTableDto']['time'].substring(0, 5);
                });

        } else {
            colFilmTypeInCanvas.innerText = '';
            colBoxNameInCanvas.innerText = '';
            colTimeInCanvas.innerText = ''
        }


        bsOffcanvas?.show();
    }
};

const renderPage = function (requestUrl) {
    fetch(requestUrl)
        .then(response => response.json())
        .then(json => {
            // console.log("응답 데이터:", json);

            renderFilm(json);
            renderTheater(json);
            renderDate(json);

            // if (selectedTheaterId > 0 && selectedFilmId > 0 && selectedDate !== '') {
            //     renderTimeTable();
            // } else {
            renderTimeTable();
            // }
        });
}

const renderTimeTable = function () {
    const BASE_URL = `/api/main/playingTable`;
    const params = makeUrl();
    const requestUrl = `${BASE_URL}?${params.toString()}`;

    fetch(requestUrl).then(response => {
        return response.json();
    }).then(json => {
        // console.log(json);

        const colPlayingTimeTableBox = document.getElementById("colPlayingTimeTableBox");
        colPlayingTimeTableBox.innerHTML = '';

        for (let filmPlayingTable of json) {
            const rowEachPlayingTimeTableBox = document.querySelector("#template .rowEachPlayingTimeTableBox").cloneNode(true);


            const colBoxTypeAndName = rowEachPlayingTimeTableBox.querySelector(".colBoxTypeAndName");
            colBoxTypeAndName.innerHTML = '';


            const rowBoxTypeAndName = document.querySelector("#template .rowBoxTypeAndName").cloneNode(true);

            const colTypeName = rowBoxTypeAndName.querySelector(".colTypeName");
            colTypeName.innerHTML = '';
            colTypeName.innerText = filmPlayingTable['typeDto']['typeName'];

            const colBoxName = rowBoxTypeAndName.querySelector(".colBoxName");
            colBoxName.innerHTML = '';
            colBoxName.innerText = filmPlayingTable['boxDto']['name'];

            const colCapacity = rowBoxTypeAndName.querySelector(".colCapacity");
            colCapacity.innerHTML = '';
            colCapacity.innerText = filmPlayingTable['boxDto']['capacity'];
            const capacity = filmPlayingTable['boxDto']['capacity'];

            colBoxTypeAndName.appendChild(rowBoxTypeAndName);


            const colTimeBox = rowEachPlayingTimeTableBox.querySelector(".colTimeBox");
            colTimeBox.innerHTML = '';

            let rowTimeBox = null; // 루프 외부에서 선언하고 초기화

            for (let i = 0; i < filmPlayingTable['timeAndSeatCountList'].length; i++) {
                const timeAndSeat = filmPlayingTable['timeAndSeatCountList'][i];
                const currentCount = i % 3; // 0, 1, 2, 0, 1, 2 ...

                // 새 rowTimeBox를 생성해야 할 때 (매 3번째 항목마다, 즉 0, 3, 6번째 인덱스)
                if (currentCount === 0) {
                    // 이전 rowTimeBox가 있다면 (첫 번째 반복이 아닐 때)
                    // 그리고 이전 rowTimeBox가 실제로 존재한다면 (null이 아니라면)
                    // 이전에 채웠던 rowTimeBox를 부모에 추가해야 합니다.
                    if (rowTimeBox !== null) {
                        colTimeBox.appendChild(rowTimeBox);
                    }
                    // 새로운 rowTimeBox 생성
                    rowTimeBox = document.querySelector("#template .rowTimeBox").cloneNode(true);
                    // 새로운 rowTimeBox를 생성한 직후이므로, 아직 부모에 추가하지 않습니다.
                    // 다음 3개 항목을 채운 후에 추가할 것입니다.
                }

                const colTimeCap = rowTimeBox.querySelector(`.colTimeCap_${currentCount + 1}`);
                colTimeCap.id = `${timeAndSeat['filmPlayingTableDto']['id']}`;

                // console.log(selectedFilmPlayingTableId);
                toggleSelectionStyle(colTimeCap, selectedFilmPlayingTableId === timeAndSeat['filmPlayingTableDto']['id']);

                colTimeCap.onclick = () => {
                    // colTimeCap.classList.add('selected-item');

                    selectFilmPlayingTable(timeAndSeat['filmPlayingTableDto']['id']);
                };

                // 현재 항목에 해당하는 colTime 요소를 찾습니다.
                // currentCount는 0, 1, 2로 변하므로, colTime_1, colTime_2, colTime_3에 대응
                // +1을 해주어 1부터 시작하는 CSS 클래스 이름에 맞춥니다.
                const colTime = rowTimeBox.querySelector(`.colTime_${currentCount + 1}`);

                // null 체크: 템플릿 구조가 정확한지 확인
                // if (!colTime) {
                //     console.error(`Error: colTime_${currentCount + 1} not found in rowTimeBox.`);
                //     continue; // 다음 반복으로 건너뛰어 다른 오류 방지
                // }

                colTime.innerHTML = ''; // 기존 내용 초기화 (안전하게)
                // colTime.innerText = `${timeAndSeat['time']}`;
                const timeString = `${timeAndSeat['timeDto']['time']}`
                colTime.innerText = timeString.substring(0, 5);
                // colTime.innerText = timeString` ${colCapacity.innerText - timeAndSeat['bookedSeatCount']}석`;

                const colCapacity = rowTimeBox.querySelector(`.colCapacity_${currentCount + 1}`);
                colCapacity.innerHTML = '';
                colCapacity.innerText = `${capacity - timeAndSeat['bookedSeatCount']}석`;

                // if (capacity - timeAndSeat['bookedSeatCount'] > 0) {
                //     colCapacity.innerText = `${capacity - timeAndSeat['bookedSeatCount']}석`;
                //     colTimeCap.onclick = () => {
                //         // colTimeCap.classList.add('selected-item');
                //
                //         selectFilmPlayingTable(timeAndSeat['filmPlayingTableDto']['id']);
                //     };
                // } else{
                //     colCapacity.innerText = `매진`;
                // }


                // 마지막 항목이거나, 현재 rowTimeBox의 마지막 항목일 때 부모에 추가
                // (루프가 끝나기 전에 마지막 rowTimeBox가 추가되도록)
                if (i === filmPlayingTable['timeAndSeatCountList'].length - 1) {
                    colTimeBox.appendChild(rowTimeBox);
                }
            }

            // let count = 0;
            // for (let timeAndSeatCount of filmPlayingTable['timeAndSeatCountList']) {
            //     let rowTimeBox;
            //
            //     if (count === 3) {
            //         colTimeBox.appendChild(rowTimeBox);
            //     }
            //     if (count % 3 === 0) {
            //         rowTimeBox = document.querySelector("#template .rowTimeBox").cloneNode(true);
            //         count = 0;
            //     }
            //
            //     count++;
            //     const colTime = rowTimeBox.querySelector(`.colTime_${count}`);
            //     colTime.innerHTML = '';
            //     colTime.innerText = timeAndSeatCount['time'] + ' ' + colCapacity.innerText - timeAndSeatCount['bookedSeatCount'] + '석';
            //
            // }

            colPlayingTimeTableBox.appendChild(rowEachPlayingTimeTableBox);
        }
    });

};


const renderFilm = function (json) {
    // 영화
    const colFilmListBox = document.getElementById("colFilmListBox");
    colFilmListBox.innerHTML = '';

    // let filmDto = json['filmDtoList'];
    for (let filmDto of json['filmDtoList']) {
        const rowEachFilm = document.querySelector("#template .rowEachFilm").cloneNode(true);

        const colFilmName = rowEachFilm.querySelector(".colFilmName");
        colFilmName.innerText = '[' + filmDto['filmRating'] + ']' + ' ' + filmDto['filmName'];
        colFilmName.id = `filmId_${filmDto['id']}`;

        toggleSelectionStyle(colFilmName, selectedFilmId === filmDto['id']);

        colFilmName.onclick = () => {
            selectFilm(filmDto['id']);
        };

        colFilmListBox.appendChild(rowEachFilm);
    }

    for (let filmDtoWhite of json['filmDtoWhiteList']) {
        const rowExcludeFilm = document.querySelector("#template .rowExcludeFilm").cloneNode(true);

        const colExcludeFilmName = rowExcludeFilm.querySelector(".colExcludeFilmName");
        colExcludeFilmName.innerText = '[' + filmDtoWhite['filmRating'] + ']' + ' ' + filmDtoWhite['filmName'];
        colExcludeFilmName.id = `filmId_${filmDtoWhite['id']}`;

        colExcludeFilmName.onclick = () => {
            if (confirm("선택한 영화에 원하시는 상영스케줄이 없습니다. " +
                "계속하시겠습니까? (선택한 날짜 및 극장이 해제됩니다.)")) {
                // confirm : 버튼이 두개인데 확인하면 true, 취소하면 false

                selectedTheaterId = 0;
                selectedDate = '';
                selectFilm(filmDtoWhite['id']);
                // js 내장객체 location
            }
        };

        colFilmListBox.appendChild(rowExcludeFilm);
    }
}

const renderTheater = function (json) {
    // 극장
    const colTheaterListBox = document.getElementById("colTheaterListBox");
    colTheaterListBox.innerHTML = '';

    // let theaterDto = json['theaterDtoList'];


    for (let theaterDto of json['theaterDtoList']) {
        const rowEachTheater = document.querySelector("#template .rowEachTheater").cloneNode(true);

        const colTheaterName = rowEachTheater.querySelector(".colTheaterName");
        colTheaterName.innerText = theaterDto['name'];
        colTheaterName.id = `theaterId_${theaterDto['id']}`;

        toggleSelectionStyle(colTheaterName, selectedTheaterId === theaterDto['id']);

        colTheaterName.onclick = () => {
            selectTheater(theaterDto['id']);
        };

        colTheaterListBox.appendChild(rowEachTheater);
    }

    for (let theaterDtoWhite of json['theaterDtoWhiteList']) {
        const rowExcludeTheater = document.querySelector("#template .rowExcludeTheater").cloneNode(true);

        const colExcludeTheaterName = rowExcludeTheater.querySelector(".colExcludeTheaterName");
        colExcludeTheaterName.innerText = theaterDtoWhite['name'];
        colExcludeTheaterName.id = `theaterId_${theaterDtoWhite['id']}`;

        colExcludeTheaterName.onclick = () => {
            if (confirm("선택한 극장에 원하시는 상영스케줄이 없습니다. " +
                "계속하시겠습니까? (선택한 영화 및 날짜이 해제됩니다.)")) {
                // confirm : 버튼이 두개인데 확인하면 true, 취소하면 false

                selectedFilmId = 0;
                selectedDate = '';
                selectTheater(theaterDtoWhite['id']);
                // js 내장객체 location
            }
        };

        colTheaterListBox.appendChild(rowExcludeTheater);
    }
}

const renderDate = function (json) {
    // 날짜
    const colDateListBox = document.getElementById("colDateListBox");
    colDateListBox.innerHTML = '';

    json['dateDtoWhiteList'].forEach(dateDtoWhite => {

        const rowEachDate = document.querySelector("#template .rowEachDate").cloneNode(true);
        const colDate = rowEachDate.querySelector(".colDate");

        const date = new Date(dateDtoWhite['date']);

        const parts = dateDtoWhite['date'].split('-');
        const year = parseInt(parts[0]);
        const month = parseInt(parts[1]) - 1; // 월은 0부터 시작
        const day = parseInt(parts[2]);
        const localDate = new Date(year, month, day);

        const weekdays = ['일', '월', '화', '수', '목', '금', '토'];
        const monthFormatted = String(localDate.getMonth() + 1).padStart(2, '0'); // getMonth()는 0부터 시작
        const dayFormatted = String(localDate.getDate()).padStart(2, '0');
        const dayOfWeek = weekdays[localDate.getDay()]; // getDay()는 0(일요일)부터 6(토요일)까지 반환

        colDate.innerText = `${dayFormatted} (${dayOfWeek})`;
        colDate.id = `dateId_${dateDtoWhite['date']}`;

        // some()을 사용하여 a 배열에 bDateDto와 같은 date를 가진 요소가 있는지 확인
        const isInDateDto = json['dateDtoList'].some(dateDto => dateDto.date === dateDtoWhite.date);

        if (dayOfWeek === '토') {
            colDate.classList.add('sat-color');
        } else if (dayOfWeek === '일') {
            colDate.classList.add('sun-color');
        }


        if (isInDateDto) {
            // console.log(`dateDtoList에 존재`);
            colDate.onclick = () => {
                selectDate(dateDtoWhite['date']);
            };
            toggleSelectionStyle(colDate, selectedDate === dateDtoWhite['date']);

        } else {
            colDate.style.color = "rgb(180,180,180)";
            // console.log(`dateDtoList에 미존재`);

            colDate.onclick = () => {
                if (confirm("선택한 날짜에 원하시는 상영스케줄이 없습니다. " +
                    "계속하시겠습니까? (선택한 영화 및 극장이 해제됩니다.)")) {
                    // confirm : 버튼이 두개인데 확인하면 true, 취소하면 false

                    selectedFilmId = 0;
                    selectedTheaterId = 0;
                    selectDate(dateDtoWhite['date']);
                    // js 내장객체 location
                }
            };
        }

        colDateListBox.appendChild(rowEachDate);

    });
}

const toggleSelectionStyle = function (element, isSelected) {
    if (element) { // 요소가 존재하는지 확인
        if (isSelected) {
            element.classList.remove('sat-color');
            element.classList.remove('sun-color');
            element.classList.add('selected-item');
        } else {
            element.classList.remove('selected-item');
        }
    }
};

const makeConfirm = function () {
    const selectConfirm = document.getElementById("selectConfirm");

    selectConfirm.onclick = () => {

        if (selectedFilmId > 0 && selectedTheaterId > 0 && selectedDate !== '' && selectedFilmPlayingTableId > 0) {
            if (memberDto === null) {
                if (confirm("로그인이 필요한 서비스입니다. 로그인 페이지로 이동하시겠습니까?")) {
                    location.href = `/member/loginPage`;
                }
            } else {
                location.href = `/main/makeConfirm?id=${selectedFilmPlayingTableId}`;
            }
        }

    };
}
