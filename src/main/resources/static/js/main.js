/*------------------------------------------------------------------
* Project:        Realvilla - Modern Real Estate & Property HTML Templates
* Author:         HtmlDesignTemplates
* URL:            https://themeforest.net/user/htmldesigntemplates
* Created:        03/05/2025
-------------------------------------------------------------------*/

(function ($) {
  "use strict";

  $(document).ready(function () {

      /* For Popup Search Start */
        $('a[href="#search1"]').on('click', function(event) {
          event.preventDefault();
          $('#search1').addClass('open');
          $('#search1 input').focus(); // Focus on the input field
        });

        $('#search1, #search1 button.close').on('click keyup', function(event) {
          if (event.target == this || $(event.target).hasClass('close') || event.keyCode == 27) {
            $('#search1').removeClass('open');
          }
        });


      /* SlickNav Responsive Menu */
        $('#responsive-menu').slicknav({
            duration: 500,
            easingOpen: 'easeInExpo',
            easingClose: 'easeOutExpo',
            closedSymbol: '<i class="arrow-indicator fa fa-angle-down"></i>',
            openedSymbol: '<i class="arrow-indicator fa fa-angle-up"></i>',
            prependTo: '#slicknav-mobile',
            allowParentLinks: true,
            label: ""
        });


      /* Sticky Header */
        $(document).ready(() => {
          let $headerMenu = $('.header-nav-menu');
      
          $(window).on('scroll', function () {
              let curScroll = $(window).scrollTop();
      
              if (curScroll > 130) {
                  $headerMenu.addClass('navbar-sticky-in');
              } else {
                  $headerMenu.removeClass('navbar-sticky-in');
              }
          });
      });
    

      /* Dropdown Menu */
        var selected = $('#navbar li');
        selected.on("mouseenter", function () {
            $(this).find('ul').first().stop(true, true).delay(350).slideDown(500, 'easeInOutQuad');
        }).on("mouseleave", function () {
            $(this).find('ul').first().stop(true, true).delay(100).slideUp(150, 'easeInOutQuad');
        });

        /* Arrow Indicator for Submenus */
        if ($(window).width() > 992) {
            $(".navbar-arrow ul ul > li").has("ul").children("a").append("<i class='arrow-indicator fa fa-angle-right'></i>");
        }

      
      /* Back-to-top js Start */
        $(document).ready(() => {
          $('#back-to-top').hide(); // Hide button initially
      
          $(window).on('scroll', () => {
              if ($(window).scrollTop() > 500) {
                  $('#back-to-top').fadeIn(200);
              } else {
                  $('#back-to-top').fadeOut(200);
              }
          });
      
          $(document).on('click', '#back-to-top, .back-to-top', () => {
              $('html, body').animate({
                  scrollTop: 0
              }, 500);
              return false;
          });
        });
    

      /* Slick Sliders */
        $('.banner-slider').slick({
          slidesToShow: 1,
          slidesToScroll: 1,
          autoplay: true,
          autoplaySpeed: 2500,
          arrows: false,
          dots: false,
          draggable:false,
          swipe: false,
          touchMove: false
        });

        $('.partner-slider').slick({
          slidesToShow: 6,
          slidesToScroll: 1,
          autoplay: true,
          autoplaySpeed: 2500,
          arrows: false,
          dots: false,
          responsive: [
            { breakpoint: 1100, settings: { slidesToShow: 4, slidesToScroll: 1 } },
            { breakpoint: 600, settings: { slidesToShow: 2, slidesToScroll: 1 } },
            { breakpoint: 480, settings: { slidesToShow: 1, slidesToScroll: 1 } }
        ]
        });

        $('.review-slider').slick({
          slidesToShow: 1,
          slidesToScroll: 1,
          autoplay: true,
          autoplaySpeed: 2500,
          arrows: false,
          dots: false,
        });


      /*//For Spinning letter animation start//*/
        const listItems = document.querySelectorAll('.spin');
        listItems.forEach((item, index) => {
          item.style.animationDelay = `${index * 0.1}s`;
        });


      /* Counter JS */
        document.querySelectorAll(".num").forEach(valueDisplay => {
          let startValue = 0;
          let endValue = parseInt(valueDisplay.getAttribute("data-val"), 10);
          let duration = Math.max(Math.floor(2000 / endValue), 1);
          let counter = setInterval(() => {
              valueDisplay.textContent = ++startValue;
              if (startValue === endValue) clearInterval(counter);
          }, duration);
        });


      /* Countdown Timer */
        const targetTime = new Date(Date.now() + 100 * 24 * 60 * 60 * 1000); // 100 days from now
        function updateCountdown() {
          const now = Date.now();
          const diff = targetTime - now;
          if (diff <= 0) {
            clearInterval(interval);
            $('#days, #hours, #minutes, #seconds').text('00');
            return;
          }
          
          const d = Math.floor(diff / (1000 * 60 * 60 * 24));
          const h = Math.floor((diff / (1000 * 60 * 60)) % 24);
          const m = Math.floor((diff / (1000 * 60)) % 60);
          const s = Math.floor((diff / 1000) % 60);
    
          $('#days').text(d);
          $('#hours').text(h.toString().padStart(2, '0'));
          $('#minutes').text(m.toString().padStart(2, '0'));
          $('#seconds').text(s.toString().padStart(2, '0'));
        }
    
        updateCountdown();
        const interval = setInterval(updateCountdown, 1000);

        
})})(jQuery);

