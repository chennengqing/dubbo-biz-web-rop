/**
  *  write by ftt
  *  time 2013.08.23
  */

// 错误提示
function pop_error(title,content,callback,hasCancel){
	var aTitle = title || '错误提示';
	var aContent = content || '错误提示文字';
	var aCancel = hasCancel && true;

    art.dialog({
		title: aTitle,
		icon: 'error',
		content: aContent,
		ok: callback,
		cancel: aCancel,
		lock: true
	});
}


// 成功提示
function pop_succeed(title, content, callback, hasCancel){
	var aTitle = title || '成功提示';
	var aContent = content || '成功提示文字';
	var aCancel = hasCancel && true;
	
    art.dialog({
		title: aTitle,
		icon: 'succeed',
		content: aContent,
		ok: callback,
		cancel: aCancel,
		lock: true
	});
}


// 问题提示
function pop_question(title,content,callback,hasCancel){
	var aTitle = title || '错误提示';
	var aContent = content || '错误提示文字';
	var aCancel = hasCancel && true;
	
    art.dialog.through({
		title: aTitle,
		icon: 'question',
		content: aContent,
		ok: callback,
		cancel: aCancel,
		lock: true
	});
}


// 警告提示
function pop_warning(title,content,hasCancel,callback){
	var aTitle = title || '警告提示';
	var aContent = content || '警告提示';
	var aCancel = hasCancel && true;

    art.dialog({
		title: aTitle,
		icon: 'warning',
		content: aContent,
		ok: callback || function(){},
		cancel: aCancel,
		lock: true
	});
}


// 伤心提示
function pop_faceSad(){
    art.dialog({
		title: '伤心提示',
		icon: 'face-sad',
		content: '伤心提示伤心提示伤心提示伤心提示<br>伤心提示',
		ok: function () {
			//art.dialog({content: '确定', lock: true});
			//return false;
		},
		cancel: true,
		lock: true
	});
}


// 微笑提示
function pop_faceSmile(){
    art.dialog({
		title: '微笑提示',
		icon: 'face-smile',
		content: '微笑提示微笑提示微笑提示微笑提示<br>微笑提示',
		ok: function () {
			//art.dialog({content: '确定', lock: true});
			//return false;
		},
		cancel: true,
		lock: true
	});
}

//提问
function pop_prompt(content, yes, value) {
	 value = value || '';
	 var input;
	 
	 artDialog.through({
		  id: 'Prompt',
		  icon: 'question',
		  fixed: true,
		  lock: true,
		  content: [
			   '<div style="margin-bottom:5px;font-size:12px">',
			    content,
			   '</div>',
			   '<div>',
			    '<input value="',
			     value,
			    '" style="width:18em;padding:6px 4px" />',
			   '</div>'
			   ].join(''),
		  init: function () {
			  input = this.DOM.content.find('input')[0];
			  input.select();
			  input.focus();
		  },
		  ok: function (here) {
			  return yes && yes.call(this, input.value, here);
		  },
		  cancel: true
	 });
}