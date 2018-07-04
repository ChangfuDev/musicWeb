const ImgURLprefix="http://localhost:8080/userImg/";
const SINGER_IAMGE_URL="http://localhost:8080/singer/";
const prefixURL="http://localhost:8080/musicweb";

const LOAD_SEARCHSL_URL=prefixURL+"/htmlSearchLoadSL";
const SINGER_SEARCH_URL=prefixURL+"/searchSinger";
const SINGERPAGE_URL=prefixURL+"/singerPage?singerId=";
var getCount=0;

function getsingersVue(){
    var singersVue=new Vue({
        data:{
            singers:[],
            search:{}
           
        },
        computed:{
            cpsingers:function(){
                var b=this.singers;
                b.forEach(element => {
                    element.src=SINGER_IAMGE_URL+element.iamge;
                    element.href=SINGERPAGE_URL+element.singerId;
                    //element.createUserId.src=ImgURLprefix+element.createUserId.image;

                });
                
                return b;
            }
        },
        beforeUpdate:function(){
            if(getCount==1)
            $("#singer-show .row").empty();
        },
        updated:function(){
              
        }
    });
    return singersVue;
};
function getInputValue(){
    var inputValue=$(".user-seach").attr("value");
    console.log(inputValue);
    return inputValue;
};

function updateSingersVue(singersVue,updateURl)
{
       
       getCount++;
        $.ajax({
            type: "GET",
            url: updateURl,
            data:  singersVue.search,
            dataType: "json",
            success: function (response) {
                singersVue.singers=response;
                console.log( SingersVue);
                
                
            }
        }); 
};
function refreshSinger(){
    $("#searchBtn").trigger("click");
}
$(function () {

    var singersVue=getsingersVue().$mount("#singer-show");
    $("#searchBtn").click(function (e) { 
        e.preventDefault();
        singersVue.search.searchStr=getInputValue();
        console.log(singersVue.search);
        updateSingersVue(singersVue,SINGER_SEARCH_URL);
        
    });
    $(".user-seach").on("input", function () {
        refreshSinger();
    });


});