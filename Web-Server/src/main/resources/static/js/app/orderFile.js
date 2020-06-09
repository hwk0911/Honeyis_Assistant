$('.order')
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

    var formData = new FormData();

    for (var index = 0, size = files.length; index < size; ++index) {
        formData.append("files", files[index]);
    }

    $.ajax({
        type: "POST",
        url: "/api/v1/sendfilesup",
        data: formData,
        processData: false,
        contentType: false
    }).done (function (e) {
        window.location.href = "/users/orderList";
    });


}