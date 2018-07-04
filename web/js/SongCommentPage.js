$(function () {
  
    $(".lrc-btn").click(function (e) { 
        e.preventDefault();
        $(".lrc-show").slideToggle("slow");
    });
    $(".Comment-ft span").hover(function () {
        // over
        $(this).css("background-position-x", "5px");
    }, function () {
        // out
        $(this).css("background-position-x", "-20px");
    }
);

});