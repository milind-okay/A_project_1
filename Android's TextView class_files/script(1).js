//Top Menu -------------------------------------------
jQuery(document).ready(function() {
	jQuery('.mn-top-menu-trigger').on('click', function(){
		
		if( jQuery('.mn-primary-nav').hasClass('is-visible') ) {
			jQuery('.mn-primary-nav').removeClass('is-visible').one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend',function(){
				jQuery('body').removeClass('overflow-hidden');
			});
		} else {
			jQuery('.mn-primary-nav').addClass('is-visible').one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend',function(){
				jQuery('body').addClass('overflow-hidden');
			});	
		}
	});
    
	jQuery(window).scroll(function(){
		if (jQuery(this).scrollTop() > 60) {
			jQuery('.scroll-top').css({bottom:"25px"});
		} else {
			jQuery('.scroll-top').css({bottom:"-100px"});
		}
	});
	jQuery('.scroll-top').click(function(){
		jQuery('html, body').animate({scrollTop: '0px'}, 800);
		return false;
	});
    
});

jQuery(document).ready(function() {
	
	jQuery('a[data-toggle="tab"]').on('shown.bs.tab', function (e) 
	{	
		var $wall = jQuery('.mn-tabs .mnwall_iso_container').imagesLoaded( function() 
		{
			$wall.isotope('layout');	
		});
		
	});
		
});
