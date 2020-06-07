var addAmount = {
    init: function () {
        var _this = this;
        $(document).on('click','#btn-addAmount', function () {
            _this.addAmount();
        })
    },

    addAmount: function () {
        var ele = document.getElementsByName("id");

        alert(ele.eq(0).value);
        alert(ele.eq(1).value);
        alert(ele.eq(2).value);


        var data = {
            id: $('#id').val(),
            addAmount: $('#addAmount').val()
        }
        if(!data.addAmount) {
            alert("input amount");
        }
        else {
            $.ajax({
                type: 'POST',
                url: '/api/v1/addAmount',
                dataType: 'json',
                contentType: 'application/json; charset = utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                window.location.href = "/users/stock";
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    }
};

addAmount.init();