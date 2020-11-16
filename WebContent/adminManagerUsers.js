$(document).ready(function() {
    $(".user-row").click(function() {
        $('#user-ban').modal('show');
    });

    $("#banned").click(function() {
        $('#user-banned').modal('show');
        $('#user-ban').modal('hide');
    });

    $("#unbanned").click(function() {
        $('#user-unbanned').modal('show');
        $('#user-ban').modal('hide');
    });

    $(".employee-row").click(function() {
        $('#employee-promote').modal('show');
    });

    $("#promoted").click(function() {
        $('#employee-promoted').modal('show');
        $('#employee-promote').modal('hide');
    });

    $("#demoted").click(function() {
        $('#employee-demoted').modal('show');
        $('#employee-promote').modal('hide');
    });
});