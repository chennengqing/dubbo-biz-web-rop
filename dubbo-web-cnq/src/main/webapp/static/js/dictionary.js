(function($){
	var cybClient = new Cyb.client();
	/**
	 * @public 
	 * @param data 配置信息
	 * @description 汽车品牌类型数据字典
	 */
	$.fn.carserial = function(data){
		var _defaults = {
			brand:{
				url:"/platformCarbrand/find.shtml",
				target:$(this),
				textField:"brand",
				valueField:"bid",
				emptyOption:"请选择",
				value:$(this).val(),
				param:{},
				data:{}
			},
			carseries:{
				url:"/platformCarseries/find.shtml",
				target:"#seriesId",
				textField:"carseries",
				valueField:"csid",
				emptyOption:"请选择",
				value:"",
				param:{},
				data:{}
			}
		};
		
		var brandConfig = data && data.brand ? data.brand : _defaults.brand;
		var carseriesConfig = data && data.carseries ? data.carseries : _defaults.carseries;
		
		_defaults.brand = $.extend({},_defaults.brand,brandConfig);
		_defaults.carseries = $.extend({},_defaults.carseries,carseriesConfig);
		loadingBrand(_defaults);
	};
	
	/**
	 * @public
	 * @param _defaults 配置信息
	 * @description 加载汽车品牌数据
	 */
	function loadingBrand(_defaults){
		var data = _defaults.brand;
		cybClient.ajax({
			url:cybClient.fullPath(data.url),
			data:data.param,
			success:function(result){
				if( result == undefined ){
					return;
				}
				if( result.result == 'success'){
					data.data = result.resultText;
					fill(data);
					loadingCarseries(_defaults);
					$(data["target"]).change(function(){
						loadingCarseries(_defaults);
					});
				}
			}
		});
	}
	
	/**
	 * @public
	 * @param _defaults 配置信息
	 * @description 加载汽车类型
	 */
	function loadingCarseries(_defaults){
		var data = _defaults.carseries;
		var brandConfig = _defaults.brand;
		if($(brandConfig.target).val() == "" && brandConfig.value == ""){
			emptySelect(data);//当车牌选回默认值，也要让车系赋空
			return;
		}
		cybClient.ajax({
			url:cybClient.fullPath(data.url),
			data:{"brandId":$(brandConfig.target).val()},
			success:function(result){
				if( result == undefined ){
					return;
				}
				if( result.result == 'success'){
					data.data = result.resultText;
					fill(data);
				}
			}
		});
	}
	
	/**
	 * @public
	 * @param defaults 配置信息
	 * @description 加载汽车类型
	 */
	$.fn.series = function(defaults){
		var data = $.extend({},{
			url:cybClient.fullPath("/platformCarseries/find.shtml"),
			data:null,
			textField:"carseries",
			valueField:"csid",
			emptyOption:"请选择",
			value:"",
			target:$(this),
			param:{}
		},defaults);
		
		$(this).empty();
		cybClient.ajax({
			url:data.url,
			data:data.param,
			success:function(result){
				if( result == undefined ){
					return;
				}
				if( result.result == 'success'){
					data.data = result.resultText;
					fill(data);
				}
			}
		});
	};
	/**
	 * @public
	 * @param data 配置信息
	 * @description 加载汽车排量
	 */
	$.fn.displacement = function(data){
		var _defaults = {
						url:"/platformConfiguration/findDisplacementByCartype.shtml",
						target:$(this),
						textField:"confValue",
						valueField:"confId",
						emptyOption:"请选择",
						value:$(this).val(),
						param:{"cartypeId":0},
						data:{}
					};
		data = $.extend({},_defaults,data);
		cybClient.ajax({
			url:cybClient.fullPath(data.url),
			data:data.param,
			success:function(result){
				if( result == undefined ){
					return;
				}
				if( result.result == 'success'){
					data.data = result.resultText;
					fill(data);
				}
			}
		});
	};
	
	/**
	 * 加载车型
	 */
	$.fn.cartype = function(data){
		var _defaults = {
						url:"/platformCartype/find.shtml",
						target:$(this),
						textField:"carTypeName",
						valueField:"carTypeId",
						emptyOption:"请选择",
						value:$(this).val(),
						param:{},
						data:{}
					};
		data = $.extend({},_defaults,data);
		//加载车型，必须传车辆品牌和车系的ID
		if(data.param.carBrandId == '' || data.param.carSeriesId == ''){
			return;
		}
		cybClient.ajax({
			url:cybClient.fullPath(data.url),
			data:data.param,
			success:function(result){
				if( result == undefined ){
					return;
				}
				if( result.result == 'success'){
					data.data = result.resultText;
					fill(data);
				}
			}
		});
	};
	
	/**
	 * @public
	 * @description 加载设备类型
	 */
	$.fn.deviceType = function(data){
		var defaults = {
				url:"/platformConfiguration/getDeviceTypeList.shtml",
				textField:"confValue",
				valueField:"confId",
				target:$(this),
				emptyOption:"请选择",
				value:"",
				param:{},
				data:{}
		};
		data = $.extend({},defaults,data);
		cybClient.ajax({
			url:cybClient.fullPath(data.url),
			data:data.param,
			success:function(result){
				if( result == undefined ){
					return;
				}
				if( result.result == 'success'){
					data.data = result.resultText;
					fill(data);
				}
			}
		});
	};
	
	/**
	 * @public
	 * @description 加载设备类型
	 */
	$.fn.device = function(data){
		var defaults = {
				url:"/glsxCommonDevice/list.shtml",
				textField:"deviceName",
				valueField:"deviceId",
				target:$(this),
				emptyOption:"请选择",
				value:"",
				param:{},
				data:{}
		};
		data = $.extend({},defaults,data);
		cybClient.ajax({
			url:cybClient.fullPath(data.url),
			data:data.param,
			success:function(result){
				if( result == undefined ){
					return;
				}
				if( result.result == 'success'){
					data.data = result.resultText;
					fill(data);
				}
			}
		});
	};
	
	
	$.fn.region = function(data){
		var _defaults = {
				province :{
					url:"/platformProvince/find.shtml",
					textField:"province",
					valueField:"pid",
					target:$(this),
					emptyOption:"省",
					value:"",
					param:{},
					data:{}
				},
				city :{
					url:"/platformCity/find.shtml",
					textField:"city",
					valueField:"cid",
					target:"#cityId",
					emptyOption:"市",
					value:"",
					param:{},
					data:{}
				},
				area : {
					url:"/platformArea/find.shtml",
					textField:"area",
					valueField:"aid",
					target:"#areaId",
					emptyOption:"区/县",
					value:"",
					param:{},
					data:{}
				}
		};
		
		var provinceConfig = data && data.province ? data.province : _defaults.province;
		var cityConfig = data && data.city ? data.city : _defaults.city;
		var areaConfig = data && data.area ? data.area : _defaults.area;
		
		_defaults.province = $.extend({},_defaults.province,provinceConfig);
		_defaults.city = $.extend({},_defaults.city,cityConfig);
		_defaults.area = $.extend({},_defaults.area,areaConfig);
		loadingProvince(_defaults);
	};
	/**
	 * @public
	 * @param _defaults 配置信息
	 * @description 加载省市信息
	 */
	function loadingProvince(_defaults){
		var _provinceConfig = _defaults.province;
		cybClient.ajax({
			url:cybClient.fullPath(_provinceConfig.url),
			data:_provinceConfig.param,
			success:function(result){
				if( result == undefined ){
					return;
				}
				if( result.result == 'success'){
					_provinceConfig.data = result.resultText;
					fill(_provinceConfig);
					
					loadingCity(_defaults);
					$(_provinceConfig["target"]).change(function(){
						_defaults["city"].param = {"father":$(this).val()};
						_defaults["city"].value = "";
						loadingCity(_defaults);
					});
				}
			}
		});
	}
	/**
	 * @public
	 * @param _defaults 配置信息
	 * @description 加载城市信息
	 */
	function loadingCity(_defaults){
		var _cityConfig = _defaults.city;
		var _target = _cityConfig.target;
		if( $(_target).length == 0 || jQuery.isEmptyObject(_cityConfig["param"])) 
			return;
		
		cybClient.ajax({
			url:cybClient.fullPath(_cityConfig.url),
			data:_cityConfig.param,
			success:function(result){
				if( result == undefined ){
					return;
				}
				if( result.result == 'success'){
					_cityConfig.data = result.resultText;
					fill(_cityConfig);
					
					loadingArea(_defaults);
					$(_cityConfig["target"]).change(function(){
						_defaults["area"].param = {"father":$(this).val()};
						loadingArea(_defaults);
					});
				}
			}
		});
	}
	/**
	 * @public
	 * @param _defaults 配置信息
	 * @description 加载地域信息
	 */
	function loadingArea(_defaults){
		var _areaConfig = _defaults.area;
		
		var _target = _areaConfig.target;
		if( $(_target).length == 0 || jQuery.isEmptyObject(_areaConfig["param"])) 
			return;
		
		cybClient.ajax({
			url:cybClient.fullPath(_areaConfig.url),
			data:_areaConfig.param,
			success:function(result){
				if( result == undefined ){
					return;
				}
				if( result.result == 'success'){
					_areaConfig.data = result.resultText;
					fill(_areaConfig);
				}
			}
		});
	}
	
	/**
	 * @public
	 * @description 加载商户救援类型
	 */
	$.fn.boxMerchantsSuccorType = function(data){
		var defaults = {
				url:"/boxMerchantsSuccorType/getMerSuccType.shtml",
				textField:"succorName",
				valueField:"id",
				target:$(this),
				emptyOption:"全部",
				value:"",
				param:{},
				data:{}
		};
		data = $.extend({},defaults,data);
		cybClient.ajax({
			url:cybClient.fullPath(data.url),
			data:data.param,
			success:function(result){
				if( result == undefined ){
					return;
				}
				if( result.result == 'success'){
					data.data = result.resultText;
					fill(data);
				}
			}
		});
	};
	
	/**
	 * @public
	 * @description 加载处理结果
	 */
	$.fn.boxDealResult = function(data){
		var defaults = {
				url:"/boxCauseSetting/getBoxCauseSettingSelect.shtml",
				textField:"content",
				valueField:"causeId",
				target:$(this),
				emptyOption:"请选择",
				hasEmpty:false,
				value:"",
				param:{},
				data:{}
		};
		data = $.extend({},defaults,data);
		cybClient.ajax({
			url:cybClient.fullPath(data.url),
			data:data.param,
			success:function(result){				
				if( result == undefined ){
					return;
				}
				if( result.result == 'success'){
					data.data = result.resultText;
					fill(data);
				}
			}
		});
	};
	
	$.fn.department = function(options){
		var defaults = {
			url:"/department/ajaxList.shtml",
			textField:"cdepName",
			valueField:"cdepId",
			target:$(this),
			emptyOption:"请选择",
			value:"",
			param:{},
			data:{}
		};
		
		options = $.extend({},defaults,options);
		
		cybClient.ajax({
			url:cybClient.fullPath(options.url),
			data:options.param,
			success:function(result){				
				if( result == undefined ){
					return;
				}
				if( result.result == 'success'){
					options.data = result.resultText;
					fill(options);
					$(options["target"]).change(function(){
						var $next = $(this).parent().parent().next().find('select');//.next();
						while( $next != undefined && $next[0].tagName != 'SELECT'){
							$next = $next.parent().parent().next().find('select');//.next();
						}
						if( $(this).val() == "" ){
							$next.empty();
							return;
						}
						if( $next != undefined ){
							var _opts = $.extend({},options,{data:$(this).data(""+$(this).val()),target:$next});
							fill(_opts);
						}
					});
					
					if( $(options["target"]).val() == "" ){
						for( var i in options.data ){
							var dt = options.data[i];
							for( var j in dt.childrens ){
								var dt2 = dt.childrens[j];
								if( dt2[options["valueField"]] == options["value"] ){
									$(options["target"]).find("option[value='"+dt[options["valueField"]]+"']").attr("selected","selected");
									$(options["target"]).change();
								}
							}
						}
					}else{
						$(options["target"]).change();
					}
				}
			}
		});
		
	};
	/**
	 * @public
	 * @description 加载角色
	 */
	$.fn.role = function(options){
		var defaults = {
				url:"/roles/list.shtml",
				textField:"croleName",
				valueField:"croleId",
				target:$(this),
				emptyOption:"请选择",
				value:"",
				param:{},
				data:{}
		};
		options = $.extend({},defaults,options);
		cybClient.ajax({
			url:cybClient.fullPath(options.url),
			data:options.param,
			success:function(result){		
				if( result == undefined ){
					return;
				}
				if( result.result == 'success'){
					options.data = result.resultText;
					fill(options);
				}
			}
		});
	};
	
	/**
	 * @public
	 * @description 加载客服组
	 */
	$.fn.cgroupResult = function(data){
		var defaults = {
				url:"/customergroup/getCgroupByMtsId.shtml",
				textField:"cgroupName",
				valueField:"id",
				target:$(this),
				emptyOption:"",
				hasEmpty:false,
				value:"",
				param:{},
				data:{}
		};
		data = $.extend({},defaults,data);
		cybClient.ajax({
			url:cybClient.fullPath(data.url),
			data:data.param,
			success:function(result){				
				if( result == undefined ){
					return;
				}
				if( result.result == 'success'){
					data.data = result.resultText;
					fill(data);
				}
			}
		});
	};
	
	
	/**
	 * @public
	 * @param data 填充的数据
	 * @description 填充select
	 */
	function fill(data){
		var _target = $(data["target"]);
		var _data = data.data;
		var _value = data.value;
		_target.empty();
		if(data.hasEmpty == undefined || data.hasEmpty){
			_target.append("<option value=''>"+data.emptyOption+"</option>");
		}		
	
		for( var i = 0; i < _data.length ;i++ ){
			var _item = _data[i];
			var option = $("<option title="+_item[data.textField]+" value='"+_item[data.valueField]+"'>"+_item[data.textField]+"</option>");
			if( _item[data.valueField] == _value ){
				option.attr("selected","selected");
				data.value = "";
			}
			if( _item.childrens != undefined ){
				var key = _item[data.valueField];
				_target.data(""+key,_item.childrens);
			}
			_target.append(option);
		}
	}
	
	/**
	 * 让select赋值为空
	 * @param data
	 * @returns
	 */
	function emptySelect(data){
		var _target = $(data["target"]);
		_target.empty();
		if(data.hasEmpty == undefined || data.hasEmpty){
			_target.append("<option value=''>"+data.emptyOption+"</option>");
		}	
	}
	
})(jQuery);