$(document).ready(function() {
    $(".suspended").click(function() {
        $('#user-suspended').modal('show');
    });

    $(".unsuspended").click(function() {
        $('#user-unsuspended').modal('show');
    });

    $(".promoted").click(function() {
        $('#employee-promoted').modal('show');
    });

    $(".demoted").click(function() {
        $('#admin-demoted').modal('show');
    });
});