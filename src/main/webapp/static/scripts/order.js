$(document).ready(function(){
		dom = {
			products: $('.products'),
			search: $('.order-page__search')
		};

	$.ajax({
		url: dom.products.data('products-source'),
		method: 'GET'
	}).done(function(response){
		dom.products.html(response);
	});

	dom.search.keyup(function(){
			$that = $(this);
			dom.products.find('li').each(function(element, value){
				$element = $(value);
				product_name = $element.text().toLowerCase();
				input_text = $that.val().toLowerCase();
				$element.toggle(product_name.search(input_text) !== -1);
			})
	});
});
