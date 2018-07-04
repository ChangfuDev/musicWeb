// JavaScript Document
// JavaScript Document
//播放器栏效果控制

	$(function palyControls(){
		
	
		
		$("#Next").mouseenter(function(){
			this.className="NextEnter";
		});
		$("#Next").mouseleave(function(){
			this.className="NextLeave";
		});
		
		$("#Previous").mouseleave(function(){
			this.className="PreviousLeave";
		});
		$("#Previous").mouseenter(function(){
			this.className="PreviousEnter";
		});
		
		$("#Controls-right").children().mouseenter(function(){
			  this.className=this.id+"Enter";
		});
		$("#Controls-right").children().mouseleave(function(){
			  this.className=this.id+"Leave";
		});
		
	$("#volume-Controls").click(function(){
		event.stopPropagation();      //Propagation：传播
	});
		
	$("#pg-Controls").click(function(){
		
		var e=event||window.event;
		var movPos=e.offsetY;
		$("#Vo-header").css("bottom",90-movPos+"px");
		$("#mov").css("top",movPos+"px");
		
	});
		
	$("#mov").click(function(){
		
		var e=event||window.event;
		var movPos=e.offsetY;
		
		var h=$("#Vo-header").css("bottom").replace("px","");
		var m=parseInt($("#mov").css("top").replace("px",""));
		
		$("#Vo-header").css("bottom",h-movPos+"px");
		$("#mov").css("top",m+movPos+"px");
		event.stopPropagation();
	});
		 

	$("#volume").click(function(){
		
		
		if($("#volume-Controls").attr("class")=="")
			{
				$("#volume-Controls").attr("class","volume-Controls-hide");
			}
		else
			{
				$("#volume-Controls").attr("class","");
			}
		
		
	});

	 $("#list").click(function(e){
		e.preventDefault();
		$("#music-list-bg").toggle("fast");
	});
	$("#music-list-bg").click(function (e) { 
		e.preventDefault();
		
	});
	 $(".music-list-bg").click(function(){
			event.stopPropagation();
		});
	 
			 $("#mode").click(function(){
	
		 $(".mode-bg").toggle("slow");

	});
		$(".mode-bg").click(function(){
			event.stopPropagation();
		});


		$(".mymusicList").hover(function () {
				// over
			//$(".hide").show("slow");
			//	$(this).css("background", "#282c34");
			$(this).stop();
			$(this).animate({right: '0px'}, "slow");

			}, function () {
				// out
				//$(".hide").hide("slow");
				//$(this).css("background", "");
				$(this).stop();
				$(this).animate({right: '-380px'}, "slow");
			}
		);

		
	});
