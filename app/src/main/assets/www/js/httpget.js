	
var http = function(datas) {
		return datas;
};

var httpget = function(url, data) {
	
		$.ajax(url, {
			data: data,
			dataType: 'jsonp',
			crossDomain: true,
			success: function(data) {
				if (data) {
					console.log(JSON.stringify(data));
					http(data);
				}
			}
		});
	
	}