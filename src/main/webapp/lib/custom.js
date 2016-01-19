$(function () {

	// ラジオボタンの選択と同時にtableの行選択
/*	 $('body').on('click',"input[type='radio']",function(e){
		 $(this).prop("checked", true);
		if($(this).is(":checked")){
			$("label.radio-lbl").removeClass("checked");
		    $(this).parent().addClass("checked");

			$("tr").removeClass("selected");
			$(this).closest("tr").addClass("selected");

		}else{
			$(this).parent().removeClass("checked");
			$(this).closest("tr").removeClass("selected");
		}
	});*/


	 // Custom js for Grid after jGrid loaded.
	$(document).ajaxComplete(function(){
			// Move Pager of jGrid out of Scroll
		    $("#pager").detach().insertAfter('.table_scroll');

		    // Custom sortable for jGrid
		    $('.ui-jqgrid-sortable').click(function(){
		    	$('.s-ico').hide();
		    	$(this).children('.s-ico').css({"display" : "block" });
		    });




		   // Custom Scroll
		   if($('#custom-scroll-y').length){
		    	$(this).remove();
		   }else{
			   var element = '<div id="custom-scroll-y"><div id="content-for-scroll"></div></div>';
			   $('.table_scroll').append(element);
			   $('#content-for-scroll').height($('#result').height());
		   }

		   if($('#custom-scroll-y').length){
			   var target = $(".table_scroll #gview_result .ui-jqgrid-bdiv:last-child > div");
			   target.height($('.table_scroll').height()- 47);

	           $("#custom-scroll-y").scroll(function() {
	             target.prop("scrollTop", this.scrollTop) .prop("scrollLeft", this.scrollLeft);
	           });

	           target.scroll(function() {
	            $("#custom-scroll-y").prop("scrollTop", this.scrollTop) .prop("scrollLeft", this.scrollLeft);
	           });
		   }

		   $( ".ui-pg-selbox" ).change(function() {
			   $('#content-for-scroll').height($('#result').height());
		   });




		   $('#btn-cancel-ie, .btn-popup-stop-ie').click(function(e) {
				if($('#overlay-ie').length){
					$('#overlay-ie').remove();
					$(window).trigger('resize');
					 $('aside').css("border-bottom", "solid 1px transparent");
					    setTimeout(function()
					    {
					        $('aside').css("border-bottom", "solid 0px transparent");
					    }, 0);

					   $('#pager').css("border-bottom", "solid 1px transparent");
					    setTimeout(function()
							    {
							        $('#pager').css("border-bottom", "solid 0px transparent");
							    }, 0);
					    $('.table_scroll').css("border-bottom", "solid 1px transparent");
					    setTimeout(function()
							    {
							        $('.table_scroll').css("border-bottom", "solid 0px transparent");
							    }, 0);

				}
			});

	});
});
