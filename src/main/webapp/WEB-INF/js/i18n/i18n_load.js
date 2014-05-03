function loadProperties() {
		jQuery.i18n.properties({// 加载资浏览器语言对应的资源文
			name:'i18n', // 资源文件名称
			path:'js/i18n/', // 资源文件路径
			mode:'map', // 用 Map 的方式使用资源文件中的值
			callback: function() {// 加载成功后设置显示内容
			} 
		}); 
 }