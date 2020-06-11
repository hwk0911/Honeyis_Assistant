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
        var my_tbody = document.getElementById('inputForm');
        var row = my_tbody.insertRow(my_tbody.rows.length); // 하단에 추가
        var cell1 = row.insertCell(0);:w
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);
        cell1.innerHTML = '<td class="form-group">' + '<input type="text" id="client" class="form-control" placeholder="client">' + '</td>';
        cell2.innerHTML = '<td class="form-group">' + '<input type="text" id="productName" class="form-control" placeholder="productName">' + '</td>';
        cell3.innerHTML = '<td class="form-group">' + '<input type="text" id="color" class="form-control" placeholder="color">' + '</td>';
        cell4.innerHTML = '<td class="form-group">' + '<input type="text" id="size" class="form-control" placeholder="size">' + '</td>';
        cell5.innerHTML = '<td class="form-group">' + '<input type="text" id="amount" class="form-control" placeholder="amount">' + '</td>';
    },

    removeInput: function () {
        var my_tbody = document.getElementById('inputForm');
        if (my_tbody.rows.length < 1) return;
        // my_tbody.deleteRow(0); // 상단부터 삭제
        my_tbody.deleteRow(my_tbody.rows.length - 1); // 하단부터 삭제
    }
};

addForm.init();