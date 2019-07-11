function editCliente(url) {
	$.get(url, function(entity, status){
		$('#id').val(entity.id);
		$('#nome').val(entity.nome);
		$('#cpf').val(entity.cpf);
		$('#rua').val(entity.rua);
		$('#bairro').val(entity.bairro);
		$('#cep').val(entity.cep);
		$('#complemento').val(entity.complemento);
		$('#referencia').val(entity.referencia);
		$('#cidade').val(entity.cidade.id);
		
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

