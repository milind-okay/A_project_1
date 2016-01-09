(function($) {
	"use strict";
	
	$('#main-menu .menu').mobileMenu({
			defaultText: 'Navigate to...',					//default text for select menu
			className: 'select-menu',						//class name
			subMenuDash: '&nbsp;&nbsp;&nbsp;&ndash;'		//submenu separator
	});
	
	$("#main-menu").show();	
	$('#main-menu ul.menu').superfish({						// main menu settings
		hoverClass:  'over', 								// the class applied to hovered list items 
		delay:       0,                            		// one second delay on mouseout 
		animation:   {opacity:'show',height:'show'},  		// fade-in and slide-down animation 
		speed:       150,                          			// faster animation speed 
		autoArrows:  false,                           		// disable generation of arrow mark-up 
		dropShadows: true,                            		// disable drop shadows		
	});	
	
	$('.top-menu .menu ul').mobileMenu({
			defaultText: 'Navigate to...',					//default text for select menu
			className: 'select-menu',						//class name
			subMenuDash: '&nbsp;&nbsp;&nbsp;&ndash;'		//submenu separator
	});
			
	$('.footer-menu .menu').mobileMenu({
			defaultText: 'Navigate to...',					//default text for select menu
			className: 'select-menu',						//class name
			subMenuDash: '&nbsp;&nbsp;&nbsp;&ndash;'		//submenu separator
	});
	
	$(".feat-section .tab-titles ul li").click(function() {
		$(".feat-section .tab-titles ul li").removeClass('active');
		$(this).addClass("active");
		$(".feat-section .tab-content").hide();
		var selected_tab = $(this).find("a").attr("href");
		$(selected_tab).fadeIn();
		return false;
	});
	
	$("ul.slides").show();	
	$('.section1 .list-slider').flexslider({				// sliding settings for featured section 1
			animation: "fade",								// animation style
			slideshow: false,								// disable automatic sliding
			directionNav: true,  							// enable nav arrows
			controlsContainer: "#sec1-nav",  				// nav arrows container.
			controlNav: false,   							// disable navigation paging.
			smoothHeight: false,							// animate the container height smoothly
			animationSpeed: 800								// speed of animations in milliseconds
	});
	
	$('.section2 .sec-slider').flexslider({					// sliding settings for featured section 1
			animation: "fade",								// animation style
			slideshow: false,								// disable automatic sliding
			directionNav: true,  							// enable nav arrows
			controlsContainer: "#sec2-nav",  				// nav arrows container.
			controlNav: false,   							// disable navigation paging.
			smoothHeight: false,							// animate the container height smoothly
			animationSpeed: 800								// speed of animations in milliseconds
	});
	    
	$('.widget-title h4').each(function(){
		var me = $(this);
		me.html( me.text().replace(/(^\w+)/,'<span>$1</span>') );
	});
		
	$('#header .search i').click(function() {
		$('.searchfield').toggleClass('expanded');
	});
	
	$('#header .searchfield').focusout(function() {
		$('.searchfield').removeClass('expanded');
	});
  
	$(".widget_video iframe").each(function(){
      var ifr_source = $(this).attr('src');
      var wmode = "wmode=transparent";
      if(ifr_source.indexOf('?') != -1) $(this).attr('src',ifr_source+'&'+wmode);
      else $(this).attr('src',ifr_source+'?'+wmode);
	});	
	
})(jQuery); 