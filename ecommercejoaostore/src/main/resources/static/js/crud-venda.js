function editVenda (url) {
	$.get(url, function(entity, status){
		$('#id').val(entity.id);
		$('#nf').val(entity.nf);
		$('#nota').val(entity.nota);
		$('#cliente').val(entity.cliente.id);
		$('#data').val( formatDate(entity.data) );
		if (entity.data)
			$('#data').val( formatDate(entity.data) );
	});
	$('#modal-form').modal();
}

function onCarrinhoClick(url){
	$.get(url, function(entity, status){
	$('#produto').val(entity.produto.id);
	$('#quantidade').val(entity.vendaproduto.quantidade);
	$('#valor').val(entity.vendaproduto.valor);
});
	$('#modal-form').modal();
}
function formatDate(inputFormat){
	function pad(s){
		return (s < 10) ? '0' + s : s;
	}
	var d = new Date(inputFormat);
	return [ pad(d.getDate()), pad(d.getMonth()+1), d.getFullYear() ].join('/');
}


function saveUpload(urlDestino){
	var formData = new FormData($('#frm')[0]);
	$.ajax({
		type: $('#frm').attr('method'),
		 url: $('#frm').attr('action'),
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function(){
			swal('Salvo!', 'Registro salvo com sucesso!', 'success');
			window.location = urlDestino;
		},
		error: function(){
			swal('Errou!', 'Falha ao salvar o registro!', 'error');
		}
	});//Fim Ajax
}