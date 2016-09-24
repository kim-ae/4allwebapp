var ORDERED_PRODUCT_LI = "<li data-quantity='1' data-id='{{PRODUCT_ID}}'>{{PRODUCT_NAME}}<span class='operation-icons'><i class='fa fa-minus-square'></i><span>1</span><i class='fa fa-plus-square'></i></span></li>"
function increment_quantity(element){
		$element = $(element)
		quantity = $element.data('quantity')
		new_quantity = quantity + 1
		$element.data('quantity', new_quantity)
		$element.find('.operation-icons span').html(new_quantity)
}
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
		dom.products.find('li').click(function(){
			$that = $(this)
			alreadyRegistered = $('.order-list').find('li').get().find(function(element){
				return $(element).data('id') === $that.data('id')
			})
			if(alreadyRegistered == undefined){
				$('.order-list').append(ORDERED_PRODUCT_LI.replace('{{PRODUCT_NAME}}',$(this).text())
																									.replace('{{PRODUCT_ID}}', $(this).data('id')));
			}else{
				increment_quantity(alreadyRegistered)
			}
		})
	});

	$('body').on('click', 'i.fa.fa-plus-square', function(){
			element = $(this).closest('li');
			increment_quantity(element);
	})

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
