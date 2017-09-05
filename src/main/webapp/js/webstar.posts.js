var APP_THREAD;
var page = 0;

var ThreadWidget = {
	settings : {
	    postContents : $("#post-contents"),
		postSubmit : $("#post_submit"),
		textAreaPost : $("#ta-post-1"),
		videoLinks : $("#videoLinks"),
		category : $("#cat_dd"),
		subcategory : $("#subcat_dd"),
		contentsDiv: $('#contents-div'),
		postComments :$('#post-comments')
		
	},

	init : function() {
		APP_THREAD = this.settings;
		this.bindUIActions();
    },
	
	buildCommentWindow: function(e){
		ThreadWidget.buildDialog(APP_THREAD.postComments,e);
	},
	
	buildPostWindow: function(e){
		ThreadWidget.buildDialog(APP_THREAD.postContents,e);
		$.ajax({
			type : "GET",
			url : "/categories",
			success : function(data) {
				APP_THREAD.category.empty();
				APP_THREAD.category.append($("<option></option>").attr("value", '').text('Please select'));
				$.each(data, function(value, key) {
					APP_THREAD.category.append($("<option></option>").attr("value", value).text(key));
				});
			},
			error : function(e) { console.log("error:", e); }
		})
	},
	
	loadPostsByCategory: function(event){
		 var req = '/bycategory?category='+event.target.id;
		 	var next = page * 5;
	    	var dummy = 50;
	    	   $.getJSON(req, function(result){
	    		   APP_THREAD.contentsDiv.find('.widget').find('.z-depth-2-top').html('');  
	            $.each(result, function(i, field){
	            	ThreadWidget.buildAjaxUI(field);
	            });
	        });
	},
	
	loadMorePosts : function(page){
		var BLOCKSIZE = 50;
		var next = page * BLOCKSIZE;
		   $.getJSON("/loadMoreRecent?blocksize="+BLOCKSIZE+"&offset="+next, function(result){
            $.each(result, function(i, field){
            	ThreadWidget.buildAjaxUI(field);
            });
        });
    },
	
	bindUIActions : function() {
		
		APP_THREAD.postSubmit.on("click", function() {
			ThreadWidget.submitPost();
		});
		APP_THREAD.category.on("change", function() {
			ThreadWidget.showSubCategories();
		});
		window.onscroll = function(ev) {
			 if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
			  page++;
		      ThreadWidget.loadMorePosts(page);
		    }
		};
	},
	
	showSubCategories : function() {
		var key = APP_THREAD.category.val();
		$.ajax({
			type : "GET",
			url : "/subcategories?category=" + key,
			success : function(data) {
				var items = data.split(",");
				APP_THREAD.subcategory.empty();
				APP_THREAD.subcategory.append($("<option></option>").attr("value", '').text('Please select'));
				for (var i = 0; i < items.length; i++) {
					APP_THREAD.subcategory.append($("<option></option>").attr("value", items[i]).text(items[i]));
				}
			},
			error : function(e) {
				console.log("error:", e);
			}
		})
	},
    
	
    buildAjaxUI: function(field){
		var dummy = 50;
		var html = '<div class="card-body"><medium><strong>'+ field.userDetails.firstName+" "+field.userDetails.lastName+ " ";
	    html = html +"</strong></medium>";
	    html = html+'<small>'+ field.timeLapse+'</small> <a href="#">'+field.category+'</a> <a href="#">'+field.subcategory+'</a><p class="mb-4">'+ field.contents+'</p>';
	    if(field.imageUrl!=null){
	        html = html+'<img src=../'+field.imageUrl+' style="width:100%; top: -0px;" />';
	    }    
	    if(field.videoUrl.startsWith('$$-')){
			  html = html +'<iframe width="100%" height="315" src="https://www.youtube.com/embed/'+field.videoUrl.split('-')[1]+'"></iframe>';
		  }   
		  if(field.videoUrl.startsWith('##-')){
			  html = html +'<iframe width="100%" height="315" src="https://player.vimeo.com/video/'+field.videoUrl.split('-')[1]+'"></iframe>';
		}    
       html = html+ '<hr /></div>';
       html  = html+ '<div class="col-8"><ul class="inline-links inline-links--style-3">';
	   html = html+ '<li><a href="#"><i class="fa fa-heart"></i>'+ dummy +'</a></li>';
	   html = html+ '<li><a href="#"><i class="fa fa-comment"></i>'+ dummy +'</a></li>';
	   html = html+ '<li><a href="#"><i class="fa fa-retweet"></i>'+ dummy +'</a></li>';
	   html = html+ '<li><a href="#"><i class="fa fa-envelope"></i></a></li>';
	   html = html +'</ul></div>';
	   APP_THREAD.contentsDiv.find('.widget').find('.z-depth-2-top').append(html);
	},
    
	validateVideoLinks : function(url) {
		var regU = /^(?:https?:\/\/)?(?:www\.)?(?:youtu\.be\/|youtube\.com\/(?:embed\/|v\/|watch\?v=|watch\?.+&v=))((\w|-){11})(?:\S+)?$/;
		var regv = /^(http\:\/\/|https\:\/\/)?(www\.)?(vimeo\.com\/)([0-9]+)$/;
		return (url.match(regU) || url.match(regv)) ? true /* RegExp.$*1 */: false;
	},

	submitPost : function() {
		var errorpost = $("#error_post");
		errorpost.html('');
		if (APP_THREAD.textAreaPost.val() == "") {
			errorpost.html('Post contents is required.').show();
			return;
		}
		if (APP_THREAD.category.val() == "" || APP_THREAD.subcategory.val() == "") {
			errorpost.html('Category and respective subcateogy is required.').show();
			return;
		}
		if (APP_THREAD.videoLinks.val() != "" && !ThreadWidget.validateVideoLinks(APP_THREAD.videoLinks.val())) {
			errorpost.append("Incorrect video url.").show();
			return;

		} else {
			$("#post_form").submit();
		}
	},
	
	buildDialog : function(div, e) {
		    
		div.dialog({ 
			modal : true,
			minHeight : 'auto',
			width : $(window).width() > 450 ? 700 : 350,
			resizable : true,
			autoOpen : true,
			position : {
				my : "center+150 top+37", of: e,
				within : $("body")
			},
			closeOnEscape : false,
			close : function(event, ui) {
				$("#error_post").hide();
			},
			buttons : {}

		});
		$(window).resize(function() {
			div.dialog({
				width : $(window).width() > 450 ? 700 : 350,
			});
		});
	}
}
