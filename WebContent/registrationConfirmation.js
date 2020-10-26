$(function () {
    $('[data-toggle="popover"]').popover()
});

$(document).ready(function() {
    $('#resend').on('shown.bs.popover', function() {
        setTimeout(function() {
            $('#resend').popover('hide');
        }, 2000);
    });
});