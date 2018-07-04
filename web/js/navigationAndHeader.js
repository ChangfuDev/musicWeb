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