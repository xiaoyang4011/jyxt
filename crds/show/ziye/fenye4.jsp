<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>分页显示</title>
<script type="text/javascript" src="js/jquery1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	//how much items per page to show
	var show_per_page = 5; 
	//getting the amount of elements inside content div
	var number_of_items = $('#content').children().size();
	//calculate the number of pages we are going to have
	var number_of_pages = Math.ceil(number_of_items/show_per_page);
	
	//set the value of our hidden input fields
	$('#current_page').val(0);
	$('#show_per_page').val(show_per_page);
	
	//now when we got all we need for the navigation let's make it '
	
	/* 
	what are we going to have in the navigation?
		- link to previous page
		- links to specific pages
		- link to next page
	*/
	var navigation_html = '<a class="previous_link" href="javascript:previous();">Prev</a>';
	var current_link = 0;
	while(number_of_pages > current_link){
		navigation_html += '<a class="page_link" href="javascript:go_to_page(' + current_link +')" longdesc="' + current_link +'">'+ (current_link + 1) +'</a>';
		current_link++;
	}
	navigation_html += '<a class="next_link" href="javascript:next();">Next</a>';
	
	$('#page_navigation').html(navigation_html);
	
	//add active_page class to the first page link
	$('#page_navigation .page_link:first').addClass('active_page');
	
	//hide all the elements inside content div
	$('#content').children().css('display', 'none');
	
	//and show the first n (show_per_page) elements
	$('#content').children().slice(0, show_per_page).css('display', 'block');
	
});

function previous(){
	
	new_page = parseInt($('#current_page').val()) - 1;
	//if there is an item before the current active link run the function
	if($('.active_page').prev('.page_link').length==true){
		go_to_page(new_page);
	}
	
}

function next(){
	new_page = parseInt($('#current_page').val()) + 1;
	//if there is an item after the current active link run the function
	if($('.active_page').next('.page_link').length==true){
		go_to_page(new_page);
	}
	
}
function go_to_page(page_num){
	//get the number of items shown per page
	var show_per_page = parseInt($('#show_per_page').val());
	
	//get the element number where to start the slice from
	start_from = page_num * show_per_page;
	
	//get the element number where to end the slice
	end_on = start_from + show_per_page;
	
	//hide all children elements of content div, get specific items and show them
	$('#content').children().css('display', 'none').slice(start_from, end_on).css('display', 'block');
	
	/*get the page link that has longdesc attribute of the current page and add active_page class to it
	and remove that class from previously active page link*/
	$('.page_link[longdesc=' + page_num +']').addClass('active_page').siblings('.active_page').removeClass('active_page');
	
	//update the current page input field
	$('#current_page').val(page_num);
}
  
</script>
<style>
#page_navigation a{
	padding:3px;
	border:1px solid gray;
	margin:2px;
	color:black;
	text-decoration:none
}
.active_page{
	background:darkblue;
	color:white !important;
}
</style>
</head>
<body>
	
	<!-- the input fields that will hold the variables we will use -->
	<input type='hidden' id='current_page' />
	<input type='hidden' id='show_per_page' />
	
	<!-- Content div. The child elements will be used for paginating(they don't have to be all the same,
		you can use divs, paragraphs, spans, or whatever you like mixed together). '-->
	<div id='content'>
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
		<p>Vestibulum consectetur ipsum sit amet urna euismod imperdiet aliquam urna laoreet.</p>
		<p>Curabitur a ipsum ut elit porttitor egestas non vitae libero.</p>
		<p>Pellentesque ac sem ac sem tincidunt euismod.</p>
		<p>Duis hendrerit purus vitae nibh tincidunt bibendum.</p>
		<p>Nullam in nisi sit amet velit placerat laoreet.</p>
		<p>Vestibulum posuere ligula non dolor semper vel facilisis orci ultrices.</p>
		<p>Donec tincidunt lorem et dolor fringilla ut bibendum lacus fringilla.</p>
		<p>In non eros eu lacus vestibulum sodales.</p>
		<p>Duis ultrices metus sit amet sem adipiscing sit amet blandit orci convallis.</p>
		<p>Proin ullamcorper est vitae lorem mollis bibendum.</p>
		<p>Maecenas congue fringilla enim, tristique laoreet tortor adipiscing eget.</p>
		<p>Duis imperdiet metus et lorem venenatis nec porta libero porttitor.</p>
		<p>Maecenas lacinia lectus ac nulla commodo lacinia.</p>
		<p>Maecenas quis massa nisl, sed aliquet tortor.</p>
		<p>Quisque porttitor tellus ut ligula mattis luctus.</p>
		<p>In at mi dolor, at consectetur risus.</p>
		<p>Etiam id erat ut lorem fringilla dictum.</p>
		<p>Curabitur sagittis dolor ac nisi interdum sed posuere tellus commodo.</p>
		<p>Pellentesque quis magna vitae quam malesuada aliquet.</p>
		<p>Curabitur tempus tellus quis orci egestas condimentum.</p>
		<p>Maecenas laoreet eros ac orci adipiscing pharetra.</p>
		<p>Nunc non mauris eu nibh tincidunt iaculis.</p>
		<p>Ut semper leo lacinia purus hendrerit facilisis.</p>
		<p>Praesent et eros lacinia massa sollicitudin consequat.</p>
		<p>Proin non mauris in sem iaculis iaculis vel sed diam.</p>
		<p>Nunc quis quam pulvinar nibh volutpat aliquet eget in ante.</p>
		<p>In ultricies dui id libero pretium ullamcorper.</p>
		<p>Morbi laoreet metus vitae ipsum lobortis ultrices.</p>
		<p>Donec venenatis egestas arcu, quis eleifend erat tempus ullamcorper.</p>
		<p>Morbi nec leo non enim mollis adipiscing sed et dolor.</p>
		<p>Cras non tellus enim, vel mollis diam.</p>
		<p>Phasellus luctus quam id ligula commodo eu fringilla est cursus.</p>
		<p>Ut luctus augue tortor, in volutpat enim.</p>
		<p>Cras bibendum ante sed erat pharetra sodales.</p>
		<p>Donec sollicitudin enim eu mi suscipit luctus posuere eros imperdiet.</p>
		<p>Vestibulum mollis tortor quis ipsum suscipit in venenatis nulla fermentum.</p>
		<p>Proin vehicula suscipit felis, vitae facilisis nulla bibendum ac.</p>
		<p>Cras iaculis neque et orci suscipit id porta risus feugiat.</p>
		<p>Suspendisse eget tellus purus, ac pulvinar enim.</p>
		<p>Morbi hendrerit ultrices enim, ac rutrum felis commodo in.</p>
		<p>Suspendisse sagittis mattis sem, sit amet faucibus nisl fermentum vitae.</p>
		<p>Nulla sed purus et tellus convallis scelerisque.</p>
		<p>Nam at justo ut ante consectetur faucibus.</p>
		<p>Proin dapibus nisi a quam interdum lobortis.</p>
		<p>Nunc ornare nisi sed mi vehicula eu luctus mauris interdum.</p>
		<p>Mauris auctor suscipit tellus, at sodales nisi blandit sed.</p>

	</div>

	<!-- An empty div which will be populated using jQuery -->
	<div id='page_navigation'></div><br>
<br>

</body>
</html>