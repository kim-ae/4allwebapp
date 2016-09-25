var ALERT_ELEMENT = '.order-page__alert';
function increment_quantity(element){
    var $element = $(element)
    var quantity = $element.data('quantity')
    quantity = quantity + 1
    $element.data('quantity', quantity)
    $element.find('.operation-icons span').html(quantity)
}

function decrement_quantity(element){
    var $element = $(element)
    var quantity = $element.data('quantity')
    quantity = quantity - 1
    if(quantity === 0){
        $element.remove()
    }else{
        $element.data('quantity', quantity)
        $element.find('.operation-icons span').html(quantity)
    }
}

function getOrderItens(){
    list = $('.order-list li').get().map(function(obj){
        return {
            preco: $(obj).data('price'),
            quantidade: $(obj).data('quantity'),
            sku: $(obj).data('id')
        }
    })
    return list;
}

function addItem($element){
    var alreadyRegistered = $('.order-list').find('li').get().find(function(element){
        return $(element).data('id') === $element.data('id')
    })
    if(alreadyRegistered == undefined){
        $('.order-list').append($('.order-element-li').html().replace('{{PRODUCT_NAME}}',$element.text())
                                                             .replace('{{PRODUCT_ID}}', $element.data('id'))
                                                             .replace('{{PRODUCT_PRICE}}', $element.data('price')));
    }else{
        increment_quantity(alreadyRegistered)
    }
}

$(document).ready(function(){
    var dom = {
        products: $('.products'),
        search: $('.order-page__search'),
        body: $('body'),
        modal: $('#myModal'),
        requestButton: $('.request-order'),
        orderList: $('.order-list')
    };

    $.ajax({
        url: dom.products.data('products-source'),
        method: 'GET'
    }).done(function(response){
        dom.products.html(response);
        dom.products.find('li').click(function(){
            var $that = $(this)
            addItem($that)
        })
    });

    dom.body.on('click', 'i.fa.fa-plus-square', function(){
        var element = $(this).closest('li');
        increment_quantity(element);
    }).on('click', 'i.fa.fa-minus-square', function(e){
        var element = $(this).closest('li')
        e.preventDefault();
        dom.modal.modal('toggle').one('click', '#confirm', function(){
            decrement_quantity(element)
        })
    })
    dom.requestButton.click(function(){
        var itens = getOrderItens();
        if(itens == undefined || itens.length === 0){
            new Alert("Por favor selecione pelo menos 1 item.", ALERT_ELEMENT);
        }else{
            var $that = $(this)
            $.ajax({
                url: dom.orderList.data('action'),
                contentType: "application/json; charset=utf-8",
                method: dom.orderList.data('method'),
                data: JSON.stringify(itens)
            }).done(function(response){
                 window.location.href = $that.data('redirect-url') + "/" + response;
            }).fail(function(response){
                new Alert(response.responseText, ALERT_ELEMENT);
            })
        }
    })

    dom.search.keyup(function(e){
        if(e.keyCode == 13){
            var visibleElement = dom.products.find('li:visible')
            if(visibleElement.length === 1){
                addItem($(visibleElement));
            }
        }
        var $that = $(this);
        dom.products.find('li').each(function(element, value){
            var $element = $(value);
            var product_name = $element.text().toLowerCase();
            var input_text = $that.val().toLowerCase();
            $element.toggle(product_name.search(input_text) !== -1);
        })
    });
});
