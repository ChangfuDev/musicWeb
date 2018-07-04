

$(function () {

    $(".slist-img-select input[type='file']").change(function (e) { 
        e.preventDefault();
        var file=$("#imageFile")[0].files;
        var reader=new FileReader();
        reader.readAsDataURL(file[0]);
        reader.onload=function(e){
            //alert("文件读取完成");
             $(".slist-img").attr("src", e.target.result);
            
        }
        
    });
});


/*
 Sonlist Dialog component(歌单弹窗组件)
 */
const ImgURLprefix="http://localhost:8080/userImg/";

const prefixURL="http://localhost:8080/musicweb";

const loadCreateSLURL=prefixURL+"/loadUserSL";

const DeleteSL=prefixURL+"/DeleteSL";

const DLCollection=prefixURL+"/DLCollection";

const loadCollectedSLURL=prefixURL+"/loadUserCollectSL";

function getSongListVue(){
    var SongListVue=new Vue({
        data:{
            songListInfoList:[]

        },
        computed:{
            cpsongListInfoList:function(){
                var b=this.songListInfoList;
                b.forEach(element => {
                    element.src=ImgURLprefix+element.pic;
                    element.deleteUrl=DeleteSL+"?songListId="+element.songListId;
                    element.DLCollectionUrl=DLCollection+"?songListId="+element.songListId;
            });

                return b.reverse()
            }
        },
        updated:function(){

        }
    });
    return SongListVue;
}

function updateSongListVue(SongListVue,URL) {

    $.getJSON(URL,
        function (data, textStatus, jqXHR) {
            console.log(data);
            if(data.getInfoState)
            {
                SongListVue.songListInfoList=data.songListInfoList;
                console.log(SongListVue.songListInfoList);



            }
            else
            {
                alert(data.message);
            }
        }
    )
}
function formCheckIsNUll(formName,name){
    var name=$("#"+formName+" input[name='"+name+"']").val();
    if(name==="")
        return true;
    else
        return false;

}
$(function () {

    var CreateList=getSongListVue().$mount("#CreateList");

    /*请求用户歌单 */
    $("#songlist-select").on("show.bs.modal",function(){
        alert("modal");
        updateSongListVue(CreateList,loadCreateSLURL);
    });



    $(".songlist-item,.Create-songlist-item").live({
        "mouseover":function () {
            // over
            $(this).css("background", "#f2f2f2");
        },
        "mouseout":function () {
            // out
            $(this).css("background", "");
        }
    });
    $(".songlist-item").live({
        "mouseover":function () {
            // over
            $(this).find(".deleteSL").show();
        },
        "mouseout":function () {
            // out
            $(this).find(".deleteSL").hide();
        }
    });


    $(".songlist-item").live("click",function (e) {
        //alert(".songlist-item is clicked");
        var songlistid=this.dataset.songlistid;
        location.href="MusicList?songListId="+songlistid;

    });

    $(".Create-songlist-item").click(function () {
        //alert("Create-songlist-item is click");
        $("#songlist-select").modal("hide");
        setTimeout(function(){
            $("#CreateSL-dialog").modal("show");
        },400);

    });



    $(".deleteSL").live(
        {
            /*click:function(){
                //alert("歌单删除操作");
                console.log($(this).parent());
                var songListId=$(this).parent()[0].dataset.songlistid;
                $.post(DeleteSL, {"songListId":songListId},
                    function (data, textStatus, jqXHR) {

                    },
                    "html"
                );
            },*/
            mouseover:function(){
                $(this).css("background-position", "-40px -160px");
            },
            mouseout:function(){
                $(this).css("background-position", "0 -160px");
            }
        }
    )

    $("#userForm").on("submit",function () {
        if(!formCheckIsNUll("userForm","name")&&!formCheckIsNUll("userForm","password"))
        {
           return true;
        }
        else
        {

            alert("用户名或密码不能为空！");
            return false;
        }
    })
});



$(function () {
    var collectedSL=getSongListVue().$mount("#collected-songlist");
    /*请求用户歌单 */
    $("#collected-songlist").on("show.bs.modal",function(){
       updateSongListVue(collectedSL,loadCollectedSLURL);
    });


});