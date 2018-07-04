$(function () {


    $("#clickme").click(function () {
        $(".window").css("display", "block");
        $("body").css("overflow", "hidden");
    });
    $("#ok").click(function () {
        var lis = $(".RT-content li input:checked").parent();
        var songs = new Array();
        if (lis.length > 0) {
            for (var i = 0; i < lis.length; i++) {
                songs[i] = { musicID: lis[i].dataset.id }

            }

        }
        var listName = $("#musiclistName").attr("value");
        var listpic = $("#pic").attr("value");
        var listExplanation = $("#listExplanation").attr("value");
        var Bywho = $("#Bywho").attr("value");
        var mclist = { "listName": listName, "Bywho": Bywho, "listpic": listpic, "listExplanation": listExplanation, "songs": songs };
        var encoded = $.toJSON(mclist);
        var mclistencoded = encoded;
        $.post("addmusicListServelet", mclistencoded, function (data) {
            alert(data);
        });
        $(".window").css("display", "none");
        $("body").css("overflow", "auto");
    });
});
$(function (navigationmodule) {

    $(".li-content").mouseenter(function () {
        $(this).stop();
        $(this).find(".li-content-bg").stop();
        $(this).animate({ fontSize: "27px" }, "fast");
        $(this).find(".li-content-bg").animate({ width: "100%", opacity: 1 }, "fast");
    })
    $(".li-content").mouseleave(function () {
        $(this).stop();
        $(this).find(".li-content-bg").stop();
        $(this).animate({ fontSize: "25px" }, "fast");
        $(this).find(".li-content-bg").animate({ width: "0%", opacity: 0 }, "fast");
    })



});
$(function (mainheader) {

    $(".user-seach-click").mouseenter(function () {

        $(this).css("background-color", "#dd4f43");
    });
    $(".user-seach-click").mouseleave(function () {

        $(this).css("background-color", "#3d9bf2");
    });

    //$("body").attr("overflow","hiden");

});


 /**
  *   dP""b8 88   88 888888 888888 88 88b 88  dP""b8     88     88 88b 88 888888 
  *  dP   `" 88   88   88     88   88 88Yb88 dP   `"     88     88 88Yb88 88__   
  *  Yb      Y8   8P   88     88   88 88 Y88 Yb  "88     88  .o 88 88 Y88 88""   
  *   YboodP `YbodP'   88     88   88 88  Y8  YboodP     88ood8 88 88  Y8 888888 
  */
/*function changeSearchResult(){
    alert("i changed");
    // getSL(searchResult);
}*/
var getCount=0;
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
}


var songListInfoList=new Vue({
    data:{
        isLogin:false,
        songListInfoList:[]
    },
    computed:{
        cpsongListInfoList:function(){
            var b=this.songListInfoList;
            b.forEach(element => {

                element.src="http://localhost:8080/userImg/"+element.pic;
        });

            return b.reverse()
        }
    }
});

function getSL(searchResultVue) {
    getCount++;
    $.getJSON("http://localhost:8080/musicweb/htmlsearchResult", $("#searchform").serialize(),
        function (data, textStatus, jqXHR) {

            searchResultVue.songlist=data;
            console.log(searchResultVue.songlist);
        }
    );
}
$(function(){

    /**
     * 获取搜索结果，渲染到table中
     * jq绑定操作放在updata中不然会无效
     */

    var searchResult=new Vue({
        el:"#songListTable",
        data:{
            songlist:[]

        },
        computed:{
            cpsonglist:function(){
              var b=this.songlist;
               b=durationConver(b);
              return b;

              
            }
        },
        beforeUpdate:function(){
            console.log(getCount==1);
            if(getCount==1)
            {
                $(".songlisttable tbody").empty();
            }
        },
       methods:{

       },
        updated:function updataJQ() {


        }

        
    });
    //alert(isUser);

    /*实时搜索*/
    var searchText=document.getElementById("user-search");
    searchText.oninput=function () {
        getSL(searchResult);
    };

    var SLinfo=songListInfoList.$mount("#songlist");


    $(".Create-songlist-item").live("click",function (e) {
        alert("Create-songlist-item is click");
        e.preventDefault();
        $("#songlist-select").modal("hide");
        setTimeout(function(){
            $("#CreateSL-dialog").modal("show");
        },400);

    });

    $("#selected-play").click(function (e) {
        console.log("#selected-play is clicked");
        if($(".songlisttable tbody input:checked").length==0)
        {
            alert("请选择歌曲");
        }
        else
        {
            let checkedsongs=$(".songlisttable tbody input:checked").parent().parent();
            let checkedsongsid=new Array();

            for(let i=0;i<checkedsongs.length;i++)
            {
                checkedsongsid.push(parseInt(checkedsongs[i].dataset.songid));
            }

            console.log(checkedsongsid);
            checkedsongsid=JSON.stringify(checkedsongsid);
            console.log(checkedsongsid);
            e.preventDefault();
            //location.href="http://localhost:8080/musicweb/SelectedPlay?"+checkedsongsid;
            //location.
            $.ajax({
                type: "Post",
                url: "http://localhost:8080/musicweb/SelectedPlay",
                data: checkedsongsid,
                contentType: "application/json; charset=utf-8",

                success: function (response) {
                    if(response.PostState)
                    location.href="PlaySelected";
                }
            });
        }

    });



    $(".songlist-item").live("click",function (e) {
        console.log(".songlist-item is clicked");
        if($(".songlisttable tbody input:checked").length==0)
        {
            alert("请选择歌曲");
        }
        else
        {
            let checkedsongs=$(".songlisttable tbody input:checked").parent().parent();
            let checkedsongsid=new Array();
            let songlistId=new Array();
            for(let i=0;i<checkedsongs.length;i++)
            {
                checkedsongsid.push(parseInt(checkedsongs[i].dataset.songid));
            }
            songlistId.push(parseInt(this.dataset.songlistid));
            songlistId.push(0);
            let songlist={"songListId": songlistId,"songsId":checkedsongsid};
            console.log("songlistObject:");
            console.log(songlist);
            songlist=JSON.stringify(songlist);
            console.log("songlistString:"+songlist);
            e.preventDefault();
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/musicweb/addSong",
                data: songlist,
                contentType: "application/json; charset=utf-8",
                dataType: "text",
                success: function (response) {
                    alert(response);
                    $("#songlist-select").modal("hide");

                }
            });
        }

    });


    $(".songlisttable tbody tr").live(
        {
            "mouseover":function () {
            // over
            $(this).find(".select").css("display", "block");
            $(this).addClass("table-active");
            //alert( $(this).find(".select").length);
        },
            "mouseout":function () {
            // out
            $(this).find(".select").css("display", "none");
            $(this).removeClass("table-active");
        }
        }
    );
    $(".select-add").live("click",function (e) {
        e.preventDefault();
        $("#songlist-select").modal("show");

    });

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





    /*Ajax*/
   /* $.getJSON("http://localhost:8080/musicweb/getuser",
        function (data, textStatus, jqXHR) {
            console.log(data);
            if(data)
            {
                seachResult.userData=data;
                seachResult.isLogin=true;
            }


        }
    );*/


    $("#songlist-select").on("show.bs.modal",function(){
       if(SLinfo.isLogin)
       {
           console.log("LoaduserSl......");
           $.getJSON("http://localhost:8080/musicweb/loadUserSL",
               function (data, textStatus, jqXHR) {
                   console.log(data);

                   if(data.getInfoState)
                   {
                       SLinfo.songListInfoList=data.songListInfoList;
                       console.log(SLinfo.songListInfoList);
                       console.log("finish LoaduserSl");


                   }
                   else
                   {
                       alert(data.message);
                   }
               }
           )

       }
       else
       {
           console.log("用户未登录");
       }
    });
   
    /**
     * get请求获取搜索结果json数据，放到vue中更新
     */
    /*$.get("http://localhost:8080/musicweb/searchResult?searchStr=*",
        function (data, textStatus, jqXHR) {
            seachResult.songlist=data;
        },
        "json"
    );*/

    /*表单上传*/

 $(".user-seach-click").click(function (e) { 
     e.preventDefault();

     getSL(searchResult);

 });
    /*Ajax*/
   



   
     /**
      * 全选操作
      */
    $(".tablehead input[type='checkbox']").change(function (e) { 
        e.preventDefault();
        //$(".all-selected").toggle("slow");
       // alert(this.checked);
        if(this.checked)
        {
            $(".songlisttable tbody input[type='checkbox']").attr("checked", true);

        }
        else
        {
            $(".songlisttable tbody input[type='checkbox']").attr("checked", false);

        }
        
    });



   




   $(".slist-selectImg").click(function (e) { 
       e.preventDefault();

       
   });

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

    $("#sumit-sl").click(function (e) {
        e.preventDefault();
        //alert($("#songlist")[0].length);
        var formdata = new FormData($("#songlist-upload")[0]);
        console.log(formdata);
        /*  $.post("http://localhost:8080/musicweb/CreateSongList",formdata,
          function (data, textStatus, jqXHR) {
              alert(data);
          },
          "test"
      );*/
        $.ajax({
            url: "http://localhost:8080/musicweb/CreateSongList",
            type: "POST",
            data: formdata,
            processData: false,  // 不处理数据
            contentType: false,   // 不设置内容类型
            success: function (response) {
                alert(response);
            }
        });


    });
  
   
   /** */



   

  
   /* var scrollbefore=window.scrollY;
    
    $(window).scroll(function (event) {
     
        if(this.scrollY-scrollbefore>=0)
           {
        	console.log(this.scrollY-scrollbefore);
            scrollbefore=this.scrollY;
           // $(".main-header").slideUp("slow");
            $(".main-header").hide("slow");
           var client=$(".seach-Result")[0].getBoundingClientRect();
           //var offset= $(".seach-Result").scrollTop();
           console.log(client.top);
           if(client.top<=70)
           {
             $(".seach-Result").css({"height": "600px","overflow": "scroll"});
             $("body").css("overflow", "hidden");
           }

           // $(".navagation").animate({"top":"0"},"slow");
           }
          
            
       
    });
   
    /*$(".seach-Result").scroll(function(){
        console.log(this.scrollTop);
        if(this.scrollTop==0)
        {
            
            $(".seach-Result").css({"overflow": "hidden"});
             $("body").css("overflow", "scroll");
             $(".main-header").show("slow");
             $("body")[0].scrollTop=0;
        }
    });*/





  
});