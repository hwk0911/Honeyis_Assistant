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

    var data = new FormData(files);

    alert('asdfadsfads');

    $.ajax({
        type: "POST",
            enctype: 'multipart/form-data',
            url: "/api/v1/filesup",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000
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


/*
        //preventDefault 는 기본으로 정의된 이벤트를 작동하지 못하게 하는 메서드이다. submit을 막음
        event.preventDefault();

        // Get form
        var form = $('#fileUploadForm')[0];

	    // Create an FormData object 
        var data = new FormData(form);

	   // disabled the submit button
        $("#btnSubmit").prop("disabled", true);

        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "/api/v1/filesup",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
            	alert("complete");
                $("#btnSubmit").prop("disabled", false);
            },
            error: function (e) {
                console.log("ERROR : ", e);
                $("#btnSubmit").prop("disabled", false);
                alert("fail");
            }
        });
*/