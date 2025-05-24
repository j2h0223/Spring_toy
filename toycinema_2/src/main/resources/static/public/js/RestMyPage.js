// let memberDtoJson;
window.addEventListener("DOMContentLoaded", () => {
    // memberDtoJson = JSON.parse('[[${memberDtoJson}]]');
    basicRendering();
    contentsRendering();

    reservationCheck();
    reviewedCheck();
    alreadyViewCheck();
    likeCheck();

    reservationCheckClickAction(); // ✅ 여기가 핵심! 기본으로 예매중인 영화 보기 실행

    reservationCheckClickAction(); // ✅ 기본 첫 화면 설정
    alreadyViewCheckClickAction();
    reviewedCheckClickAction();

    cancelReservation();
    modalComment();
    makeStar();
    writeCommentAction();
});


const basicRendering = function () {
    const memberNickName = document.getElementById("memberNickName");
    memberNickName.innerHTML = '';
    memberNickName.innerText = memberDto.nickname;
};

const contentsRendering = function () {

};

const reservationCheck = function () {
    const memberReservation = document.getElementById("memberReservation");
    memberReservation.onclick = () => {
        reservationCheckClickAction();
    };
};

const alreadyViewCheck = function () {
    const memberAlreadyView = document.getElementById("memberAlreadyView");
    memberAlreadyView.onclick = () => {
        alreadyViewCheckClickAction();
    };
};


const reviewedCheck = function () {
    const memberReviewed = document.getElementById("memberReviewed");
    memberReviewed.onclick = () => {
        reviewedCheckClickAction();
    };
};

const likeCheck = function () {
    const memberLike = document.getElementById("memberLike");
    memberLike.onclick = () => {
        reservationCheckClickAction();
    };
};

const gotoFilm = function (id) {
    location.href = `/film/filmDetailsPage/${id}`;

};

const reservationCheckClickAction = function (string) {
    const colContentsName = document.getElementById("colContentsName");
    colContentsName.innerHTML = '';

    fetch(`/member/reservationCheck?id=${memberDto.id}`)
        .then(response => response.json())
        .then(json => {
            const colContentsName = document.getElementById("colContentsName");
            colContentsName.innerHTML = '';
            colContentsName.innerText = '예매 중인 영화 : ' + `${json.length}건`;

            const memberReservationCount = document.getElementById("memberReservationCount");
            memberReservationCount.innerHTML = '';
            memberReservationCount.innerText = `${json.length}`;


            const colContents = document.getElementById("colContents");
            colContents.innerHTML = '';
            if (json.length === 0) {
                colContents.innerText = '예매 중인 영화가 없습니다'
                return;
            }

            for (let info of json) {

                const rowReservationAndAlready = document.querySelector("#template .rowReservationAndAlready").cloneNode(true);

                const filmImg = rowReservationAndAlready.querySelector(".filmImg");
                filmImg.src = `/public/img/poster/${info['filmDto']['filmName']}.webp`;
                filmImg.onclick = () => {
                    gotoFilm(info['filmDto']['id']);
                };

                const colId = rowReservationAndAlready.querySelector(".colId");
                colId.innerHTML = '';
                colId.innerText = '예매번호 : ' + info['filmReservationDto']['id'];

                const colNameAndType = rowReservationAndAlready.querySelector(".colNameAndType");
                colNameAndType.innerHTML = '';
                colNameAndType.innerText = info['filmDto']['filmName'] + ' (' + info['typeDto']['typeName'] + ')';

                const colOriginalNameAndType = rowReservationAndAlready.querySelector(".colOriginalNameAndType");
                colOriginalNameAndType.innerHTML = '';
                colOriginalNameAndType.innerText = info['filmDto']['originalName'];

                const colDataAndTime = rowReservationAndAlready.querySelector(".colDataAndTime");
                colDataAndTime.innerHTML = '';

                const parts = info['filmPlayingTableDto']['date'].split('-');
                const year = parseInt(parts[0]);
                const month = parseInt(parts[1]) - 1; // 월은 0부터 시작
                const day = parseInt(parts[2]);
                const localDate = new Date(year, month, day);

                const weekdays = ['일', '월', '화', '수', '목', '금', '토'];
                const monthFormatted = String(localDate.getMonth() + 1).padStart(2, '0'); // getMonth()는 0부터 시작
                const dayFormatted = String(localDate.getDate()).padStart(2, '0');
                const dayOfWeek = weekdays[localDate.getDay()];

                // const time = info['filmPlayingTableDto']['time'].split(5);

                const timeString = `${info['filmPlayingTableDto']['time']}`
                const time = timeString.substring(0, 5);


                const [hours, minutes] = time.split(':').map(Number);
                const date = new Date(); // 현재 날짜와 시간으로 Date 객체 생성
                date.setHours(hours);    // 시간을 12로 설정
                date.setMinutes(minutes); // 분을 00으로 설정
                date.setSeconds(0);      // 초를 0으로 설정 (정확성을 위해)
                // date.setMilliseconds(0); // 밀리초를 0으로 설정

                date.setMinutes(date.getMinutes() + info['filmDto']['runningTime']);
                const resultHours = date.getHours();
                const resultMinutes = date.getMinutes();

                const formattedHours = String(resultHours).padStart(2, '0');
                const formattedMinutes = String(resultMinutes).padStart(2, '0');

                // date_time.innerText = `${year}.${month}.${day} (${dayOfWeek}) ${time}`;
                colDataAndTime.innerText = `${year}.${month + 1}.${day}(${dayOfWeek})  ${time} ~ ${formattedHours}:${formattedMinutes}`;


                const colTheaterAndBoxAndSeatCount = rowReservationAndAlready.querySelector(".colTheaterAndBoxAndSeatCount");
                colTheaterAndBoxAndSeatCount.innerHTML = '';
                colTheaterAndBoxAndSeatCount.innerText = info['theaterDto'].name + ' ' + info['boxDto']['name'] + ' (' + info['boxDto']['location'] + ') / ' + info['filmReservationDto']['count'] + '명';

                const colReview = rowReservationAndAlready.querySelector(".colReview");
                colReview.innerHTML = ''
                colReview.classList.remove('mt-1');

                const rowForCancel = rowReservationAndAlready.querySelector(".rowForCancel");

                const rowReservationCancel = rowReservationAndAlready.querySelector(".rowReservationCancel");
                rowReservationCancel.onclick = () => {
                    if (confirm("선택한 영화를 예매 취소하시겠습니까?")) {
                        console.log(info['filmReservationDto']['id']);
                        cancelReservation(info['filmReservationDto']['id']);
                    }

                };

                rowForCancel.appendChild(rowReservationCancel);
                colContentsName.appendChild(rowReservationAndAlready);
            }
        });
};

const cancelReservation = function (id) {
    fetch(`/member/cancelReservation?id=${id}`)
        .then(response => {
            return response.json();
        }).then(json => {
        reservationCheckClickAction();
        console.log(json);
    });

};

const alreadyViewCheckClickAction = function () {

    const colContentsName = document.getElementById("colContentsName");
    colContentsName.innerHTML = '';

    fetch(`/member/alreadyViewCheck?id=${memberDto.id}`)
        .then(response => response.json())
        .then(json => {
            const colContentsName = document.getElementById("colContentsName");
            colContentsName.innerHTML = '';
            colContentsName.innerText = '내가 본 영화 : ' + `${json.length}건`;

            const memberAlreadyViewCount = document.getElementById("memberAlreadyViewCount");
            memberAlreadyViewCount.innerHTML = '';
            memberAlreadyViewCount.innerText = `${json.length}`;


            const colContents = document.getElementById("colContents");
            colContents.innerHTML = '';
            if (json.length === 0) {
                colContents.innerText = '관람하신 영화가 없습니다'
                return;
            }

            for (let info of json) {

                const rowReservationAndAlready = document.querySelector("#template .rowReservationAndAlready").cloneNode(true);

                const filmImg = rowReservationAndAlready.querySelector(".filmImg");
                filmImg.src = `/public/img/poster/${info['filmDto']['filmName']}.webp`;
                filmImg.onclick = () => {
                    gotoFilm(info['filmDto']['id']);
                };
                const colId = rowReservationAndAlready.querySelector(".colId");
                colId.innerHTML = '';
                colId.innerText = '예매번호 : ' + info['filmReservationDto']['id'];

                const colNameAndType = rowReservationAndAlready.querySelector(".colNameAndType");
                colNameAndType.innerHTML = '';
                colNameAndType.innerText = info['filmDto']['filmName'] + ' (' + info['typeDto']['typeName'] + ')';

                const colOriginalNameAndType = rowReservationAndAlready.querySelector(".colOriginalNameAndType");
                colOriginalNameAndType.innerHTML = '';
                colOriginalNameAndType.innerText = info['filmDto']['originalName'];

                const colDataAndTime = rowReservationAndAlready.querySelector(".colDataAndTime");
                colDataAndTime.innerHTML = '';

                const parts = info['filmPlayingTableDto']['date'].split('-');
                const year = parseInt(parts[0]);
                const month = parseInt(parts[1]) - 1; // 월은 0부터 시작
                const day = parseInt(parts[2]);
                const localDate = new Date(year, month, day);

                const weekdays = ['일', '월', '화', '수', '목', '금', '토'];
                const monthFormatted = String(localDate.getMonth() + 1).padStart(2, '0'); // getMonth()는 0부터 시작
                const dayFormatted = String(localDate.getDate()).padStart(2, '0');
                const dayOfWeek = weekdays[localDate.getDay()]; // getDay()는 0(일요일)부터 6(토요일)까지 반환


                // const time = info['filmPlayingTableDto']['time'].split(5);

                const timeString = `${info['filmPlayingTableDto']['time']}`
                const time = timeString.substring(0, 5);


                const [hours, minutes] = time.split(':').map(Number);
                const date = new Date(); // 현재 날짜와 시간으로 Date 객체 생성
                date.setHours(hours);    // 시간을 12로 설정
                date.setMinutes(minutes); // 분을 00으로 설정
                date.setSeconds(0);      // 초를 0으로 설정 (정확성을 위해)
                // date.setMilliseconds(0); // 밀리초를 0으로 설정

                date.setMinutes(date.getMinutes() + info['filmDto']['runningTime']);
                const resultHours = date.getHours();
                const resultMinutes = date.getMinutes();

                const formattedHours = String(resultHours).padStart(2, '0');
                const formattedMinutes = String(resultMinutes).padStart(2, '0');

                // date_time.innerText = `${year}.${month}.${day} (${dayOfWeek}) ${time}`;
                colDataAndTime.innerText = `${year}.${month + 1}.${day}(${dayOfWeek})  ${time} ~ ${formattedHours}:${formattedMinutes}`;


                const colTheaterAndBoxAndSeatCount = rowReservationAndAlready.querySelector(".colTheaterAndBoxAndSeatCount");
                colTheaterAndBoxAndSeatCount.innerHTML = '';
                colTheaterAndBoxAndSeatCount.innerText = info['theaterDto'].name + ' ' + info['boxDto']['name'] + ' (' + info['boxDto']['location'] + ') / ' + info['filmReservationDto']['count'] + '명';

                const colReview = rowReservationAndAlready.querySelector(".colReview");
                colReview.innerHTML = ''
                if (info['memberCommentsFilmDto'] !== null) {
                    colReview.innerText = info['memberCommentsFilmDto']['text'] + ' (' + info['memberCommentsFilmDto']['point'] + '점)';
                } else {
                    colReview.innerText = '이 영화를 평가해주세요';
                }
                colReview.onclick = () => {
                    writeCommentModal(info);
                };

                const rowReservationCancel = rowReservationAndAlready.querySelector(".rowReservationCancel");
                rowReservationCancel.innerHTML = '';
                colContentsName.appendChild(rowReservationAndAlready);
            }
        });
};


const writeCommentModal = function (info) {
    // const paymentModalElement = document.getElementById("paymentModal");
    const commentModalDom = document.getElementById("commentModal");
    commentModalDom.dataset.infoJson = JSON.stringify(info);
    modalComment(commentModalDom);

    const commentModal = bootstrap.Modal.getOrCreateInstance("#commentModal");
    commentModal.show();
}


const modalComment = function (commentModalDom) {
    const infoJson = commentModalDom.dataset.infoJson;
    const info = JSON.parse(infoJson); // JSON 문자열을 다시 객체로 파싱

    console.log(info);
    const colFilmNameInModal = document.getElementById("colFilmNameInModal");
    const colFilmRatingInModal = document.getElementById("colFilmRatingInModal");
    //
    colFilmNameInModal.innerText = '';
    colFilmNameInModal.innerText = info['filmDto'].filmName;
    colFilmRatingInModal.innerText = '';
    colFilmRatingInModal.innerText = info['filmDto'].filmRating + '세 이상 관람가';
    //
    const colTheaterNameInModal = document.getElementById("colTheaterNameInModal");
    colTheaterNameInModal.innerText = '';
    colTheaterNameInModal.innerText = info['theaterDto'].name;
    //
    const colDateInModal = document.getElementById("colDateInModal");
    colDateInModal.innerText = '';
    colDateInModal.innerText = info['filmPlayingTableDto']['date'].substring(5);
    //
    const colFilmTypeInModal = document.getElementById("colFilmTypeInModal");
    // const colBoxNameInModal = document.getElementById("colBoxNameInModal");
    const colTimeInModal = document.getElementById("colTimeInModal");
    //
    colFilmTypeInModal.innerText = '';
    colFilmTypeInModal.innerText = info['typeDto']['typeName'];
    //

    // const colBoxNameInModal = document.getElementById("colBoxNameInModal");
    // colBoxNameInModal.innerHTML = '';
    // colBoxNameInModal.innerText = info['boxDto']['name'] + ' (' + info['boxDto']['location'] + ')';


    const parts = info['filmPlayingTableDto']['date'].split('-');
    const year = parseInt(parts[0]);
    const month = parseInt(parts[1]); // - 1; // 월은 0부터 시작
    const day = parseInt(parts[2]);
    const localDate = new Date(year, month, day);

    const weekdays = ['일', '월', '화', '수', '목', '금', '토'];
    const monthFormatted = String(localDate.getMonth() + 1).padStart(2, '0'); // getMonth()는 0부터 시작
    const dayFormatted = String(localDate.getDate()).padStart(2, '0');
    const dayOfWeek = weekdays[localDate.getDay()]; // getDay()는 0(일요일)부터 6(토요일)까지 반환


    // const time = json['filmPlayingTableDto']['time'].split(5);

    const timeString = `${info['filmPlayingTableDto']['time']}`
    const time = timeString.substring(0, 5);


    const [hours, minutes] = time.split(':').map(Number);
    const date = new Date(); // 현재 날짜와 시간으로 Date 객체 생성
    date.setHours(hours);    // 시간을 12로 설정
    date.setMinutes(minutes); // 분을 00으로 설정
    date.setSeconds(0);      // 초를 0으로 설정 (정확성을 위해)
    // date.setMilliseconds(0); // 밀리초를 0으로 설정

    date.setMinutes(date.getMinutes() + info['filmDto']['runningTime']);
    const resultHours = date.getHours();
    const resultMinutes = date.getMinutes();

    const formattedHours = String(resultHours).padStart(2, '0');
    const formattedMinutes = String(resultMinutes).padStart(2, '0');

    // date_time.innerText = `${year}.${month}.${day} (${dayOfWeek}) ${time}`;
    colTimeInModal.innerText = `${time} ~ ${formattedHours}:${formattedMinutes}`;

    const colTheaterAndBoxAndSeatCountInModal = document.getElementById("colTheaterAndBoxAndSeatCountInModal");
    colTheaterAndBoxAndSeatCountInModal.innerHTML = '';
    colTheaterAndBoxAndSeatCountInModal.innerText = info['theaterDto'].name + ' ' + info['boxDto']['name'] + ' (' + info['boxDto']['location'] + ') / ' + info['filmReservationDto']['count'] + '명';

    const payConfirm = document.getElementById("payConfirm");

    const commentInput = document.getElementById("commentInput");
    if (info['memberCommentsFilmDto'] !== null) {
        makeStar(info['memberCommentsFilmDto']['point']);
        commentInput.value = info['memberCommentsFilmDto']['text'];
        payConfirm.dataset.commentId = info['memberCommentsFilmDto']['id'];
    } else {
        commentInput.value = '';
    }

    payConfirm.dataset.filmId = info['filmDto']['id'];
    payConfirm.onclick = () => {
        payConfirm.dataset.text = commentInput.value;
        writeCommentAction(payConfirm);
    };
}

const writeCommentAction = function (dom) {
    // console.log(memberDto.id);
    // console.log(dom.dataset.text);
    // console.log(dom.dataset.score);
    // console.log(dom.dataset.filmId);

    const commentsDto = {};
    // commentsDto['memberId'] = memberDto.id;
    commentsDto['filmId'] = dom.dataset.filmId;
    commentsDto['text'] = dom.dataset.text;
    commentsDto['point'] = dom.dataset.score;

    let request;

    if (dom.dataset.commentId !== undefined) {
        console.log(dom.dataset.commentId);
        commentsDto['id'] = dom.dataset.commentId;
        request = 'updateComment'

    } else {
        commentsDto['id'] = 0;
        request = 'writeComment'
    }

    console.log(commentsDto);

    fetch(`/member/${request}`, {
        method: 'post',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(commentsDto)
    })
        .then(response => {
            return response.json();
        }).then(json => {
        console.log(json['result']);
    });

    const modal = bootstrap.Modal.getInstance(document.getElementById('commentModal'));
    modal.hide();
    alreadyViewCheckClickAction();
    makeStar();
    // location.reload();
};


let currentRating = 0;

const makeStar = function (clickedScore) {
    // console.log("=== makeStar 호출 시작 ===");
    // console.log("클릭된 점수 (clickedScore):", clickedScore);
    // console.log("초기 currentRating:", currentRating);

    const totalStars = 5;

    if (clickedScore === currentRating) {
        currentRating = 0;
        // console.log("같은 점수 클릭! currentRating 0으로 재설정:", currentRating);
    } else {
        currentRating = clickedScore;
        // console.log("다른 점수 클릭! currentRating 업데이트:", currentRating);
    }

    for (let i = 1; i <= totalStars; i++) {
        const star = document.getElementById(`star_${i}`);

        // if (!star) {
        //     console.warn(`ID 'star_${i}'를 가진 별 요소를 찾을 수 없습니다.`);
        //     continue;
        // }

        if (i <= currentRating) {
            star.classList.remove("bi-star");
            star.classList.add("bi-star-fill");
        } else {
            star.classList.remove("bi-star-fill");
            star.classList.add("bi-star");
        }
        // 각 별의 최종 클래스 상태 확인 (선택 사항)
        // console.log(`star_${i}의 최종 클래스:`, star.className);
    }

    // console.log(`최종 선택된 평점: ${currentRating}점`);
    // console.log("=== makeStar 호출 종료 ===");
    //
    const payConfirm = document.getElementById('payConfirm');
    // if (reviewRatingInput) {
    payConfirm.dataset.score = currentRating;
    //     console.log("reviewRatingInput 값 업데이트:", reviewRatingInput.value);
    // }
};


const reviewedCheckClickAction = function () {

    const colContentsName = document.getElementById("colContentsName");
    colContentsName.innerHTML = '';

    fetch(`/member/reviewedCheck?id=${memberDto.id}`)
        .then(response => response.json())
        .then(json => {


            const colContentsName = document.getElementById("colContentsName");
            colContentsName.innerHTML = '';

            const memberReviewedCount = document.getElementById("memberReviewedCount");
            memberReviewedCount.innerHTML = '';


            const colContents = document.getElementById("colContents");
            colContents.innerHTML = '';


            let count = 0;

            for (let info of json) {
                count++;
                console.log(info);
                const rowReservationAndAlready = document.querySelector("#template .rowReservationAndAlready").cloneNode(true);

                const colId = rowReservationAndAlready.querySelector(".colId");
                colId.innerHTML = '';
                colId.innerText = '예매번호 : ' + info['filmReservationDto']['id']

                const filmImg = rowReservationAndAlready.querySelector(".filmImg");
                filmImg.src = `/public/img/poster/${info['filmDto']['filmName']}.webp`;
                filmImg.onclick = () => {
                    gotoFilm(info['filmDto']['id']);
                };

                const colNameAndType = rowReservationAndAlready.querySelector(".colNameAndType");
                colNameAndType.innerHTML = '';
                colNameAndType.innerText = info['filmDto']['filmName'] + ' (' + info['typeDto']['typeName'] + ')';

                const colOriginalNameAndType = rowReservationAndAlready.querySelector(".colOriginalNameAndType");
                colOriginalNameAndType.innerHTML = '';
                colOriginalNameAndType.innerText = info['memberDto']['nickname'];

                const colDataAndTime = rowReservationAndAlready.querySelector(".colDataAndTime");
                colDataAndTime.innerHTML = '';

                const dateTimeString = info['memberCommentsFilmDto']['createdAt'];
                const date = new Date(dateTimeString);

                const year = date.getFullYear();
                const month = String(date.getMonth() + 1).padStart(2, '0'); // getMonth()는 0부터 시작
                const day = String(date.getDate()).padStart(2, '0');

                colDataAndTime.innerText = `${year}.${month}.${day}`;


                const colReview = rowReservationAndAlready.querySelector(".colReview");
                colReview.innerHTML = ''
                // if (info['memberCommentsFilmDto'] !== null) {
                    colReview.innerText = info['memberCommentsFilmDto']['text'] + ' (' + info['memberCommentsFilmDto']['point'] + '점)';
                // } else {
                //     colReview.innerText = '이 영화를 평가해주세요';
                // }
                colReview.onclick = () => {
                    writeCommentModal(info);
                };

                const rowReservationCancel = rowReservationAndAlready.querySelector(".rowReservationCancel");
                rowReservationCancel.innerHTML = '';
                colContents.appendChild(rowReservationAndAlready);
            }

            if (count === 0) {
                colContentsName.innerText = '내가 쓴 리뷰 : ' + `0건`;
                memberReviewedCount.innerText = `0`;
                colContents.innerText = '리뷰한 영화가 없습니다'
            }

            colContentsName.innerText = '내가 쓴 리뷰 : ' + `${count}건`;
            memberReviewedCount.innerText = `${count}`;

        });
};


