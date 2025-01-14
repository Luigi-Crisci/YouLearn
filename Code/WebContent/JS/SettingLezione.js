$("#bLez1").click(function(){

	$("#lezione2").removeClass("d-none");

});
$("#bLez2").click(function(){


	$("#lezione3").removeClass("d-none");

});

function moveUp(item) {
	var prev = item.prev();
	if (prev.length == 0) return;
	prev.css('z-index', 999).css('position', 'relative').animate({
		top: item.height()
	}, 250);
	item.css('z-index', 1000).css('position', 'relative').animate({
		top: '-' + prev.height()
	}, 300, function () {
		prev.css('z-index', '').css('top', '').css('position', '');
		item.css('z-index', '').css('top', '').css('position', '');
		item.insertBefore(prev);

		sendOrderToServer();
	});
}

function moveDown(item) {
	var next = item.next();
	if (next.length == 0) return;
	next.css('z-index', 999).css('position', 'relative').animate({
		top: '-' + item.height()
	}, 250);
	item.css('z-index', 1000).css('position', 'relative').animate({
		top: next.height()
	}, 300, function () {
		next.css('z-index', '').css('top', '').css('position', '');
		item.css('z-index', '').css('top', '').css('position', '');
		item.insertAfter(next);

		sendOrderToServer();
	});
}



$(".collection").sortable({
	items: ".item"
});


var str="";
var firstStr="";
var lastStr="";
var orderList = jQuery.grep($(".collection").sortable('toArray'), function (n, i) {
	firstStr= firstStr + n+"-"+(i+1) + ","; 

	return (n !== "" && n != null);
});


function sendOrderToServer() {
	lastStr="";
	var items = $(".collection").sortable('toArray');
	var itemList = jQuery.grep(items, function (n, i) {

		lastStr= lastStr+ n+"-"+(i+1) + ","; 

		return (n !== "" && n != null);
	});

	console.log("vecchio: "+firstStr+" nuovo:"+ lastStr);
	if(firstStr == lastStr) {
		$("#conferma").fadeOut();
		$("#conferma").removeClass("d-block");
		$("#conferma").addClass("d-none");
		$("#upload").removeAttr("disabled");
		$("#upload").removeAttr("disabled");
		$("#nome-lezione").attr("readonly", false);


	}else {
		$("#conferma").removeClass("d-none").fadeIn();
		$("#conferma").addClass("d-block");
		$("#upload").attr("disabled", "disabled");
		$("#nome-lezione").attr("readonly", true);

	}	
}


$('.b-up').click(function () {

	var btn = $(this);
	var val = btn.val();
	if (val == 'up') moveUp(btn.parents('.item'));
	else moveDown(btn.parents('.item'));


});



var getUrlParameter = function getUrlParameter(sParam) {
	var sPageURL = window.location.search.substring(1),
	sURLVariables = sPageURL.split('&'),
	sParameterName,
	i;

	for (i = 0; i < sURLVariables.length; i++) {
		sParameterName = sURLVariables[i].split('=');

		if (sParameterName[0] === sParam) {
			return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
		}
	}
};	






function UploadResult()
{
	console.log(lastStr);
	var coppie = lastStr.substring(0, lastStr.length-1);
	var idCorso= getUrlParameter("idCorso");
	console.log("idcorso: "+ idCorso)
	$.ajax({
		type: "POST",
		url: "http://localhost:8080/YouLearn/ModificaOrdineServlet",
		data:{ "coppie": coppie,
			"idCorso": idCorso	},

	});

	refreshPage();

}


var nomeLezione;
var path;
function modifica(form){


	//inizializzo le variabili
	var nome = form.nome;
	var btnModLezione = form.modLezione;
	$(btnModLezione).text("Modifica Lezione...");

	if(path == null){
		path = document.getElementById("file-lezione").innerHTML;
		console.log("Modifica con path=null: " +path);

	} else{

		console.log("Modifica con path!=null: " +path);}

	var file = form.nomeL;
	var btn = form.btnM;
	var btnA = form.btnA;
	var btnUp = form.up;
	var btnDown = form.down;
	var btnC = form.btnC;
	nomeLezione = $(nome).val();

	var li =$(btnA).parent();
	var ul = li.parent();
	var liText = ul.children("li").eq(0);
	console.log(liText);
	var liFile = ul.children("li").eq(1);
	console.log(liFile);


	//Diasbilito gli altri bottoni per la modifica
	disableButton($(btn).text(), btnUp, btnDown);


	//Rendo l'input text editabile
	$(btn).removeAttr("disabled");
	$(nome).attr("readonly", false);
	$(nome).focus();




	//Rendo visibili a schermo gli altri tasti COnferma, Annulla
	$(btn).addClass("d-none");
	$(btnA).removeClass("d-none");
	$(btnC).removeClass("d-none");
	$(btnModLezione).removeClass("d-none")
	$(btnModLezione).hide();
	$(btnModLezione).fadeIn();
}


function disableButton(x, up, down){


	if(x == "Conferma modifiche"){


	} else if($(".modifica").is(":disabled")){

		$(".modifica").removeAttr("disabled");
		$(".b").removeAttr("disabled");


	}else{

		$(".modifica").attr("disabled", "disabled");
		$(".b").attr("disabled", "disabled");
	}
}


function annulla(form){

	var nome = form.nome;
	var btn = form.btnM;
	var btnA = form.btnA;
	var btnC = form.btnC;
	var btnModLezione = form.modLezione;
	var fileLezione = form.nomeL;

	var li =$(btnA).parent();
	var ul = li.parent();
	var liText = ul.children("li").eq(0);
	console.log(liText);
	var liFile = ul.children("li").eq(1);
	console.log(liFile);
	var button = liFile.children("button");
	console.log(button);

	if($(btnModLezione).css("display") == "none"){

		$(liFile).text("");
		console.log("Annulla con bottone non visibile: " + path);
		$(liFile).append(path);

	} else {

		$(liFile).text("");
		console.log("Annulla con bottone visibile: " + path);

		$(btnModLezione).addClass("d-none");	
		$(liFile).append(path);

	}


	$(nome).val(nomeLezione+"");
	$(nome).attr("readonly", true);

	disableButton();
	$(btn).removeClass("d-none");
	$(btnC).addClass("d-none");
	$(btnA).addClass("d-none");



}

function modificaLezione(form){
	var btnA = form.btnA;

	var li =$(btnA).parent();
	var ul = li.parent();
	var liText = ul.children("li").eq(0);
	console.log(liText);
	var liFile = ul.children("li").eq(1);
	console.log(liFile);
	var text = liText.children("input[type=text]");
	var f = liFile.children("input[type=file]");


	console.log("ModificaLezione: " +path);
	$(liFile).text("");
	$(liFile).append("<input type='file' name='nomeL' id='videoUP' accept='video/mp4'>");

	var fileLezione = liFile.children("input[type=file]");
	console.log(fileLezione);
	$(fileLezione).removeClass("d-none");





}




function invioDatiLezione(form){

	var nome = form.nome;
	var btn = form.btnM;
	var btnA = form.btnA;
	var btnC = form.btnC;
	var btnModLezione = form.modLezione;
	var fileLezione = form.nomeL;
	var id = form.idL;

	var li =$(btnA).parent();
	var ul = li.parent();
	var liText = ul.children("li").eq(0);
	console.log(liText);
	var liFile = ul.children("li").eq(1);
	console.log(liFile);
	var text = liText.children("input[type=text]");
	var f = liFile.children("input[type=file]");
	console.log($(text).val());
	console.log($(id).val());


	if(!$(btnModLezione).length >0){
		console.log("Il bottone non esiste: " + $(btnModLezione));

		sendDataUpload(text, f, id);

	} else {

		console.log("Il bottone esiste: " + $(btnModLezione));

		updateNomeLezione($(text).val(), $(id).val(), form);

	}



}

function updateNomeLezione(nome, idL, form){

	var idCorso= getUrlParameter("idCorso");

	console.log("idCOrso: "+idCorso);
	console.log("Nome: " + nome + ", idLezione: " + idL);
	file ="";

	$.ajax({
		type: "POST",
		enctype : 'multipart/form-data',
		url: "http://localhost:8080/YouLearn/ModificaLezioneServlet",
		enctype :'multipart/form-data',
		data:{ "name": nome,
			"file": null,
			"idLezione": idL,
			"idCorso": idCorso	}
	});

	window.location.reload();



}




$(".UPL").click(function(){	


	var div=$(this).parent();
	var ul = div.parent();

	var liText = ul.children("li").eq(0);

	var liFile = ul.children("li").eq(1);




	var text = liText.children("input[type=text]");
	var f = liFile.children("input[type=file]");

	console.log("CIAOO: "+ liFile.children("input[type=file]"));
	console.log("Ecco il file:" + f.val());
	var divU = ul.parent();
	var divX = divU.parent();
	var divF = divX.parent();
	var up = divU.children().eq(1)
	var divI = divF.children().eq(1);//div input con id in form

	if(f==null){alert("file non modificato")}

	if(checkNome($(text).val()) == false){

		return false;

	}

	console.log("Questo è: "+idCorso);
	console.log("Questo è: "+$(text).val());

	var idCorso= getUrlParameter('idCorso');
	var idLezione = $(divI).val();
	var nome = $(text).val();
	console.log(idCorso);

	if(f.val()=="") {



		$.ajax({
			type: "POST",
			url: "http://localhost:8080/YouLearn/ModificaLezioneServlet",
			data:{ "name": nome,
				"idLezione": idLezione,
				"idCorso": idCorso,
				"tipo": "soloNome"
			},
			success: function(data){
				if(data.success == true){ // if true (1)

					location.reload(); // then reload the page.(3)
				}

			}
		});


		window.location.reload();
	}
	else{

		if(document.getElementById("videoUP").files[0].size >  524288000){
			console.log(document.getElementById("videoUP").files[0].size)
			alertify.error("Il file che vuoi caricare &egrave; troppo grande!\nGrandezza massima: 500MB.");
			return false;
		}

		f.simpleUpload("http://localhost:8080/YouLearn/ModificaLezioneServlet?name="+$(text).val()+"&idCorso="+ idCorso+"&idLezione="+$(divI).val(), {

			allowedExts: ["mp4"],
			allowedTypes: ["video/mp4"],
			maxFileSize: 500000000, //500 MB in bytes

			start: function(file){
				//Modificare nome file con file.name=qualcosa

				this.block = $('<div class="block"></div>');
				this.progressBar = $('<div class="progressBar"></div>');
				this.cancelButton = $('<div class="cancelButton">x</div>');

				/*
				 * Since "this" differs depending on the function in which it is called,
				 * we need to assign "this" to a local variable to be able to access
				 * this.upload.cancel() inside another function call.
				 */

				var that = this;

				this.cancelButton.click(function(){
					that.upload.cancel();
					//now, the cancel callback will be called
				});

				this.block.append(this.progressBar).append(this.cancelButton);
				up.append(this.block);

			},

			progress: function(progress){
				//received progress
				this.progressBar.width(progress + "%");
			},

			success: function(data){
				//upload successful

				this.progressBar.remove();
				this.cancelButton.remove();

				if (data.success) {
					//now fill the block with the format of the uploaded file

					var format = data.format;
					var formatDiv = $('<div class="format"></div>').text(format);
					this.block.append(formatDiv);
				} else {
					//our application returned an error
					//var error = data.error.message;
					var errorDiv = $('<div class="error"></div>').text("Upload risucito!");	
					this.block.append(errorDiv);
				}

			},
			error: function(error){
				//upload failed
				this.progressBar.remove();
				this.cancelButton.remove();
				var error = error.message;
				var errorDiv = $('<div class="error"></div>').text("Upload riuscito!");
				this.block.append(errorDiv);
			},

			cancel: function(){
				//upload cancelled
				this.block.fadeOut(400, function(){
					$(this).remove();
				});
			},
			finish: function(){

				window.location.reload();

			}
		});
	}



});

function refreshPage(){
	console.log("prova");
	window.location.reload();
}


function checkNome(nome){
	var nameValidator = /^[a-zA-Z\s\!\-\d]{5,50}$/;

	if(nome == ""){

		return false;
	} else

		if(!nome.match(nameValidator)){
			return false;

		} else {

			return true;
		}

}



