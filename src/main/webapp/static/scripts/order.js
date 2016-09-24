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
$(document).ready(function(){

		var dom = {
			products: $('.products'),
			search: $('.order-page__search')
		};

	$.ajax({
		url: dom.products.data('products-source'),
		method: 'GET'
	}).done(function(response){
		dom.products.html(response);
		dom.products.find('li').click(function(){
			var $that = $(this)
			var alreadyRegistered = $('.order-list').find('li').get().find(function(element){
				return $(element).data('id') === $that.data('id')
			})
			if(alreadyRegistered == undefined){
				$('.order-list').append($('.order-element-li').html().replace('{{PRODUCT_NAME}}',$that.text())
																														 .replace('{{PRODUCT_ID}}', $that.data('id')));
			}else{
				increment_quantity(alreadyRegistered)
			}
		})
	});

	$('body').on('click', 'i.fa.fa-plus-square', function(){
			var element = $(this).closest('li');
			increment_quantity(element);
	}).on('click', 'i.fa.fa-minus-square', function(e){
		var element = $(this).closest('li')
		e.preventDefault();
		$('#myModal').modal('toggle').one('click', '#confirm', function(){
			decrement_quantity(element)
		})
	})

	dom.search.keyup(function(){
			var $that = $(this);
			dom.products.find('li').each(function(element, value){
				var $element = $(value);
				var product_name = $element.text().toLowerCase();
				var input_text = $that.val().toLowerCase();
				$element.toggle(product_name.search(input_text) !== -1);
			})
	});
});
