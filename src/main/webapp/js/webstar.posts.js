var APP_THREAD;
var page1 = 0;
var page2 = 0;
var page3 = 0;
var page4= 0;
var page5=0;

var ThreadWidget = {
	settings : {
	    postContents : $("#post-contents"),
		postSubmit : $("#post_submit"),
		textAreaPost : $("#ta-post-1"),
		videoLinks : $("#videoLinks"),
		category : $("#cat_dd"),
		subcategory : $("#subcat_dd"),
		contentsDiv: $('#contents-div').find('.widget').find('.z-depth-2-top'),
		postComments :$('#post-comments'),
		postForm:$("#post_form"),
		contentsDivComment: $('.block-post-comments--style-1'),
		postRepost : $("#post-repost"),
		postRatings : $("#post-ratings")
		
	},

	init : function() {
		APP_THREAD = this.settings;
		this.bindUIActions();
    },
	
	buildCommentWindow: function(e){
	   ThreadWidget.buildDialog(APP_THREAD.postComments,e);
	},
	
	buildRatingWindow: function(e){
		   ThreadWidget.buildDialog(APP_THREAD.postRatings,e);
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
	
	buildRepostWindow: function(e){
		ThreadWidget.buildDialog(APP_THREAD.postRepost,e);
		 $.ajax({
				type : "GET",
				url : "/doRepost?postId="+event.target.id,
				success : function(data) {
			     $("#ta-repost-2").html(data.contents);
	              $("#repost-name").html(data.author +"'s post");
	              $("#repost_postid").val(data.postId);
				},
				error : function(e) { console.log("error:", e); }
			})
	},
	
	loadPostsByCategory1: function(event,page){
		//console.log(event);
		console.log("hello",event.path[0].innerText);
		 var req = '/bycategorypage?category='+event.path[0].innerText+"&offset=0";
	   	 window.location.href = req;
	},
	loadPostsByCategory: function(event,page){
		 var req = '/bycategory?category='+event.target.id+"&offset=0";
		   $.getJSON(req, function(result){
	    		APP_THREAD.contentsDiv.html('');  
	    	    $.each(result, function(i, field){
	            	ThreadWidget.buildAjaxUI(field);
	            });
	    	    $(".stars").stars();
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
		 	 $(".stars").stars();
            });
	},
	//pagination
	loadMorePostsByUser: function(id,page){
		next = page; 
		var req = '/byuserajax?uid='+id+'&offset='+next+"&repost=0";
		    $.getJSON(req, function(result){
		  	 $.each(result, function(i, field){
		  		ThreadWidget.buildAjaxUI(field);
	            });
		  	  $(".stars").stars();
	        });
		   
			 
	},
	//pagination
	loadMorePosts : function(page){
		   next = page;
		    $.getJSON("/loadMoreRecent?offset="+next, function(result){
		    $.each(result, function(i, field){
		    	ThreadWidget.buildAjaxUI(field);
            })
            $(".stars").stars();
		    
        });
    },
  //pagination
	loadMorePostsCommentsByPostId : function(postid,page){
		    next = page;
		    $.getJSON("/loadmorecomments?postid="+postid+"&offset="+next, function(result){
	         $.each(result, function(i, field){
            	ThreadWidget.buildCommentUI(field);
            });
	        $(".stars").stars();
			 
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
			  var _uri = ev.target.documentURI;
			  if(_uri.indexOf('byuser') > 0){
				  page5++;
				  page1,page2,page3,page4=0;
				  v = ThreadWidget.paramValue('uid');
				  //console.log(v+"  "+page5);
				  ThreadWidget.loadMorePostsByUser(v,page5);
			  }
			  else if(_uri.indexOf('?') > 0){
				  var v = ThreadWidget.paramValue('category');
				  if( v == "" || v == null)
				  {
		    		 page1++;
		    		 page2,page3,page4,page5=0;
		    		 v = ThreadWidget.paramValue('postid');
		    		 ThreadWidget.loadMorePostsCommentsByPostId(v,page1);
		    	  }
				  else{
		    	    page2++;
		    	    page1,page3,page4,page5=0;
		    	    ThreadWidget.loadMorePostsByCategory(v,page2);
		    	  }
		      }
			  else if(ev.target.activeElement.id !=''){
				   page3++;
				   page1,page2,page4,page5=0;
				   ThreadWidget.loadMorePostsByCategory(ev.target.activeElement.id,page3);
			  }
			  else{
				   page4++;
				   page1,page2,page3,page5 = 0;
				   ThreadWidget.loadMorePosts(page4);
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
			html =  html + '<p class="comment-text">'+field.comments+'</p>'; 
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
	     	APP_THREAD.contentsDivComment.append(html);            
   },
 
    buildAjaxUI: function(field){
		var dummy = 100;
		var html = '<div class="card-body" style="padding-left:0.5em;padding-top:0.5em;!important"><div class="block block-comment" style="margin-bottom: 0rem;!important"><div class="block-image"><img src="../img/prv/people/brin.jpg" class="img-square"></div>';
			html =   html + '<div class="block-body"><div class="block-body-inner"><h3 class="heading heading-6"><span class="stars" data-rating="'+  field.avgRatings  +'" data-num-stars="5" ></span>';
			    	      if(field.avgRatings > 0){  html = html +'<small> '+  field.avgRatings  +' / 5 avg rating</small><br />';   }else{  html = html +'<small> Not rated yet.</small><br/>';}
	        if(field.userDetails === undefined){
	  	      html = html+'<span style="color:#007aff"><a href="/byuser?uid='+field.uid+'&offset=0&repost=0">' + field.username+" - "+ field.firstName+" "+field.lastName + '</a></span><small>&nbsp;&nbsp;'+ field.timeLapse +'</small>';
	        }else{
	        	 html = html+'<span style="color:#007aff"><a href=/byuser?uid='+field.userDetails.id+'&offset=0&repost=0>' +  field.userDetails.username+" "+field.userDetails.firstName+" "+field.userDetails.lastName + '</a></span><small>&nbsp;&nbsp;'+ field.timeLapse +'</small>';
	        }
	        html = html + '<span style="float:right"><a href=/bycategorypage?category='+field.category+'&offset=0>' + field.category +'</a>';
	        html = html + '<a href=/bycategorypage?category='+field.category+'&offset=0">'+ "  "+field.subcategory +'</a></span></h3>';
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
	        html  = html+ '<div class="col-10"><ul class="inline-links inline-links--style-3" style="margin-left:-4%;">';
		    html = html+ '<li><a href="#"><i class="fa fa-heart"></i>'+ dummy +'</a></li>';
		    html = html+ '<li><a href="javascript:void(0)"><i class="fa fa-comment"  id='+field.id+' onclick=PageWidget.displayCommentWindow(event);></i></a></li>';     
		    html = html+ '<li><a href="#"><i class="fa fa-retweet"></i>'+ dummy +'</a></li>';
	        html = html+ '<li><a href="#"><i class="fa fa-envelope"></i></a></li>';
	        html = html+ '<li><a href="javascript:void(0)" onclick="PageWidget.displayRatingWindow(event)"><i class="fa fa-star-o fa-2" aria-hidden="true" id='+field.id+'></i></a></li>';
	        html = html+ '<li><a href="/getcomments?postid='+field.id+'">Show comments('+ field.totalComments+')</a> </li>';
		    html = html +'</ul></div></div>';
	    APP_THREAD.contentsDiv.append(html);
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
			closeText : '',
			position : {
				my : "center+50 top+50", of: e,
				within : $("body")
			},
			//closeOnEscape : false,
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
