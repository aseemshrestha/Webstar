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
		postComments :$('#post-comments'),
		postForm:$("#post_form"),
		contentsDivcomment: $('#contents-div-comment'),
		
		
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
	
	loadPostsByCategory: function(event,page){
		 var req = '/bycategory?category='+event.target.id+"&offset=0";
		    $.getJSON(req, function(result){
	    		APP_THREAD.contentsDiv.find('.widget').find('.z-depth-2-top').html('');  
	            $.each(result, function(i, field){
	            	ThreadWidget.buildAjaxUI(field);
	            });
	        });
	},
	
	//pagination
	loadMorePostsByCategory: function(cat,page){
		next = page; 
		var req = '/bycategory?category='+cat+'&offset='+next;
		    $.getJSON(req, function(result){
	    		 $.each(result, function(i, field){
	            	ThreadWidget.buildAjaxUI(field);
	            });
	        });
	},
	//pagination
	loadMorePosts : function(page){
		   next = page;
	        $.getJSON("/loadMoreRecent?offset="+next, function(result){
		    $.each(result, function(i, field){
            	ThreadWidget.buildAjaxUI(field);
            });
        });
    },
  //pagination
	loadMorePostsCommentsByPostId : function(postid,page){
		    next = page;
	        $.getJSON("/loadmorecomments?postid="+postid+"&offset="+next, function(result){
	        console.log("result",result);
		    $.each(result, function(i, field){
            	ThreadWidget.buildCommentUI(field);
            });
        });
    },
    paramValue : function(name) {
        return (location.search.split(name + '=')[1] || '').split('&')[0];
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
			  var _uri = ev.target.documentURI;
			  if(_uri.indexOf('?') > 0){
		    	  var v = ThreadWidget.paramValue('category');
		    	  if( v == "" || v == null){
		    		  v = ThreadWidget.paramValue('postid');
		    		  ThreadWidget.loadMorePostsCommentsByPostId(v,page);
		    	  }else{
		    	  ThreadWidget.loadMorePostsByCategory(v,page);
		    	  }
		      }
		      else if(ev.target.activeElement.id==''){
		          ThreadWidget.loadMorePosts(page);
			  }else{
				  ThreadWidget.loadMorePostsByCategory(event.target.activeElement.id,page);
			  }
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
    
	buildCommentUI : function(field){
		var html = '<div class="card-body"><li><div class="block block-comment"><div class="block-image"><img src="../img/prv/people/brin.jpg" class="img-square"></div>';
		    html =  html + '<div class="block-body"><div class="block-body-inner"><h3 class="heading heading-6">'+ field.commentedByName+ '</h3><span class="comment-date">'+ field.timeLapse +'</span>';
			html =  html + '<p class="comment-text">'+field.comments+'</p><br />'; 
			
			if(field.imageUrl!=null){
			  html = html+'<img src=../'+field.imageUrl+' style="width:100%; top: -0px;" />';
			}    
			if(field.videoUrl.startsWith('$$-')){
			  html = html +'<iframe width="100%" height="315" src="https://www.youtube.com/embed/'+field.videoUrl.split('-')[1]+'"></iframe>';
			}   
			if(field.videoUrl.startsWith('##-')){
			  html = html +'<iframe width="100%" height="315" src="https://player.vimeo.com/video/'+field.videoUrl.split('-')[1]+'"></iframe>';
			}     
			html = html+ '</div></div></div></li><hr />';
	    
			$('.block-post-comments--style-1').append(html);            
   },
	
    buildAjaxUI: function(field){
		var dummy = 50;
		var html = '<div class="card-body" style="padding-left:0.5em;padding-top:0.5em;!important">'+
		            '<div class="block block-comment" style="margin-bottom: 0rem;!important">'+
		            '<div class="block-image">'+
		            '<img src="../img/prv/people/brin.jpg" class="img-square"></div>';
		
		    html =   html + '<div class="block-body">'+
		    	     '<div class="block-body-inner">'  +
		             '<h3 class="heading heading-6">'+ field.userDetails.firstName+" "+ field.userDetails.lastName+ '<small>&nbsp;&nbsp;'+ field.timeLapse +'</small>';
		 
		    html = html + '<span style="float:right"><a href="/bycategorypage?category='+field.category+'&offset=0">' + field.category +'</a> <a href="/bycategorypage?category='+field.category+'&offset=0">'+ "  "+field.subcategory +'</a></span></h3>';
		    html = html + '<p class="mb-4" style="margin-bottom:0px;!important">'+ field.contents+'</p>';

		    if(field.imageUrl!=null){
	          html = html+'<img src=../'+field.imageUrl+' style="width:100%; top: -0px;" />';
	        }    
	       
		    if(field.videoUrl.startsWith('$$-')){
			  html = html +'<iframe width="100%" height="315" src="https://www.youtube.com/embed/'+field.videoUrl.split('-')[1]+'"></iframe>';
		    }   
		    
		    if(field.videoUrl.startsWith('##-')){
			  html = html +'<iframe width="100%" height="315" src="https://player.vimeo.com/video/'+field.videoUrl.split('-')[1]+'"></iframe>';
		    }     
           html = html+ '</div>';
           html  = html+ '<div class="col-10"><ul class="inline-links inline-links--style-3" style="margin-left:-4%;">';
    	   html = html+ '<li><a href="#"><i class="fa fa-heart"></i>'+ dummy +'</a></li>';
	       html = html+ '<li><a href="#"><i class="fa fa-comment"></i>'+ dummy +'</a></li>';
	       html = html+ '<li><a href="#"><i class="fa fa-retweet"></i>'+ dummy +'</a></li>';
	       html = html+ '<li><a href="#"><i class="fa fa-envelope"></i></a></li>';
	       html = html+ '<li><a href="#"><i class="fa fa-share" aria-hidden="true"></i></li>';
	       html = html+ '<li><a href="/getcomments?postid='+field.id+'">Show comments(10)</a> </li>';
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
		if (APP_THREAD.category.val() == "" || APP_THREAD.subcategory.val() == "" || APP_THREAD.subcategory.val()==null) {
			errorpost.html('Category and respective subcateogy is required.').show();
			return;
		}
		if (APP_THREAD.videoLinks.val() != "" && !ThreadWidget.validateVideoLinks(APP_THREAD.videoLinks.val())) {
			errorpost.append("Incorrect video url.").show();
			return;

		} else {
		  APP_THREAD.postForm.submit();
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
				my : "center+50 top+50", of: e,
				within : $("body")
			},
			closeOnEscape : false,
			close : function(event, ui) {
				$("#error_post").hide();
				$("#show_comment").hide();
				//$("#displayComment").html();
				
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
