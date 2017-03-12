$(document).ready(function() {
    $.ajax({
        url: "http://localhost:9002/room-type?id=1"
    }).then(function(data, status, jqxhr) {
        $('.accommodation').append(data.name);
        $('.description').append(data.description);
        $('.max_guest').append(data.maximum_guest);
        $('.price').append(data.price);
        console.log(jqxhr);
    });
});