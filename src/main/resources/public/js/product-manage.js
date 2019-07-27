;

$('div').on('click', 'a.remove-size-btn', function() {
    $(this).parent("div").parent("div").remove();
    var counter = 0;
    $("#productItemArea > div").each(function (i) {
        $(this).find("div > input").each(function () {
            var originalValue = $(this).attr("name");
            var split = originalValue.split(/\d+/g);
            $(this).attr("name", split[0] + counter + split[1]);
        });
        counter++;
    });
});

$('#add-sizes-btn').click(function () {
    var index = $("#productItemArea > div:last > div:first > input:last").attr("name");
    if (index === undefined) {
        index = 0;
    } else {
        index = parseInt(index.match(/\d+/g).map(Number), 10) + 1;
    }
    var content = $("<div class=\"form-row\">" +
        "<div class=\"form-group col-md-4\">" +
        "<label>Наименование</label>" +
        "<input type=\"text\" class=\"form-control\" name=\"productItemDtos[" + index + "].name\" placeholder=\"\">" +
        "</div>" +
        "<div class=\"form-group col-md-7\">" +
        "<label>Размеры</label>" +
        "<input type=\"text\" class=\"form-control\" name=\"productItemDtos[" + index + "].sizes\" placeholder=\"\">" +
        "</div>" +
        "<div class=\"form-group col-md-1\">" +
        "<a class=\"align-items-center text-muted btn remove-size-btn\">" +
        "<span data-feather=\"minus-circle\"></span>" +
        "</a>" +
        "</div>" +
        "</div>");

    $('#productItemArea').append(content);
    feather.replace();
});