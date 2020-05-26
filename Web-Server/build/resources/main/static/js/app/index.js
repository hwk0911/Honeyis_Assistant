var main = {
    init: function () {
        var _this = this;
        $('#btn-signup').on('click', function () {
            _this.save();
        }),
        $('#btn-signin').on('click', function () {
            _this.signin();
        });
    },

    save: function () {
        var data = {
            userId: $('#userId').val(),
            password: $('#password').val(),
            company: $('#company').val()
        };

        var checkEamil = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

        if(!data.userId || !data.password || !data.company) {
            alert("모든 값을 입력해주시기 바랍니다.");
        }
        else if(!checkEamil.test(data.userId)) {
            alert("이메일 형식을 맞춰주시기 바랍니다.");
        }
        else {
            $.ajax({
                type: 'POST',
                url: '/api/v1/signup',
                dataType: 'json',
                contentType: 'application/json; charset = utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('회원가입이 완료되었습니다.');
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    },

    signin: function () {
        var data = {
            userId: $('#userId').val(),
            password: $('#password').val()
        }

        var checkEamil = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

        if(!data.userId || !data.password) {
            alert("모든 값을 입력해주시기 바랍니다.");
        }
        else if(!checkEamil.test(data.userId)) {
            alert("이메일 형식을 맞춰주시기 바랍니다.");
        }
        else {
            $.ajax({
                type: 'POST',
                url: '/api/v1/signin',
                dataType: 'json',
                contentType: 'application/json; charset = utf-8',
                data: JSON.stringify(data)
            }).done(function() {              
                alert('Signin Sucess');  
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    }
};

main.init();