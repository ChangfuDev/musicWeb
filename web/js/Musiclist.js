const PREFIX_URL="http://localhost:8080/musicweb";
const COLLECT_URL=PREFIX_URL+"/CollectSL";
function ajaxPost(data,postUrl) {
   $.ajax({
        type: "POST",
        url: postUrl,
        data: data,
        dataType: "text",
        success: function (response) {
            alert(response);
        }
    });

}
$(function () {
    $(".songlisttable tbody tr").hover(function () {
            // over
            $(this).find(".select").css("display","block");
            $(this).addClass("table-active");
            //alert( $(this).find(".select").length);
        }, function () {
            // out
            $(this).find(".select").css("display","none");
            $(this).removeClass("table-active");
        }
    );
    $(".select ul a").hover(function () {
            // over
            $(this).css("background-position-x", "-40px");
        }, function () {
            // out
            $(this).css("background-position-x", "0px");
        }
    );
    $(".intro-btn").click(function (e) { 
        e.preventDefault();
        $(".ML-intro").slideToggle("slow");
    });
    $(".Comment-ft span").hover(function () {
            // over
            $(this).css("background-position-x", "5px");
        }, function () {
            // out
            $(this).css("background-position-x", "-20px");
        }
    );

    $(".slist-img-select input[type='file']").change(function () {
        var file=$(this)[0].files;
        console.log($(this));
        var reader=new FileReader();
        reader.readAsDataURL(file[0]);
        var current=this;
        reader.onload=function(e){
            //alert("文件读取完成");
            $(".slist-img").attr("src", e.target.result);

        }

    });

    $("#conmmentBtn").click(function () {
        $("#Comment-txt").focus();
    });
    $("#collectBtn").click(function () {
        var collectId={"collectId":parseInt(this.dataset.songlistid)};
        console.log(collectId);
        ajaxPost(collectId,COLLECT_URL);

    });

    $(".reply-txt-show").click(function () {
        $(this).parents(".itemAndReply").children(".reply-txt").toggle("slow");
    })
    
    $(".Comments form").on("submit",function () {
        //alert("submit");
        console.log($(this).find("textarea"));
        var content=$(this).find("textarea").val();
        console.log("this is content"+content);
        if(content=="")
        {
            alert("评论内容不能为空！");
            return false;

        }
        else
        {
            return true;
        }

    })

});
