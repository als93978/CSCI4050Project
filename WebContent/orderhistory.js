$(document).ready(function() {
    $(".reorder button").click(function() {
        $("#notification").toast("show");
    });

    document.getElementById("logoutBtn").onclick = function() {
        document.getElementById("logoutForm").submit();
    }
});