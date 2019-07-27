;

function getCount(key) {
    return parseInt(document.getElementById('product-count-' + key).textContent, 10);
}

function setCount(key, newCount) {
    if (newCount !== undefined && !isNaN(newCount)) {
        document.getElementById('product-count-' + key).textContent = newCount;
    }
}

function plusItem(key) {
    $.ajax({
        url: '/order/add-one-more-to-cart',
        type: 'POST',
        data: key,
        dataType: 'text',
        contentType: 'text/plain',
        success: function (price) {
            $('#overall-product-price').html(price);
            setOverallPrice(price);
            setCount(key, getCount(key) + 1);
        }
    });
}

function minusItem(key) {
    var count = getCount(key);
    if (count - 1 === 0) {
        removeItem(key);
        return;
    }
    $.ajax({
        url: '/order/remove-one-from-cart',
        type: 'POST',
        data: key,
        dataType: 'text',
        contentType: 'text/plain',
        success: function (price) {
            $('#overall-product-price').html(price);
            setOverallPrice(price);
            setCount(key, count - 1);
        }
    });
}

function removeItem(key) {
    $.ajax({
        url: '/order/remove-product-cart',
        type: 'POST',
        data: key,
        dataType: 'text',
        contentType: 'text/plain',
        success: function (price) {
            $('#overall-product-price').html(price);
            setOverallPrice(price);
            document.getElementById('product-entry-' + key).remove();
        }
    });
}

function setOverallPrice(price) {
    var floatPrice = parseFloat(price);
    if (floatPrice > parseFloat('0')) {
        $('#overall-price').html(floatPrice + getDeliveryPrice());
    } else {
        $('#overall-price').html(price);
    }
}

 function getDeliveryPrice() {
     return parseInt($('#deliveryCodPrice').text());
 }

function updateConfidentialityState() {
    $('#main-form-submit-btn').prop('disabled', !$('#confidentiality').is(':checked'));
}

$('form').on('click', 'button#product-order-form-submit', function() {
    var form = $('#product-order-form');
    $.ajax({
        url: form.attr('action'),
        type: form.attr('method'),
        data: form.serialize(),
        success: function (newPrice) {
            var productPriceElement = $('#shopping-cart-btn');
            var oldPrice = productPriceElement.attr('data-original-title');
            if (oldPrice.indexOf(newPrice) < 0) {
                productPriceElement.attr('data-original-title', newPrice + ' BYN');
                var productCountElement = $('#shopping-cart-product-count');
                var currentCount = parseInt(productCountElement.text(), 10);
                productCountElement.html(currentCount + 1);
                if (currentCount === 0) {
                    productPriceElement.show();
                }
            }
        }
    });
});

var options = [];

$('#filter-btn').click(function () {
    var url = window.location.href;
    var firstCutIdx = url.indexOf('sizes=');
    var cutUrlIdx = firstCutIdx > -1 ? firstCutIdx : url.indexOf('sort=');

    if (cutUrlIdx > -1) {
        url = url.substring(0, cutUrlIdx - 1);
    }

    url += url.indexOf('?') > -1 ? '&' : '?';
    if (options.length > 0) {
        url += 'sizes=' + options.join() + '&';
    }
    url += 'sort=price,' + ($('#priceAsc').is(':checked') ? 'ASC' : 'DESC');

    window.location.href = url;
});

$('.dropdown-menu a').on('click', function (event) {
    var $target = $(event.currentTarget),
        val = $target.attr('data-value'),
        $inp = $target.find('input'),
        idx;

    if ((idx = options.indexOf(val)) > -1) {
        options.splice(idx, 1);
        setTimeout(function () {
            $inp.prop('checked', false)
        }, 0);
    } else {
        options.push(val);
        setTimeout(function () {
            $inp.prop('checked', true)
        }, 0);
    }

    $(event.target).blur();

    return false;
});