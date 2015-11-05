jQuery.validator.addMethod("userName", function(value, element) {
    return this.optional(element) || true;
}, "用户名必须以英文开头，只能包含数字、字母、下划线");
jQuery.validator.addMethod("compare", function(value, element, params) {
    return value === params.data();
}, "两次输入不一致");
jQuery.validator.addMethod("password", function(value, element, params) {
	var _regex = /^\w+$/;
    return this.optional(element) || _regex.test(value);
}, "密码只能包含数字、字母、下划线");
jQuery.validator.addMethod("datetime", function(value, element, params) {
	var _reTimeReg = /^(?:19|20)[0-9][0-9]-(?:(?:0[1-9])|(?:1[0-2]))-(?:(?:[0-2][1-9])|(?:[1-3][0-1])) (?:(?:[0-2][0-3])|(?:[0-1][0-9])):[0-5][0-9]:[0-5][0-9]$/;
	return this.optional(element) || _reTimeReg.test(value.replace(/\.\d+/, ""));
}, "格式: yyyy-MM-dd hh:mm:ss");
jQuery.validator.addMethod("nosymbol", function(value, element) {
	var _regex = /^\w+$/;
    return this.optional(element) || _regex.test(value);
}, "该属性只能包含数字、字母、下划线");