/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function save() {
    //disbale redirection behaviour which redirects pages
    $("#itemFormData").submit(function () {
        alert("we are doing fine");
        //return false;
    });

    //make an ajax post to the server
    $.post($("#itemFormData").attr("action"), $("#itemFormData :input").serializeArray(), function (serverInfo) {
        $(".result").html(serverInfo);
    });

    //clear form details after post
    $("#itemFormData :input").each(function () {
        $(this).val("");
    });
}

function replenish() {
    //disbale redirection behaviour which redirects pages
    $("#replenishFormData").submit(function () {
        return false;
    });

    //make an ajax post to the server
    $.post($("#replenishFormData").attr("action"), $("#replenishFormData :input").serializeArray(), function (serverInfo) {
        $(".result").html(serverInfo);
    });

    //clear form details after post
    $("#replenishFormData :input").each(function () {
        $(this).val("");
    });
}

function checkout() {
    $("#checkoutFormData").submit(function () {
        return false;
    });

    //make an ajax post to the server
    $.post($("#checkoutFormData").attr("action"), $("#checkoutFormData :input").serializeArray(), function (serverInfo) {
        $(".result").html(serverInfo);
    });

    //clear form details after post
    $("#checkoutFormData :input").each(function () {
        $(this).val("");
    });

}



