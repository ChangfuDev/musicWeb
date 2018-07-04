function formCheckIsNUll(formName,name){
    var name=$("#"+formName+" input[name='"+name+"']").val();
    if(name==="")
        return true;
    else
        return false;

}
$(function(){
   
    /* $("#sign").click(function (e) { 
         e.preventDefault();
         
         
     });
 */
    $("#sign").click(function (e) {

        if(!formCheckIsNUll("signForm","name")&&!formCheckIsNUll("signForm","password"))
        {
            $('#myModal').modal('hide');

            //alert($(".sex")[0].value);
            $.post("http://localhost:8080/musicweb/signIn", $("#signForm").serialize(),
                function (signMessage, textStatus, jqXHR) {
                    console.log(signMessage);
                    console.log(signMessage.state);
                    if(signMessage.state)
                    {
                        $("#successModal h5")[0].innerHTML=signMessage.message;
                        $("#successModal").modal('show');
                        setTimeout(function(){

                                $("#successModal").modal('hide');
                                location.href="/musicweb/index";
                            },
                            1000);

                    }
                    else
                    {
                        $("#successModal h5")[0].innerHTML=signMessage.message;

                        $("#successModal").modal('show');
                        setTimeout(function(){$("#successModal").modal('hide');}, 1000);
                    }

                },
                "json"
            );
        }
        else
        {

            alert("用户名或密码不能为空！");
        }

 });

    $("#Login").click(function(e) {
        if(!formCheckIsNUll("loginForm","name")&&!formCheckIsNUll("loginForm","password"))
        {
            $.post(
                "http://localhost:8080/musicweb/login",
                $("#loginForm").serialize(),
                function(data, textStatus, jqXHR) {
                    //if(data.)
                    console.log(data);
                    if (data.loginState) {
                        if(data.admin)
                        {
                            location.href =
                                "/musicweb/adminPage";
                        }
                        else
                        {
                            location.href =
                                "/musicweb/index";
                        }

                    } else {
                        $("#successModal h5")[0].innerHTML = data.message;
                        $("#successModal").modal("show");
                        setTimeout(function() {
                            $("#successModal").modal("hide");
                        }, 1000);
                    }
                },
                "json"
            );
        }
        else
        {

               alert("用户名或密码不能为空！");
        }


    });





 });