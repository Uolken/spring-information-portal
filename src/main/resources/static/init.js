   function init(){
        $(".postDate").each(function(){
            var diff = Date.now() - new Date($(this).find(".timeAgo").text());
            diff = Math.floor(diff / 1000);
            if (diff < 60){
                $(this).find(".timeAgo").text(diff + " Секунд");
                return;
            }
            diff = Math.floor(diff / 60);
            if (diff < 60){
                $(this).find(".timeAgo").text(diff + " Минут");
                return;
            }
            diff = Math.floor(diff / 60);
            if (diff < 60){
                $(this).find(".timeAgo").text(diff + " Часов");
                return;
            }
            diff = Math.floor(diff / 24);
            $(this).find(".timeAgo").text(diff + " Дней");

        })
    }