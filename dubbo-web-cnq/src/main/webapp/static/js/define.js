$(function(){
	$('#set').mouseover(function() {
		$(this).find('.setting').show();
	});
	$('.setting').mouseout(function() {
		$(this).hide();
	});
	$('#header').mouseleave(function() {
		$('.setting').hide();
	});
	$('.setting').delegate('li', 'mouseover', function() {
		$(this).addClass('on').siblings().removeClass('on');
	});
})


function DateDiff(dtStart,dtEnd,strInterval) {   
    if (typeof dtStart == 'string' )//如果是字符串转换为日期型  
    {   
    	dtStart = StringToDate(dtStart);  
        dtEnd = StringToDate(dtEnd);  
    }  
    switch (strInterval) {   
        case 's' :return parseFloat((dtEnd - dtStart) / 1000);  
        case 'n' :return parseFloat((dtEnd - dtStart) / 60000);  
        case 'h' :return parseFloat((dtEnd - dtStart) / 3600000);  
        case 'd' :return parseFloat((dtEnd - dtStart) / 86400000);  
        case 'w' :return parseFloat((dtEnd - dtStart) / (86400000 * 7));  
        case 'm' :return (dtEnd.getMonth()+1)+((dtEnd.getFullYear()-dtStart.getFullYear())*12) - (dtStart.getMonth()+1);  
        case 'y' :return dtEnd.getFullYear() - dtStart.getFullYear();  
    }  
}  

function StringToDate(DateStr)  
{   
  
    var converted = Date.parse(DateStr);  
    var myDate = new Date(converted);  
    if (isNaN(myDate))  
    {   
        //var delimCahar = DateStr.indexOf('/')!=-1?'/':'-';  
        var arys= DateStr.split('-');  
        myDate = new Date(arys[0],--arys[1],arys[2]);  
    }  
    return myDate;  
} 

function modify_user(j) {
	var f = $.trim($("#birthday").val());
	//var d = parseFloat($.trim($("#month").val()));
	//var g = parseFloat($.trim($("#day").val()));
	var b = $.trim($("#weight").val());
	var k = $.trim($("#height").val());
	var e = ($(".component_slider").attr("data-state") == "left") ? "1" : "2";
	var a = /^\d+(\.\d+)?$/;

	var a1 = $.trim($("#customerid").val());
	var a2 = $.trim($("#customermemberid").val());
	var a3 = $.trim($("#customerextid").val());
	var nickname = $.trim($("#nick_name").val());

	/*
	if (!f || !d || !g || isNaN(f) || isNaN(d) || isNaN(g) || f < 1900  || d <= 0
			|| g <= 0 || d > 12 || g > 31) {
		$("#birthdayNotice").html(I18N.account_birthday_error);
		return false;
	} else {
		if (f < 10) {
			f = f + "0";
		}
		if (d < 10) {
			d = "0" + d;
		}
		if (g < 10) {
			g = "0" + g;
		}
		$("#birthdayNotice").html("");
	}
	*/
	if (!b || !k || !a.test(b) || !a.test(k)) {
		$("#healthNotice").html(I18N.account_helthinfo_error);
		return false
	} else {
		$("#healthNotice").html("");
	}

	if (b < 10 || b > 280 || k < 1 || k > 500) {
		$("#healthNotice").html(I18N.account_helthinfo_error);
		return false;
	} else {
		$("#healthNotice").html("");
	}
	
	b = parseFloat(b).toFixed(1);
	k = parseFloat(k).toFixed(1);

	//var h = f + "-" + d + "-" + g;
	var h = f;
	var j = j ? j : modify_register_callback;
	UserdUtil.modify(e, h, k, b, a1, a2, a3,nickname,j);
}

function modify_callback(b) {
	if (b == 1) {
		var a = $("#setting_user_notice");
		a.show();
		objFindOut(a, 1);
	} else {
		alert(I18N.common_system_error);
	}
}

var UserdUtil = {
	data : null,
	devices : null,
	modify : function(e, d, a, g, a1, a2, a3,nickname, h) {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "website/user/user!saveUserInfo.action",
			data : {
				'reg.sex' : e,
				'reg.birthDay' : d,
				'reg.weight' : g,
				'reg.height' : a,
				'reg.nick_name' : nickname,
				customerid : a1,
				customermemberid : a2,
				customerextid : a3
			},
			success : h
		});
	},
	_modify_cb : function(b, c) {
		if (Util._checkMsg(b) && b.userID) {
			var a = {
				res : "0"
			};
			Util._sendMsg("", "1", "", base + "/health/ufresh", null);
			this.data = b;
			if (Sportsd) {
				Sportsd.renewGoal(b.userHealthInfo.exerciseGoal,
						b.userHealthInfo.exerciseGoalType)
			}
			c(a);
		} else {
			var a = b.resultCode ? {
				res : b.resultCode,
				desc : b.resultDesc
			} : null;
			c(a);
		}
	},

	modPasswd : function(a, f,e, d) {
		var b = {
			password : a,
			password_old : f
		};
		Util._sendMsg(d, b, e);
	},

	bind : function(e, c, f, a, d) {
		var b = {
			account : e,
			type : c,
			customerextid : f
		};

		Util._sendMsg(d, b, a);
	},

	statDay : function(a, b, c, d) {
		var e = {
			member_gid : a,
			begindate : b
		};

		Util._sendMsg(c, e, d);
	}
};

var Util = {
	_checkMsg : function(a) {
		if (a && a.resultCode == "w100") {
			document.location.href = base + "/health/logout"
		} else {
			if (!a || !a.resultCode || a.resultCode == "0") {
				return true
			}
		}
		return false;
	},
	
	_writeHtml: function(doc, html) {
//		doc.designMode = 'On';
		doc.contentEditable = true;
		doc.open();
		doc.write(html);
		doc.close();
	},

	_sendMsg : function(a, h, l, t) {
		$.ajax({
			type : "post",
			dataType : t || "JSON",
			url : a,
			data : h,
			complete : null,
			success : l
		});
	},

	_modPasswd_cb : function(b, c) {
		if (BDUtil._checkMsg(b)) {
			var a = {
				res : "0"
			};
			c(a)
		} else {
			var a = b.resultCode ? {
				res : b.resultCode,
				desc : b.resultDesc
			} : null;
			c(a)
		}
	},
	_open_url: function(win, url, params, isPost) {
		if(!isPost) {
			win.location.href = url + params;
		} else {
			var local = this;
			local._sendMsg(url, params, function(result){
				local._writeHtml(win.document, result);
			}, "html");
		}
	}
};

function tab_setting() {
	var obj = $(this);
	var data = obj.attr('data');
	if (data == 'setting_safe') {
		if (!$('.tab_setting_item').is(':visible')) {
			obj.find('li').each(function() {
				if ($(this).hasClass('on')) {
					$(this).trigger('click');
				}
			});
		}
	} else {
		$('.tab_setting_item').hide('fast');
		$('.setting_box[data="' + data + '"]').show().siblings().hide();
		if(data == 'setting_step_detail'){
			querystepdate();
			
		}else if(data == 'setting_step_stat'){
			
			initStepData();
		}
		else if(data == 'setting_device'){
			
			queryplan();
			
		}else if(data == 'setting_heartrate'){
			
			initHeartData();
			
		}else if(data == 'setting_heartrate_data'){
			queryheartrate();
		}
		else if(data == 'setting_sleep_detail'){
			querysleep_detail();	
			
			
		}else if(data == 'setting_sleep_chart'){
			
			initSleepData()
		}
	}
	obj.addClass('on').siblings().removeClass('on');
}

function showSex() {
	if ((Userd && Userd.data && Userd.data.sex && Userd.data.sex == "2")) {
		$(".component_slider").trigger("click")
	}

	if ((Userd && Userd.data && Userd.data.username)) {
		$("#_account_").html(Userd.data.username);
		$("#nick_name").val(Userd.data.name);
		$(".username").html("Hi,<strong>" + Userd.data.username + "</strong>");
	}

}

function showBirthday() {
	var a = (Userd && Userd.data && Userd.data.sex && Userd.data.birthday) ? Userd.data.birthday
			: "1980-01-01";
	
	$("#birthday").val(a);

}

function showHeightWeight() {
	var c = (Userd && Userd.data && Userd.data.height) ? Userd.data.height
			: null;
	var d = (Userd && Userd.data && Userd.data.weight) ? Userd.data.weight
			: null;

	var a = c ? c : "165";
	var b = d ? parseFloat(d).toFixed(1) : "65";
	$("#height").val(a);
	$("#weight").val(b);
}

function setPrimary() {
	var a = (Userd && Userd.data && Userd.data.customerid) ? Userd.data.customerid
			: 0;
	var b = (Userd && Userd.data && Userd.data.customermemberid) ? Userd.data.customermemberid
			: 0;
	var c = (Userd && Userd.data && Userd.data.customerextid) ? Userd.data.customerextid
			: 0;
	$("#customerid").val(a);
	$("#customermemberid").val(b);
	$("#customerextid").val(c);
}

function showHeadPic() {
	var a = (Userd && Userd.data && Userd.data.userEX && Userd.data.userEX.headPicUrl) ? Userd.data.userEX.headPicUrl
			: base + "/images/website/user/X0_0009_avatar_boy_210.jpg";
	$("#headPic").attr("src", a);
}

function checkPwd(obj, a) {
	var obj = $(obj);
	var p = $.trim(obj.val());
	var id = obj.attr('id');
	var str = '密码不能为空';
	if (id == 'newPassword') {
		str = '新' + str;
	}
	if (!p) {
		obj.next().addClass('error').text(str);
		return false;
	} else if (p.length < 6 || p.length > 16) {
		obj.next().addClass('error').text('请输入6～16位由数字或字母组合的字符，字母区分大小写');
		return false;
	}

	if (a) {
		var m = $("#oldPassword_").val();
		if (m == p) {
			return false;
		}
		var b = {
			password_old : p
		};

		Util._sendMsg(base+"/website/user/user!passwordExist.action?rand=" + new Date(), b,
				checkPwd_callback);
		$("#oldPassword_").val(p);
	}

	if (!a) {
		obj.next().removeClass('error').text('');
		return true;
	}

}

function checkPwd_callback(a) {
	if (a == 1) {
		$("#oldPassword").next().addClass('error').text('');
		
	} else {
		$("#oldPassword").next().addClass('error').text('密码错误');
		
	}
}

function checkPwdCon(obj) {
	// checkPwd.call(this);
	var el = $(obj);
	var pc = $.trim(el.val());
	if (!pc) {
		el.next().addClass('error').text('确认密码不能为空');
		return false;
	} else if (el.val() !== $('#newPassword').val()) {
		el.next().addClass('error').text('两次输入的密码不一致');
		return false;
	} else {
		el.next().removeClass('error').text('');
		return true;
	}
}

function objFindOut(b, a) {
	b.show();
	var c = setInterval(function() {
		if (a <= 0) {
			clearInterval(c);
			b.fadeOut("slow");
		} else {
			a--;
		}
	}, 600);
}

function isEmpty(a) {
	switch (typeof (a)) {
	case "string":
		return $.trim(a).length == 0 ? true : false;
		break;
	case "number":
		return a == 0;
		break;
	case "object":
		return a == null;
		break;
	case "array":
		return a.length == 0;
		break;
	default:
		return true
	}
}
function isNumber(b) {
	var a = /^([0-9]{1,})(\.[0-9]{1,2}){0,1}$/;
	return a.test(b)
}
function isInt(b) {
	if (b == "") {
		return false
	}
	var a = /^[0-9]+[0-9]*$/;
	return a.test(b);
}
function isEmail(a) {
	var b = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
	return b.test(a)
}
function isMobile(a) {
	var b = /^0?1(3|5|8)\d{9}$/;
	return b.test(a)
}
function isURL(a) {
	var b = /^(https|http)?:\/\/.+$/;
	return b.test(a)
}

function roundFun(numberRound, roundDigit) // 四舍五入，保留位数为roundDigit
{
	if (numberRound >= 0) {
		var tempNumber = parseInt((numberRound * Math.pow(10, roundDigit) + 0.5))
				/ Math.pow(10, roundDigit);
		return tempNumber;
	} else {
		numberRound1 = -numberRound
		var tempNumber = parseInt((numberRound1 * Math.pow(10, roundDigit) + 0.5))
				/ Math.pow(10, roundDigit);
		return -tempNumber;
	}
}

function writeNav(t,p) {
	var i, navhtml;
	
	if(p == 1){
		navhtml = "<a class='jp-previous jp-disabled' href='javaScript:search("+(p-1)+");'>上一页</a>" ;
	}else{
		navhtml = "<a class='jp-previous' href='javaScript:search("+(p-1)+");'>上一页</a>" ;
	}
	
	for (i = 1; i <= t; i++) {
		if (i>1 && t>7 && (p-i>2 || i-p >2 ) && i != t)
  			navhtml += "<a href='#' class='jp-hidden'>";
		else
			if(p == i){
				navhtml += "<a class='jp-current' href='javaScript:search("+i+");'>";
			}else{
				navhtml += "<a href='javaScript:search("+i+");'>";
			}

		navhtml += i;
		navhtml += "</a>";
		if(i == 1 && t > 7 && p - 3 > 1){
			navhtml += "<span>...</span>";
		}
	
		if(i == t - 1 && t > 7 && t - p > 3){
			navhtml += "<span>...</span>";
		}
	}
	
	if(p == t){
		navhtml += "<a class='jp-next jp-disabled' href='javaScript:search("+(p+1)+");'>下一页</a>";
	}else{
		navhtml += "<a class='jp-next' href='javaScript:search("+(p+1)+");'>下一页</a>";
	}
	
	return navhtml;
}


