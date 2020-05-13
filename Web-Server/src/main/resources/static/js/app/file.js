$('.content')
    .on("dragover", dragOver)
    .on("dragleave", dragOver)
    .on("drop", uploadFiles);

function dragOver(e) {
    e.stopPropagation();
    e.preventDefault();
    if (e.type == "dragover") {
        $(e.target).css({
            "background-color": "black",
            "outline-offset": "-20px"
        });
    } else {
        $(e.target).css({
            "background-color": "gray",
            "outline-offset": "-10px"
        });
    }
}

function uploadFiles(e) {
    e.stopPropagation();
    e.preventDefault();
    dragOver(e); //1

    e.dataTransfer = e.originalEvent.dataTransfer; //2
    var files = e.target.files || e.dataTransfer.files;


    $.ajax({
        type: 'POST',
        url: '/api/v1/filesup',
        dataType: 'json',
        contentType: false,
        data: JSON.stringify(files)
    }).done(function () {
        alert('파일 전송이 완료되었습니다.');
    }).fail(function (error) {
        alert(error);
    });
}

/*
if (files[0].type.match(/image.*//*    )) {

}else{
    alert('이미지가 아닙니다.');
    return;
}
*/


