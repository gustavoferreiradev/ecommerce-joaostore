function editProduto(url) {
	$.get(url, function(entity, status){
		$('#id').val(entity.id);
		$('#nome').val(entity.nome);
		$('#descricao').val(entity.descricao);
		$('#resumo').val(entity.resumo);
		$('#categoria').val(entity.categoria.id);
		$('#marca').val(entity.marca.id);
		
		
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