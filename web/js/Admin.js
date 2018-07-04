const PREFIX_URL="http://localhost:8080/musicweb"
const SINGER_IAMGE_URL="http://localhost:8080/singer/";
const Image_URL="http://localhost:8080/userImg/";

const UPLOADSONGS_URL=PREFIX_URL+"/UploadSongs";  
const UPLOADLRC_URL=PREFIX_URL+"/UploadLrcs";
const DLSONGS_URL=PREFIX_URL+"/DeleteSongs";
const SEARCHUSER_URL=PREFIX_URL+"/searchUser";
const GETUSERFORM_URL=PREFIX_URL+"/userForm";
const POSTUSERFORM_URL=PREFIX_URL+"/htmlUserAlter";
const DLUSER_URL=PREFIX_URL+"/DeleteUser";
const ADDSINGER_URL=PREFIX_URL+"/AddSinger";
const SEARCH_SINGER_URL=PREFIX_URL+"/searchSinger";
const DELETESINGER_URL=PREFIX_URL+"/DeleteSinger";
const UPDATESINGER_URL=PREFIX_URL+"/updateSinger";


function getsearchRtVue(){
    var searchResult=new Vue({
        data:{
            songlist:[],
            searchString:""
           
    
        },
        computed:{
            cpsonglist:function(){
             var b=this.songlist;
             if(b.length!=0)
             {
                
                b=durationConver(b);
             }
              return b;
    
              
            }
        },
        beforeUpdate:function(){
        }
    });
    return searchResult;
};
function durationConver(songList) {
    songList.forEach(function(element) {
        var duration=parseInt(element.duration);
        var href=element.songId;
        href="/musicweb/GetSongs?StrsongId="+href;
        element.href=href;
        var m=parseInt(duration/60);
        var s=duration%60;
        var duration=((m<10)?"0"+m:m)+":"+((s<10)?"0"+s:s);
        element.duration=duration;

});
    return songList;
};
function getSongListData(searchRtVue){
    $.get("http://localhost:8080/musicweb/htmlsearchResult",{"searchStr":searchRtVue.searchString},
        function (data, textStatus, jqXHR) {
            searchRtVue.songlist=data;
        },
        "json"
    );
};
function JqOperation(){

    // 绑定的th在mount之前不存在
    $(".searchRT thead input[type='checkbox']").change(function (e) { 
        e.preventDefault();
        //$(".all-selected").toggle("slow");
       // alert(this.checked);
        if(this.checked)
        {
            $(".searchRT tbody input[type='checkbox']").attr("checked", true);

        }
        else
        {
            $(".searchRT tbody input[type='checkbox']").attr("checked", false);

        }
        
    });


    $(".searchRT tbody tr,.user-item,.singer-item").live(
        {
            "mouseover":function () {
            // over
            $(this).find(".select").css("display", "block");
            //$(this).addClass("table-active");
            //alert( $(this).find(".select").length);
        },
            "mouseout":function () {
            // out
            $(this).find(".select").css("display", "none");
            //$(this).removeClass("table-active");
        }
        }
    );


    $(".select ul a").live(
        {
            "mouseover":function () {
                // over
                $(this).css("background-position-x", "-40px");
            },
            "mouseout":function () {
                // out
                $(this).css("background-position-x", "0px");
            }

        }
    );
};

function PostDlSongs(dlSongs){
      dlSongs=JSON.stringify(dlSongs);
      return $.ajax({
          type: "Post",
          url: DLSONGS_URL,
          data: dlSongs,
          dataType: "json",
          contentType:"application/json;charset=utf-8"
         
      });
}

$(function () {

    
    var searchRT=getsearchRtVue().$mount("#songs-show");
    
    
    JqOperation();

    $("#searchstr").on("input",function(){
        // searchRT.searchString=this.value;
         getSongListData(searchRT);
     });

    $("#searchbt").click(function (e) { 
        e.preventDefault();
        getSongListData(searchRT);
    });
    
    $(".select-delete",).live("click",function (e) { 
        e.preventDefault();
        var r=confirm("是否删除歌曲？");
        if(r)
       {
        var th=$(this).parents("tr")[0];
        console.log(th);
        var dlSongs=new Array();
        dlSongs.push(parseInt(th.dataset.songid));
        console.log(dlSongs);
        var PostDlSongsxhr= PostDlSongs(dlSongs);
        PostDlSongsxhr.success(function(response){
               if(response.state)
               {
                $("#showMessage").empty();
                $("#showMessage").append(response.Message);
                $("#uploadMessage").modal("show");
                setTimeout(function(){
                    $("#uploadMessage").modal("hide");
                },1000);
                getSongListData(searchRT);
               
               }
               
        });
        
       }
    });

    $("#dlSelectedSongs").click(function (e) { 
        e.preventDefault();
        var r=confirm("是否删除歌曲？");
        if(r)
        {
            var dlSongs=new Array();
            var ths=$("#searchRT table tbody input:checked").parents("tr").get();
            console.log(ths);
            for (const th of ths) {
                dlSongs.push(parseInt(th.dataset.songid));
            }
            console.log(dlSongs);
            var PostDlSongsxhr= PostDlSongs(dlSongs);
            PostDlSongsxhr.success(function(response){
                if(response.state)
                {
                    $("#showMessage").empty();
                    $("#showMessage").append(response.Message);
                    $("#uploadMessage").modal("show");
                    setTimeout(function(){
                        $("#uploadMessage").modal("hide");
                    },1000);
                    getSongListData(searchRT);
                
                }
                
            });
        }
        
        
    });

    console.log(searchRT);


});






/*uploadComponent*/

function UploadSongs(SongsForm,url){
    $.ajax({
        type: "POST",
        url: url,
        data: SongsForm,
        dataType: "json",
        processData: false,  // 不处理数据
        contentType: false,   // 不设置内容类型
        success: function (response) {
            if(response.state)
            {
               // alert(response.Message);
               $("#showMessage").empty();
               $("#showMessage").append(response.Message);
               setTimeout(function(){
               $("#uploadMessage").modal("hide");
               },500);
            }
            else
            {
                $("#uploadMessage").modal("hide");
                alert(response.Message);
            }
               
        }
    });
};

function getshowFilesVue() {

    var showFilesVue=  new Vue({       
          data:{
              files:[]
          },
          computed:{
              cpfiles:function(){
                  var cpfiles=this.files;
                  for (const file of cpfiles) {
                      file.msize=parseFloat(file.size/1024/1024).toFixed(2);
                      file.Ksize=parseInt(file.size/1024);
                  }
                  return cpfiles; 
              }
          }
    });

    return showFilesVue;
   
};
function updateShowFilesVue(showFilesVue,showFiles){
    showFilesVue.files=showFiles;
};
/*mp3 */
$(function () {

    var showMp3Files =getshowFilesVue().$mount("#showMp3Files");
    $("#uploadBtn").click(function (e) { 
        e.preventDefault();
        if(showMp3Files.files.length!=0){

        var SongsForm=new FormData($("#uploadSongsForm")[0]);
        console.log(SongsForm);
        $("#showMessage").empty();
        $("#showMessage").append("正在上传，请稍后.....");
        $("#uploadMessage").modal("show");
        UploadSongs(SongsForm,UPLOADSONGS_URL);

        }
        else{
            alert("上传文件不能为空！");
        }
    });

    $("#mp3Files").change(function(){
        var files=this.files;
        console.log(files);
        updateShowFilesVue(showMp3Files,files);
    });

    $("#clearmp3").click(function(){
        updateShowFilesVue(showMp3Files,[]);
    });

});
/*lrc */
$(function () {
    var showLrcFiles=getshowFilesVue().$mount("#showLrcFiles");
    $("#uploadLrcBtn").click(function (e) { 
        e.preventDefault();
        var lrcsForm=new FormData($("#uploadlrcsForm")[0]);
        console.log(lrcsForm);
        $("#showMessage").empty();
        $("#showMessage").append("正在上传，请稍后.....");
        $("#uploadMessage").modal("show");
        UploadSongs(lrcsForm,UPLOADLRC_URL);
    });
    $("#lrcFiles").change(function(){
        var files=this.files;
        console.log(files);
        updateShowFilesVue(showLrcFiles,files);
    });

    $("#clearlrc").click(function(){
        updateShowFilesVue(showLrcFiles,[]);
    });

});


/*user manage */
function getUserVue(){
    return new Vue({
        data:{
            users:[]
        },
        computed:{
            cpusers:function(){
                var cpusers=this.users;
                cpusers.forEach(function(cpuser){
                    cpuser.src=Image_URL+cpuser.image;
                    if(cpuser.sex)
                    cpuser.sexclass="male";
                    else
                    cpuser.sexclass="female";
                })
                return cpusers;

            },
            hasUser:function(){
                if(this.users.length==0)
                return false;
                else
                return true;
            }
        }
        
    })
};
function updateVue(userVue,url,postDate){
    $.ajax({
        type: "POST",
        url: url,
        data: postDate,
        dataType: "json",
        success: function (response) {
            userVue.users=response;
            
        }
    });
};
function postuserForm(postuserFormUrl,form){
    var userForm=new FormData(form);
    userForm.append("userId",parseInt(form.dataset.userid));
    $.ajax({
        type: "Post",
        url: postuserFormUrl,
        data: userForm,
        dataType: "text",
        processData: false,  // 不处理数据
        contentType: false,   // 不设置内容类型
        success: function (response) {
            
            $("#user-edit").modal("hide");
            $("#userSearch-btn").trigger("click");
            alert(response);
        }
    });
};
function getUserForm(userId,getUserFormUrl){
    $.ajax({
        type: "GET",
        url: getUserFormUrl,
        data: userId,
        dataType: "html",
        success: function (response) {
            $("#user-form-show").empty();
            $("#user-form-show").append(response);
            $("#user-edit").modal("show");

        }
    });

};
function DeleteUser(userId,DeleteUserUrl){
    $.ajax({
        type: "POST",
        url: DeleteUserUrl,
        data:userId,
        dataType: "json",
        success: function (DLstate) {
            if(DLstate.state)
            {
                alert(DLstate.message);
                refreshUser();
            }
            else
            {
                alert(DLstate.message);
            }
        }
    });
};
function refreshUser(){
    $("#userSearch-btn").trigger("click");
}
function formCheckIsNUll(formName,name){
    var name=$("#"+formName+" input[name='"+name+"']").val().trim();
    if(name==="")
        return true;
    else
        return false;

}
function fileFormCheckIsNUll(formName,name){
    var files=$("#"+formName+" input[name='"+name+"']")[0].files;
    if(files.length==0)
        return true;
    else
        return false;

}
function formCanPost() {

}
$(function(){
    var userSearchVue=getUserVue().$mount("#user-seach-show");
    $("#userSearch-btn").click(function (e) { 
        e.preventDefault();
        var Search=new Object();
        Search.searchStr=$("#userSearch").val();
        updateVue(userSearchVue, SEARCHUSER_URL,Search);
        console.log(userSearchVue);
    });
   $("#userSearch").on("input", function () {
           refreshUser();
   });

    $(".user-item").live("click",function(){
         // $("#user-edit").modal("show");
         var userId={"userId":parseInt(this.dataset.userid)};
         getUserForm(userId,GETUSERFORM_URL);
         
    });
    
    $(".delete-user").live("click",function(e){
        var r=confirm("确认删除用户吗？");
        if(r) 
        {
            var id=parseInt($(this).parents(".user-item")[0].dataset.userid);
            var userId={"userId":id};
            console.log(userId);
            //alert("正在删除");
            DeleteUser(userId,DLUSER_URL);
        }
        e.stopPropagation();
        
        

    });

    $("#userFormPost-btn").click(function (e) { 
        e.preventDefault();
        if(!formCheckIsNUll("userForm","name")&&!formCheckIsNUll("userForm","password"))
        {
            var userForm=$("#userForm")[0];
            postuserForm(POSTUSERFORM_URL,userForm);
            refreshUser();
        }
        else
        {

            alert("用户名或密码不能为空！");
        }


    });

});



/*singerManage */
function readPicture(file,img){
    $(file).change(function () { 
        var file=$(this)[0].files;
        console.log($(this));
        var reader=new FileReader();
        reader.readAsDataURL(file[0]);
        var current=this;
        reader.onload=function(e){
            //alert("文件读取完成");
             $(img).attr("src", e.target.result);
            
        }
        
    });
};
function changeSingerManageView(){
    $("#singerSearch").toggle();
    $("#singerMessage").toggle();
};
function createChangeSingerManageView(){
    $("#singerSearch").toggle();
    $("#addSinger").toggle();
};
function postSingerForm(singerForm,postSingerFormUrl){
    var form=new FormData(singerForm);
    $.ajax({
        type: "POST",
        url: postSingerFormUrl,
        data: form,
        dataType:"json",
        processData: false,  // 不处理数据
        contentType: false,   // 不设置内容类型
        success: function (postMessage) {
            if(postMessage.state)
            {
                alert(postMessage.message);
                resetForm();
                createChangeSingerManageView();
                refreshSinger();
            }
            else
            {
                alert(postMessage.message);
            }
        }
    });
};
function postUpdateSingerForm(singerForm,updateUrl,singerMessageVue){
    var form=new FormData(singerForm);
    form.append("singerId",singerMessageVue.singer.singerId);
    $.ajax({
        type: "POST",
        url: updateUrl,
        data: form,
        dataType:"json",
        processData: false,  // 不处理数据
        contentType: false,   // 不设置内容类型
        success: function (postMessage) {
            if(postMessage.state)
            {
                resetSingerMessageForm();
                alert(postMessage.message);
                changeSingerManageView();
                refreshSinger();
                
            }
            else
            {
                alert(postMessage.message);
            }
        }
    });
};
function getSingerForm(singerForm,getSingerFormUrl){
    var form=new FormData(singerForm);
    $.ajax({
        type: "GET",
        url: getSingerFormUrl,
        data: form,
        dataType:"html",
        success: function (postMessage) {
           
        }
    });
};
function getSingerVue(){
    return new Vue({
        data:{
            singers:[]
        },
        computed:{
             cpSingers:function(){
                 var cpSingers=this.singers;
                 cpSingers.forEach(function(cpSinger){
                     cpSinger.src=SINGER_IAMGE_URL+cpSinger.iamge;

                 })
                return cpSingers;
             },
             hasSinger:function(){
                 if(this.singers.length==0)
                 return false;
                 else
                 return true;
             }
        }
    })
};
function updateSingerVue(SingerVue,updateSingerVueUrl,searchstr){
    $.ajax({
        type: "GET",
        url: updateSingerVueUrl,
        data: searchstr,
        dataType: "json",
        success: function (singers) {
            console.log(singers);
            SingerVue.singers=singers;
            console.log(SingerVue);
            
        }
    });
};
function getSingerMessageVue(){
    return new Vue({
        data:{
          singer:{}
        },
        computed:{
            cpsinger:function(){
                var cpsinger=this.singer;
                cpsinger.src=SINGER_IAMGE_URL+cpsinger.iamge;
                return cpsinger;
            }
        }
    })
};
function updateSingerMessgerVue(singerMessageVue,singerVue,index){
    singerMessageVue.singer=singerVue.cpSingers[index];
    console.log( singerMessageVue.singer);
    console.log(singerMessageVue);
    
    
};
function deleteSinger(singerId,DeleteSignerUrl){
    $.ajax({
        type: "POST",
        url: DeleteSignerUrl,
        data:singerId,
        dataType: "json",
        success: function (DLstate) {
            if(DLstate.state)
            {
                alert(DLstate.message);
                refreshSinger();
            }
            else
            {
                alert(DLstate.message);
            }
        }
    });
};
function refreshSinger(){
    $("#singerSearch-btn").trigger("click");
};
function resetForm(){
    $("#c-form-re").trigger("click");
    $("#singer-Image-show-Create").attr("src","");
};
function resetSingerMessageForm(){
    $("#singerMessge-re").trigger("click");

};
$(function () {

    var singerVue=getSingerVue().$mount("#singer-Search-show");
    var singerMessageVue=getSingerMessageVue().$mount("#singerMessage");

    $("#singerSearch-btn").click(function (e) { 
        e.preventDefault();
        var str=$("#singerSearchstr").val();
        var searchStr={"searchStr":str};
        console.log(searchStr);
        updateSingerVue(singerVue,SEARCH_SINGER_URL,searchStr);
        
    });
    $("#singer-Image-show,#singer-Image-show-btn").live("click",function (e) {
        e.preventDefault();
        $("#singer-Image-file").trigger("click");
    });
    $("#singer-Image-show-Create,#singer-Image-show-btn-Create").click(function (e) {
        e.preventDefault();
        $("#singer-Image-file-Create").trigger("click");
    });

    var $file = $("#singer-Image-file");
    var $img = $("#singer-Image-show");
    readPicture($file, $img);

    var $file = $("#singer-Image-file-Create");
    var $img = $("#singer-Image-show-Create");
    readPicture($file, $img);

    $(".singer-item").live("click", function (e) {
        e.preventDefault();
        var index=parseInt(this.dataset.index);
        updateSingerMessgerVue(singerMessageVue,singerVue,index);
        singerMessageVue.$forceUpdate();
        changeSingerManageView();
        
    });
    $("#backSingerSearch-btn").live("click", function (e) {
        e.preventDefault();
        changeSingerManageView();
        resetSingerMessageForm();
        
       
    });


    // $(".singer-item").live("click",function (e) { 
    //     e.preventDefault();
       
        
    // });


    $(".create-singer-item,#backSingerSearch-btn-Create").live("click", function (e) {
        e.preventDefault();
        createChangeSingerManageView();
       
    });

    $("#add-Singer-btn").click(function (e) { 
        e.preventDefault();
        if(!formCheckIsNUll("singer-Create-Form","name"))
        {
            var addSingerForm=$("#singer-Create-Form")[0];
            console.log(addSingerForm);
            postSingerForm(addSingerForm,ADDSINGER_URL);
        }
        else {
            alert("歌手名不能为空");
        }
        
    });

    $(".delete-singer").live("click",function (e) { 
        e.preventDefault();
        var r=confirm("确认删除歌手吗？");
        if(r) {
           
            var Id=parseInt($(this).parents(".singer-item")[0].dataset.singerid);
            var singerId={"singerId":Id};
            console.log(singerId);   
            deleteSinger(singerId,DELETESINGER_URL);
        }
        e.stopPropagation();
        
    });

    $("#c-form-re-over").click(function (e) { 
        e.preventDefault();
        resetForm();
    });

    $("#postSingerForm-btn").click(function (e) {
        e.preventDefault();
        if(!formCheckIsNUll("singer-Message-Form","name"))
        {
            var updateSingerForm=$("#singer-Message-Form")[0];
            console.log(updateSingerForm);
            postUpdateSingerForm(updateSingerForm,UPDATESINGER_URL,singerMessageVue);
        }
        else {
           alert("歌手名不能为空");
        }


    });
    
    $("#singerMessge-re-over").click(function (e) { 
        e.preventDefault();
        resetSingerMessageForm();
    });
   


});
