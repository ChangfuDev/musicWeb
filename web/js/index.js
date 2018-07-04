// JavaScript Document
$(function(navigationmodule){
		$(".li-content").mouseenter(function(){
			$(this).stop();
			$(this).find(".li-content-bg").stop();
			$(this).animate({fontSize:"27px"},"fast");
			$(this).find(".li-content-bg").animate({width:"100%",opacity:1},"fast");
		})
		$(".li-content").mouseleave(function(){
			$(this).stop();
			$(this).find(".li-content-bg").stop();
			$(this).animate({fontSize:"25px"},"fast");
			$(this).find(".li-content-bg").animate({width:"0%",opacity:0},"fast");
		})
		
	});
	$(function(dispalymodule){
		
		function dispaly()
		{
			var spans=$("#disnum span");
			var Current=0;
			var auto;
			$(".display-module").mouseenter(function(){
				$("#left").attr("class","lControls-out");
				$("#right").attr("class","rControls-out");
				$("#disnum").attr("class","disnum-out");
				
				
			});
			$(".display-module").mouseleave(function(){
				$("#left").attr("class","lControls-hide");
				$("#right").attr("class","rControls-hide");
				$("#disnum").attr("class","disnum-hide");
				
				
			});
			//无论向左向右点击都是left属性动画
			//他会根据之前元素的left进行动画
			$("#left").click(function(){
				$("#disnum span").attr("class","nodis");
				if(Current>0)
					{
						Current=Current-1;
						
					}
				else
					{
						if(Current==0)Current=4;
						else
							Current==0;
							
						
					}
				
				var movl=(Current)*100;
				spans[Current].className="spandis";
				$(".bg-Content").animate({left:'-'+movl+'%'},"slow");
				
			});
			$("#right").click(function(){
				$("#disnum span").attr("class","nodis");
				if(Current<4)
					{
						Current=Current+1;
						
					}
				else
					{
						if(Current==4)Current=0;
						else
							Current=0;
						
					}
				var movr=(Current)*100;
				spans[Current].className="spandis";
				$(".bg-Content").animate({left:'-'+movr+'%'},"slow");
				
			});

			//自动播放定时器 
				auto=setInterval(function(){
					$("#disnum span").attr("class","nodis");
					if(Current==5)
						Current=0;
					else
					  Current=Current+1;
				var mov=(Current)*100;
				spans[Current].className="spandis";
				$(".bg-Content").animate({left:'-'+mov+'%'},"slow");
					
				},5000);
			 /*   function iconclick()
			{
				for(var i=0;i<spans.length;i++)
					{
						spans[i].onclick=function(){
							alert(i);
							$("#disnum span").attr("class","nodis");
							 var mov=i*100;
				             this.className="spandis";
				             $(".bg-Content").animate({left:'-'+mov+'%'},"slow");
				
						}
					}
			}
			iconclick();*/
		
		}
		dispaly();
		
	});
	$(function(mcCommendmodule){
		
		//alert($(".content>:last").attr("class"));
		//$(".content .content-num:last-child").css({"float":"right","margin-right":"0"});
		$(".music-commend").mouseenter(function(){
			$("#d0-left").animate({opacity:1,width:'5%'},"slow");
			$("#d0-right").animate({opacity:1,width:'5%'},"slow");
		});
		$(".music-commend").mouseleave(function(){
			$("#d0-right").animate({opacity:0,width:0},"slow");
			$("#d0-left").animate({opacity:0,width:0},"slow");
		});
		
		$(".Listkind span").click(function(){
			$(".Listkind span").attr("class","");
			var index=$(this).index(".Listkind span");
			$(".commend-all>div").slideUp("slow");
			$(".commend-all>div").eq(index).slideDown("slow");
			//$(".commend-all>div").css("display","none");
			//$(".commend-all>div").eq(index).css("display","block");
			this.className="Listkindselected";
			$(".Content-pic div").attr("class","");
			//alert($(".Content-pic div").length);

			
		});


		
		   $("#d0-left").toggle(
		   function(){
			   //alert($(".commend-all:visible").length);
			   $(".commend-all:visible .page-1").toggle("slow");
			   $(".commend-all:visible  .page-2").toggle("slow");
		   },
							function(){
								$(".commend-all:visible .page-2").toggle("slow");
								$(".commend-all:visible .page-1").toggle("slow");
							});

		 $("#d0-right").toggle(
		   function(){
			$(".commend-all:visible .page-2").toggle("slow");
			$(".commend-all:visible .page-1").toggle("slow");
		   },
							function(){
								 $(".commend-all:visible .page-1").toggle("slow");
			                     $(".commend-all:visible .page-2").toggle("slow");
							});
		//歌单鼠标移入移出特效
		$(".Content-pic").mouseenter(function(){ 
			    $(this).stop();
				$(this).animate({backgroundSize:'120%'},"slow");
				this.lastChild.className="palyicon-in";
			
		});
		$(".Content-pic").mouseleave(function(){  
			  $(this).stop();
			  $(this).animate({backgroundSize:'100%'},"slow");
			  this.lastChild.className="palyicon-out";
		});

	
		 
	});



	$(function(mainheader){
		$(".user-seach-click").mouseenter(function(){
			$(this).css("background-color","#dd4f43");
		});
		$(".user-seach-click").mouseleave(function(){
			$(this).css("background-color","#3d9bf2");
		});

		
	});



    $(function(musictd){
		function lichangebg(parent){
		   var lis=$("."+parent+" ul li");
		   /*for(var i=0;i<lis.length;i++)
			{
				if(i%2==0)
					{
						lis[i].style.backgroundColor="#e8e8e8";
					}
				else
					{
						
					}
			}*/
		$("."+parent+" ul li").hover(function(){
			this.className="li-in";
		},function(){
			this.className="";
		});
		}
		lichangebg("day-commend");
		lichangebg("charts");
		/* <div class="tab"><span>1</span><span>2</span><span>3</span><span>4</span></div>*/
		$(".tab span").click(function(){
			$(".tab span").attr("class","");
			var index=$(this).index(".tab span");
			$(".day-commend ul").css("display","none");
			$(".day-commend ul").eq(index).css("display","block");
			this.className="spanselect";
			
		});
		
	});