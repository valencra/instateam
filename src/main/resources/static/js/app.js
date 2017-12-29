$(function(){
    // Activate close icons for dismissible alerts
    $('[data-close]').on('click',function(){
        $(this).parent().remove();
    });
});