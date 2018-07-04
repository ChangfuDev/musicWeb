const ImgURLprefix="http://localhost:8080/userImg/";

const prefixURL="http://localhost:8080/musicweb";

const LOAD_SEARCHSL_URL=prefixURL+"/htmlSearchLoadSL";

const MUSICLIST_URL=prefixURL+"/MusicList?songListId=";
var getCount=0;

function getSongListVue(){
    var SongListVue=new Vue({
        data:{
            songListInfoList:[],
            search:{}
           
        },
        computed:{
            cpsongListInfoList:function(){
                var b=this.songListInfoList;
                b.forEach(element => {
                    element.src=ImgURLprefix+element.pic;
                    element.href=MUSICLIST_URL+element.songListId;
                    //element.createUserId.src=ImgURLprefix+element.createUserId.image;

                });
                
                return b;
            }
        },
        beforeUpdate:function(){
            if(getCount==1)
            $("#songList-show .row").empty();
        },
        updated:function(){
              
        }
    });
    return SongListVue;
};
function getInputValue(){
    var inputValue=$(".user-seach").attr("value");
    console.log(inputValue);
    return inputValue;
};
function getSelectedTab()
{
    var selectedTab=$(".selected-tab")[0].innerHTML;
    return selectedTab;
}
function updateSongListVue(songListVue)
{
       
       getCount++;
        $.ajax({
            type: "GET",
            url: LOAD_SEARCHSL_URL,
            data: songListVue.search,
            dataType: "json",
            success: function (response) {
                songListVue.songListInfoList=response;
                console.log(songListVue.songListInfoList);
                console.log(songListVue.cpsongListInfoList);
                
            }
        }); 
};
$(function () {

    var searchSL=getSongListVue().$mount("#songList-show");
    $(".tabs span").click(function(){
        $(".tabs span").attr("class", "");
        $(this).attr("class", "selected-tab");
        searchSL.search.searchString=getInputValue();
        searchSL.search.tab=this.innerHTML;
        console.log(searchSL.search); 
        updateSongListVue(searchSL);
        
    });
    
    $("#searchBtn").click(function (e) { 
        e.preventDefault();
        searchSL.search.searchString=getInputValue();
        searchSL.search.tab=getSelectedTab();
        console.log(searchSL.search);
        updateSongListVue(searchSL);
        
    });


});