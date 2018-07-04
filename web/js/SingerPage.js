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
        $("#ML-intro").slideToggle("slow");
    });

    $("#playAll").click(function () {


        var $songstr=$("#singerSongs tr");
        var checkedsongsid=new Array();
        for(let i=0;i<$songstr.length;i++)
        {
            checkedsongsid.push(parseInt($songstr[i].dataset.songid));
        }
        console.log(checkedsongsid);
        checkedsongsid=JSON.stringify(checkedsongsid);
        console.log(checkedsongsid);
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
    })

});