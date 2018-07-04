//html 播放器
var ado;
/**
 *  ado.onplay=function(){
        
        one=l/ado.duration;
        Time();
        
    }
*/
var one;
//定时器
var mov;
//当前播放索引                 
var index;
//循环模式           
var mode="listcycle";  
//ul li 数组（songs）
var musics; 
 //进度条长度          
var l;  
//资源路径          
const prefixUrl = "http://localhost:8080/";
const mp3Url = prefixUrl + "music/";
const Album = prefixUrl + "Album/";
var getCount=0;
var  palyList;
$(function () {
   
   palyList=new Vue({
        el:"#playlist",
        data:{
        playSongs:[]

        },
        computed:{
            cpplaySongs:function(){
                var songs=new Array();
                songs=this.playSongs;
                songs.forEach(song => {
                    song.songName=song.artist+" - "+song.title;
                });
                return songs;

         }},
         methods:{
             /**
              * vue 生成的li点击事件
              * 选择相应的歌曲播放
              */
             change:function(e){
                 
                 console.log(event.currentTarget);
                 index=$(event.currentTarget).index(".music-list-bg ul li"); 
                 initlyc(index);
                 
                 
             }
         },
        mounted:function(){

        },
        beforeUpdate:function(){
            console.log(getCount==1);
            
           if(getCount==1)
           {
            $("#playlist").empty();
           }
        },
        updated:function(){
            
             
             
            initSongList();
            alert("pudated");
    
        }
        
    });

    ado=document.getElementById("audio1");
    l=parseInt($("#progress-bar").css("width").replace("px",""))-5;
   
    //初始化列表
    function initSongList(){
        index=0; 
        musics=$(".music-list-bg ul li");
        initlyc(index);
        //点击事件放在vue里面了，jq在Update方法中会出现重复绑定的现象
    }

    $("#back").click(function (e) {
        e.preventDefault();
        history.back();
    });


    //根据index初始化页面设置音源和图片歌词     
    function initlyc(index){
            
        $(".music-list-bg ul li").css("color","");
        musics[index].style.color="#2177c7";
        $("#mcontent-right").scrollTop(0);
        $("#header").css("margin-left",-5);
        $("#Pause")[0].className="Pause";
        clearInterval(mov);
        var listCount=$("#list span");
        listCount[0].innerHTML=musics.length;
        var playsong=$("#song");
        playsong[0].innerHTML=musics[index].dataset.song;
        $("#downloada,#musicbg-load").attr({"href":mp3Url+musics[index].dataset.song+".mp3","download":musics[index].dataset.song+".mp3"});
        ado.src=mp3Url+musics[index].dataset.song.replace("/",",")+".mp3";//
       // alert(mp3Url+musics[index].dataset.musicname+".mp3");
        if(musics[index].dataset.album=="false")
         {
            console.log("album==false");
            $("#pic-blur,#singer-pic,#singerpic").css("background-image","");
            $("#pic-blur").attr("class","pic-blur-no");
            $(".blur-ad").css("display", "none");
            $(".lyc").css("color","#2177c7");
         }
        else
            {

            /*加载图片**/
            console.log("album!=false");
            console.log("background-image",Album+musics[index].dataset.album+".jpeg");
            console.log(musics[index].dataset.album);
            console.log(Album);
            var album=musics[index].dataset.album.replace(/\ +/g,"%20");

            $("#pic-blur,#singer-pic,#singerpic").css("background-image","url("+Album+album+".jpeg)");
            $("#pic-blur").attr("class","pic-blur");
            $(".blur-ad").css("display", "block");
            $(".lyc").css("color","#484848");
            //$("#pic-blur").css("background-image","url(/musicweb/image/deflaut.png)");
             //$("#pic-blur").attr("class","pic-blur-no");
             //$(".lyc").css("color","#2177c7");
            }
        /*加载歌词*/    
        var song=musics[index].dataset.song;
        var songId=parseInt(musics[index].dataset.songid);
        //alert(song);
        var lrc=$("#lyc-content");
        $.get("/musicweb/producelrc",{"song":song,"songId":songId},function(data){
            lrc[0].innerHTML=data;
            //alert($("#lyc-content div p").length);
            if($("#lyc-content div p").length==1)
            {
                $("#lyc-content div p").css("margin-top","200px");
               
            }
            
        });
       
        
    }

  
    ado.onplay=function(){
        $("#Pause").attr("class","Play");
        one=l/ado.duration;
        Time();
        
    }
    //播放完事件
    ado.onended=function(){
        switch(mode)
            {
            
                case "cycle":
                    initlyc(index);
                    alert(mode);
                    break;
                case "listcycle":
                    if(index<musics.length-1)
                    {
                        index++;
                        initlyc(index);
                        alert(index);
                    }
                    else
                    {
                        index=0;
                        initlyc(index);
                    }
                    alert(mode);
                    break;
                case "Random":
                    index=Math.floor(Math.random()*(musics.length-1));
                    initlyc(index);
                    alert(mode);
                    break;
                default:
                    alert("hello");
            }

            setTimeout(() => {
            ado.play();
            }, 1000);
            
        
        
    }

        //进度条更新定时器
    function Time() {

        mov = setInterval("progress(" + one + ")", 1000);
    }
    //进度条
    progress=function (one){
        var lyc=$("#lyc-content div p");
        var w=parseFloat($("#header").css("margin-left").replace("px",""));
        var t=parseInt($("#lyc-content").css("top").replace("px",""));	
        
    //	var top=$("#lyc").scrollTop();
        var top=null;
        
        if(l-w>one)
            {
                w=w+one;
                $("#header").css("margin-left",w+"px");
                var M=parseInt(ado.currentTime/60);
                var s=parseInt(ado.currentTime%60);
                var ms=ado.currentTime.toString().split(".");
                if(s<10)s="0"+s;
                if(M<10)M="0"+M;
                var songtime=$("#songtime");
                songtime[0].innerHTML=M+":"+s;
                
                for(i=0;i<lyc.length;i++)
                {
                    var ch=lyc[i+1].firstChild;
                    var a=parseInt(ch.innerHTML.substring(ch.innerHTML.indexOf(":")+1,ch.innerHTML.indexOf(":")+3))-parseInt(s);
                    if(lyc[i].innerHTML.indexOf(M+":"+s)!=-1)
                    {
                        top=lyc[i].offsetTop;
                        $("#mcontent-right").scrollTop(top-238);
                        lyc[i].className="lyc";
                    }
                    if(a==0)
                        {
                        lyc[i].className="";
                        }
                }	
            
                //$("#lyc-content").css("top",top+"px");
            }
        else
            {
                    $("#Pause").attr("class","PauseLeave");
                    $("#header").css("margin-left",-5); 
                    clearInterval(mov);
                    
            }
            
        
    };



    
  
    

    //不更新是的默认点击事件，选择歌曲播放
    $("#playlist li").click(function(){
 
        index=$(this).index(".music-list-bg ul li"); 
        initlyc(index);
        
    });



    $("#Pause").click(function(){
       if(ado.paused)
        {
          
          //alert("你点击了播放");
          ado.play();
          
       //alert(parseInt(ado.duration));
         // alert(ado.src);
          ado.play();
          this.className="Play";
 
        }
       else
           {
               clearInterval(mov);
               ado.pause();
              // alert(ado.currentTime);
               this.className="Pause";

               
           }
       
        
    });

    $("#Previous").click(function(){
        if(index>0)
        {
            index--;
            initlyc(index);
            alert(index);
        }
        else
       {
            index=musics.length-1;
            initlyc(index);
       }
    });

    $("#Next").click(function () {
        if (index < musics.length - 1) {
            index++;
            initlyc(index);
            alert(index);
        }
        else {
            index = 0;
            initlyc(index);
        }
    });

    function selectmode(){
   
        $(".mode-bg ul li").click(function(){
            $(".mode-bg ul li").css("color","");
            $(this).css("color","#15b8cf");
            mode=$(this).attr("id");
            alert(mode);
        });
        
    
    }
    selectmode();
  
    //点击进度跳转
   function move(){
       
       var e=event||window.event;
       var movPos=e.offsetX;
       if(!ado.paused)
           { 
              
               $("#header").css("margin-left",movPos);
               var T=movPos/one;
               ado.currentTime=T;
               var M=parseInt(ado.currentTime/60);
               var s=parseInt(ado.currentTime%60);
               var ms=ado.currentTime.toString().split(".");
               if(s<10)s="0"+s;
               if(M<10)M="0"+M;
               
               for(i=0;i<lyc.length;i++)
               {
                   
                   var ch=lyc[i].firstChild;
                   var a=parseInt(ch.innerHTML.substring(ch.innerHTML.indexOf(":")+1,ch.innerHTML.indexOf(":")+3))-parseInt(s);
                  if(Math.abs(ado.CurrentTime-a)<2)
                      {
                      
                      
                       top=lyc[min[0].lyc].offsetTop;
                          $("#mcontent-right").scrollTop(top-238);
                          lyc[i].className="lyc";
                     
                      
                      }
                  else
                      {
                      
                      lyc[i].className="";
                      }

                   
                   
               }	
           
               
           }
          
       
       
   }
   $("#progress-bar").click(function(){move();});
    //点击进度跳转    



  
   
  

    /**
     * 88     88""Yb  dP""b8  dP""b8  dP"Yb  88b 88 888888 88""Yb  dP"Yb  
     * 88     88__dP dP   `" dP   `" dP   Yb 88Yb88   88   88__dP dP   Yb 
     * 88  .o 88"Yb  Yb      Yb      Yb   dP 88 Y88   88   88"Yb  Yb   dP 
     * 88ood8 88  Yb  YboodP  YboodP  YbodP  88  Y8   88   88  Yb  YbodP  
     */
    //歌词参数

    
   
    /*$.getJSON("http://localhost:8080/musicweb/htmlGetSongs",{"songId":0,"songListInfoId":8},
        function (data, textStatus, jqXHR) {
            console.log(data);
            palyList.playSongs=data;
            console.log(palyList.playSongs);
            
        }
    );*/

    initSongList();
    //initlyc(index);
    $(".songlist-item").click(function (e) {
       // alert("info");
        getCount++;
        if(!ado.paused)
            ado.pause;
        e.preventDefault();
        $("#Pause")[0].className="Pause";
        var songListInfoId=parseInt(this.dataset.songlistid);
        $.getJSON("http://localhost:8080/musicweb/htmlGetSongs",{"songId":0,"songListInfoId":songListInfoId},
            function (data, textStatus, jqXHR) {
                console.log(data);
                palyList.playSongs=data;
                console.log(palyList.playSongs);

            }
        );

    });

    $("#update").click(function (e) { 
        getCount++;
        if(!ado.paused)
        ado.pause;
        e.preventDefault();
        $("#Pause")[0].className="Pause";
        palyList.playSongs=[];
        $.getJSON("http://localhost:8080/musicweb/htmlGetSongs",{"songId":0,"songListInfoId":9},
        function (data, textStatus, jqXHR) {
            console.log(data);
           
            palyList.playSongs=data;
            console.log(palyList.playSongs);
            
        }
    );
    });
    $("#update2").click(function (e) { 
        getCount++;
        if(!ado.paused)
        ado.pause;
        e.preventDefault();
       
        $("#Pause")[0].className="Pause";
       
        $.getJSON("http://localhost:8080/musicweb/htmlGetSongs",{"songId":0,"songListInfoId":8},
        function (data, textStatus, jqXHR) {
            console.log(data);
           
            palyList.playSongs=data;
            console.log(palyList.playSongs);
            
        }
    );

    });

});

$(function () {
    var content=new Vue({
        el:".content",
        data:{
            songListInfoList:[],
            user:{}
        },
        computed:{
            cpsongListInfoList:function(){
                var b=this.songListInfoList;
                b.forEach(element => {
                    
                    element.src="http://localhost:8080/userImg/"+element.pic;
                });
                return b.reverse()
            },
            isLogin:function(){
                    if(this.user.name)
                    return true;
                    else
                    return false;
                },
            image:function () {
                var image=this.user.image;
                image="/userImg/"+image;
                return image;
            }
           
        },
        updated:function () {
            $(".songlist-item").click(function (e) {
               // alert("info");
                getCount++;
                if(!ado.paused)
                    ado.pause;
                e.preventDefault();
                $("#Pause")[0].className="Pause";
                var songListInfoId=parseInt(this.dataset.songlistid);
                $.getJSON("http://localhost:8080/musicweb/htmlGetSongs",{"songId":0,"songListInfoId":songListInfoId},
                    function (data, textStatus, jqXHR) {
                        console.log(data);
                        palyList.playSongs=data;
                        console.log(palyList.playSongs);

                    }
                );

            });
        }

    });

    /**获取用户 */

    $.getJSON("http://localhost:8080/musicweb/getuser",
             function (data, textStatus, jqXHR) {
                 console.log(data);
                 content.user=data;
             }
         );
    /**获取歌单 */
    $.getJSON("http://localhost:8080/musicweb/loadUserSL",
    function (data, textStatus, jqXHR) {
        console.log(data);
        if(data.getInfoState)
        {
            content.songListInfoList=data.songListInfoList;
        }
        else
        {
           // alert(data.message);
        }
    }
)


});


$(function () {
    $(".songlist-item").live({
        "mouseover":function () {
            // over
            $(this).css("background", "#f2f2f2");
        },
        "mouseout":function () {
            // out
            $(this).css("background", "");
        }
    });

})