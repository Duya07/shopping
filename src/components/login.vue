<template>
	<div id="login">
		<form>
			用户名：<input type="text" id="name"><br>
			密码：<input type="password" id="password"><br>
			<button type="button" id="loginin">登录</button>
			<button type="button">注册</button>
		</form>
	</div>
</template>

<script>
	import $ from 'jquery'
	$(document).ready(function () {
		$('#login').click(function(){
			console.log("aaa");
			// $.ajax({
			// 	type: "GET",
			// 	url: "test.json",
			// 	data: {username:$("#username").val(), content:$("#content").val()},
			// 	dataType: "json",
			// 	success: function(data){
			// 		$('#resText').empty();   //清空resText里面的所有内容
			// 		var html = '';
			// 		$.each(data, function(commentIndex, comment){
			// 			html += '<div class="comment"><h6>' + comment['username']
			// 					+ ':</h6><p class="para"' + comment['content']
			// 					+ '</p></div>';
			// 		});
			// 		$('#resText').html(html);
			// 	}
			// });
		});
	})
	export default {
		name: 'login',
	}

</script>
