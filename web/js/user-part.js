var isUser=false;
$(function(usepart){


            var  user= new Vue({
             el:"#user-part",
             data:{
                 userData:{}
             },
             computed:{
                isLogin:function(){
                    if(this.userData.name)
                    return true;
                    else
                    return false;
                },
                 image:function(){
                         var image=this.userData.image;
                         image="/userImg/"+image;
                         return image;
                 },
                 Home:function () {
                     var Home=this.userData.userId;
                     Home="/musicweb/UserHome?strUserId="+Home;
                     return Home;
                 }

             },
                updated:function(){
                    isUser=true;
                    $("#user-pic").hover(function () {
                            // over
                            //$(".user-details").stop();
                            $(".user-details").show();
                        }, function () {
                            // out
                            // $(".user-details").stop();
                            $(".user-details").hide();
                        }
                    );
                }
         });
         //alert(user.userData.name);


         $("#user-pic").hover(function () {
                 // over
                 //$(".user-details").stop();

                 $(".user-details").show();
             }, function () {
                 // out
                // $(".user-details").stop();
                 $(".user-details").hide();
             }
         );

    $.getJSON("http://localhost:8080/musicweb/getuser",
        function (data, textStatus, jqXHR) {
            console.log(data);
            user.userData=data;
            if(data)
            {
                songListInfoList.isLogin=true;
            }



        }
    );


});


