
function readUrl() {
	var $input = $(this);
	var $newInput = $(this).parent().parent().parent().find('.portimg');
     if (this.files && this.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			reset($newInput.next('.delbtn'), true);
			$newInput.attr('src', e.target.result).show();
			$newInput.after('<input type="button" class="delbtn removebtn close" value="X" style="margin-right:350px;">');
		}
		if(this.files[0].size > 1000000){
			$("#img-place").html("Image too big. Max is 1MB.");
			return;
		}
		reader.readAsDataURL(this.files[0]);
	}
}
$(".fileUpload").change(readUrl);

$("form").on('click', '.delbtn', function(e) {
	reset($(this));
});

function reset(elm, prserveFileName) {
	if (elm && elm.length > 0) {
		var $input = elm;
		$input.prev('.portimg').attr('src', '').hide();
		if (!prserveFileName) {
			$($input).parent().parent().parent().find('input.fileUpload ').val("");
		}
		elm.remove();
	}
}