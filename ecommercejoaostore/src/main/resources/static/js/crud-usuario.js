function editUsuario(url) {
	$.get(url, function(entity, status){
		$('#id').val(entity.id);
		$('#username').val(entity.username);
		$('#senha').val(entity.senha);
		$('#permissao').val(entity.permissao.id);
		
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

