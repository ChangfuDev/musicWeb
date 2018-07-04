function colorNumber(list){
    var $songList= $(list);
    $($songList[0]).addClass("text-danger");
    $($songList[1]).addClass("text-primary");
    $($songList[2]).addClass("text-success");
    $($songList[9]).css("margin","0 39px");

}
$(function () {

    colorNumber($("#songlistClick-rank div h1"));
    colorNumber($("#songlistCollect-rank div h1"));
});