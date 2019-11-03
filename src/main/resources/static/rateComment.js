   $(".rateComment").click(function(){
       var xhr = new XMLHttpRequest();
       xhr.open('POST', '/comment/rate/' + $(this).data("id"));
       xhr.setRequestHeader('Content-Type',
       'application/x-www-form-urlencoded');
       var rate = $('.comment[data-id='+$(this).data("id")+']').find(".rate");

       var str = xhr.send('');
       if ($(this).hasClass("rated")){
           $(this).removeClass("rated");
           $(this).addClass("unrated");
           rate.text(parseInt(rate.text())-1);
       }else{
           $(this).removeClass("unrated");
           $(this).addClass("rated");
           rate.text(parseInt(rate.text())+1);
       }
   });