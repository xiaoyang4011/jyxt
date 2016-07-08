jQuery(document).ready(function ($) {

        $("#slider3").responsiveSlides({
                auto: true,
                pager: true,
                nav: true,
                timeout: 6000,
                speed: 800
        });
		
        $("#slider2").responsiveSlides({
                auto: true,
                pager: false,
                nav: true,
                timeout: 6000,
                speed: 800
        });

        $('a.rslides_nav.prev, a.rslides_nav.next').fadeTo('fast', 0);
        $('a.rslides_nav.prev, a.rslides_nav.next').hover(

        function () {
                $(this).stop(true, true).fadeTo('slow', 1);
        },

        function () {
                $(this).stop(true, true).fadeTo('slow', 0);
        });


});