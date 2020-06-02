var addForm = {
    init: function () {
        var _this = this;
        $('#btn-addInput').on('click', function () {
            _this.addInput();
        }),
        $('#btn-removeInput').on('click', function () {
            _this.removeInput();
        });
    },

    addInput: function () {
        alert('addForm');
        var addedFormDiv = document.getElementById("inputForm");

        var str = '<tr>';
        str += '<th class="form-group">' + '<input type="text" id="productName" class="form-control" placeholder="productName">'
        + '</th>';
        str += '<th class="form-group">' + '<input type="text" id="color" class="form-control" placeholder="color">'
        + '</th>';
        str += '<th class="form-group">' + '<input type="text" id="size" class="form-control" placeholder="size">'
        + '</th>';
        str += '<th class="form-group">' + '<input type="text" id="amount" class="form-control" placeholder="amount">'
        + '</th>';
        str += '</tr>';


        var count = 0;
        var addedDiv = document.createElement("div");
        addedDiv.setAttribute("id", "keyword_Frm" + count);
        addedDiv.innerHTML = str;
        addedFormDiv.appendChild(addedDiv);

        ++count;
    },

    removeInput: function () {
        alert('removeForm');
        document.getElementById('field').removeChild(obj.parentNode);
    }
};

addForm.init();