//展示loading
function g_showLoading(){
	var idx = layer.msg('处理中...', {icon: 16,shade: [0.5, '#f5f5f5'],scrollbar: false,offset: '0px', time:100000}) ;  
	return idx;
}
//前端md5的salt 与后端MD5uUtil中的salt对应
var g_passsword_salt="1a2b3c4d"
