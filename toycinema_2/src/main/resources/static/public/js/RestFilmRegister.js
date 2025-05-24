window.addEventListener("DOMContentLoaded", () => {
    countFilmType = 0;
    countFilmGenre = 0;
    countFilmYoutube = 0;
    addFilmType();
    addFilmGenre();
    addFilmYoutube();
    submit();
});

let countFilmType;
let countFilmGenre;
let countFilmYoutube;

const addFilmType = function () {

    const colFilmTypeBox = document.getElementById("colFilmTypeBox");
    // colFilmTypeBox.innerHTML = '';

    if (countFilmType > 0) {
        const colFilmTypeInsertButton = document.getElementById(`colFilmTypeInsertButton_${countFilmType}`);
        colFilmTypeInsertButton.innerText = `삭제`;
        colFilmTypeInsertButton.onclick = () => {
            removeAdd(colFilmTypeInsertButton, 'rowEachFilmType');
        };
    }

    countFilmType++;

    const rowEachFilmType = document.querySelector("#template .rowEachFilmType").cloneNode(true);
    const colFilmTypeInput = rowEachFilmType.querySelector(".colFilmTypeInput");
    colFilmTypeInput.id = `colFilmTypeInput_${countFilmType}`;

    const colFilmTypeInsertButton = rowEachFilmType.querySelector(".colFilmTypeInsertButton");
    colFilmTypeInsertButton.id = `colFilmTypeInsertButton_${countFilmType}`;
    colFilmTypeInsertButton.innerText = `추가`
    colFilmTypeInsertButton.onclick = () => {
        addFilmType();
    };

    colFilmTypeBox.appendChild(rowEachFilmType);

};

const addFilmGenre = function () {

    const colFilmGenreBox = document.getElementById("colFilmGenreBox");
    // colFilmTypeBox.innerHTML = '';

    if (countFilmGenre > 0) {
        const colFilmGenreInsertButton = document.getElementById(`colFilmGenreInsertButton_${countFilmGenre}`);
        colFilmGenreInsertButton.innerText = `삭제`;
        colFilmGenreInsertButton.onclick = () => {
            removeAdd(colFilmGenreInsertButton, 'rowEachFilmGenre');
        };
    }

    countFilmGenre++;

    const rowEachFilmGenre = document.querySelector("#template .rowEachFilmGenre").cloneNode(true);
    const colFilmGenreInput = rowEachFilmGenre.querySelector(".colFilmGenreInput");
    colFilmGenreInput.id = `colFilmGenreInput_${countFilmGenre}`;

    const colFilmGenreInsertButton = rowEachFilmGenre.querySelector(".colFilmGenreInsertButton");
    colFilmGenreInsertButton.id = `colFilmGenreInsertButton_${countFilmGenre}`;
    colFilmGenreInsertButton.innerText = `추가`
    colFilmGenreInsertButton.onclick = () => {
        addFilmGenre();
    };

    colFilmGenreBox.appendChild(rowEachFilmGenre);

};

const addFilmYoutube = function () {

    const colFilmYoutubeBox = document.getElementById("colFilmYoutubeBox");
    // colFilmTypeBox.innerHTML = '';

    if (countFilmYoutube > 0) {
        const colFilmYoutubeInsertButton = document.getElementById(`colFilmYoutubeInsertButton_${countFilmYoutube}`);
        colFilmYoutubeInsertButton.innerText = `삭제`;
        colFilmYoutubeInsertButton.onclick = () => {
            removeAdd(colFilmYoutubeInsertButton, 'rowEachFilmYoutube');
        };
    }

    countFilmYoutube++;

    const rowEachFilmYoutube = document.querySelector("#template .rowEachFilmYoutube").cloneNode(true);
    const colFilmYoutubeInput = rowEachFilmYoutube.querySelector(".colFilmYoutubeInput");
    colFilmYoutubeInput.id = `colFilmYoutubeInput_${countFilmYoutube}`;

    const colFilmYoutubeInsertButton = rowEachFilmYoutube.querySelector(".colFilmYoutubeInsertButton");
    colFilmYoutubeInsertButton.id = `colFilmYoutubeInsertButton_${countFilmYoutube}`;
    colFilmYoutubeInsertButton.innerText = `추가`
    colFilmYoutubeInsertButton.onclick = () => {
        addFilmYoutube();
    };

    colFilmYoutubeBox.appendChild(rowEachFilmYoutube);

};

const removeAdd = function (dom, element) {
    const elementDom = dom.closest(`.${element}`);
    elementDom.classList.remove("my-1");
    elementDom.innerHTML = '';

};


const registerFilmBasicInfo = function () {
    const filmNameInput = document.getElementById("filmNameInput");
    const originalNameInput = document.getElementById("originalNameInput");
    const openingDayInput = document.getElementById("openingDayInput");
    const runningTimeInput = document.getElementById("runningTimeInput");
    const filmRatingInput = document.getElementById("filmRatingInput");
    const countryInput = document.getElementById("countryInput");
    const filmStoryInput = document.getElementById("filmStoryInput");

    const filmData = {
        filmName: filmNameInput.value,
        originalName: originalNameInput.value,
        openingDay: openingDayInput.value,
        runningTime: parseInt(runningTimeInput.value), // 숫자로 변환 필요 (input type="number"라도)
        filmRating: parseInt(filmRatingInput.value),
        country: countryInput.value,
        story: filmStoryInput.value
    };

    fetch(`/api/operator/registerBasicInfo`, {
        method: `post`,
        headers: {
            "Content-Type": "application/json"
            // "Content-Type": "application/x-www-form-urlencoded"
        },
        body: JSON.stringify(filmData)
    }).then(response => {
        return response.json();
    }).then(json => {
        console.log(json);
    });
}

const registerFilmType = function () {
    const filmTypeList = [];

    document.querySelectorAll("#colFilmTypeBox .rowEachFilmType")
        .forEach(child => {
            const value = child.querySelector('.filmTypeInput').value.trim() ;
            filmTypeList.push({"typeName" : value});
        });
    // console.log(filmTypeList);

    const filmTypeAndId = {};
    filmTypeAndId['filmTypeList'] = filmTypeList;
    filmTypeAndId['id'] = 1;

    fetch(`/api/operator/registerFilmType`, {
        method: `post`,
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(filmTypeAndId)
    }).then(response => {
        return response.json()
    }).then(json => {
        console.log(json['result']);
    });

};

const registerFilmGenre = function () {
    const filmGenreList = [];

    document.querySelectorAll("#colFilmGenreBox .rowEachFilmGenre")
        .forEach(child => {
            const value = child.querySelector('.filmGenreInput').value.trim() ;
            filmGenreList.push({"genreName" : value});
        });
    // console.log(filmGenreList);

    const filmGenreAndId = {};
    filmGenreAndId['filmGenreList'] = filmGenreList;
    filmGenreAndId['id'] = 1;

    fetch(`/api/operator/registerFilmGenre`, {
        method: `post`,
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(filmGenreAndId)
    }).then(response => {
        return response.json()
    }).then(json => {
        console.log(json['result']);
    });

};

const registerFilmYoutube = function () {
    const filmYoutubeList = [];

    document.querySelectorAll("#colFilmYoutubeBox .rowEachFilmYoutube")
        .forEach(child => {
            const value = child.querySelector('.filmYoutubeInput').value.trim() ;
            filmYoutubeList.push({"urlYoutube" : value});
        });
    // console.log(filmYoutubeList);

    const filmYoutubeAndId = {};
    filmYoutubeAndId['filmYoutubeList'] = filmYoutubeList;
    filmYoutubeAndId['id'] = 1;

    fetch(`/api/operator/registerFilmYoutube`, {
        method: `post`,
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(filmYoutubeAndId)
    }).then(response => {
        return response.json()
    }).then(json => {
        console.log(json['result']);
    });

};

const registerFilmPoster = function () {
    const imageUploadInput = document.getElementById("imageUploadInput");
    const files = imageUploadInput.files;

    if (files.length === 0) {
        return;
    }

    const formData = new FormData();
    for (const file of files) {
        formData.append("files", file); // 서버에서 List<MultipartFile>을 받을 키 이름
    }

    formData.append("id", 1);

    // 3. fetch를 사용하여 서버로 FormData 전송
    // FormData를 사용할 때는 'Content-Type' 헤더를 수동으로 설정하지 않습니다.
    // 브라우저가 자동으로 'multipart/form-data'와 경계(boundary)를 설정해줍니다.
    fetch(`/api/operator/registerFilmPoster`, {
        method: `post`,
        body: formData
    }).then(response => {
        return response.json()
    }).then(json => {
        console.log(json['result']);
    });
};

const submit = function () {
    const confirmButton = document.getElementById("confirmButton"); // 새로 추가된 ID

    confirmButton.onclick = () => {
        registerFilmBasicInfo();
        registerFilmType();
        registerFilmGenre();
        registerFilmYoutube();
        registerFilmPoster();
    };
};