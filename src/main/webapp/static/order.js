$(document).ready(function(){
	$.ajax({
		url: $('.products').data('products-source'),
		method: 'GET'
	}).done(function(response){
		$('.products').html(response);
	});
});