  
var apiclient = (function(){

    return {
    	insertData: function(palabra, callback) {
            var promise = $.post({
        		url: "/result",
        		data: palabra,
        		contentType: "application/json"
            });
        	promise.then(function(data){
        		callback(null, data);
        	}, function(error) {
        		callback(error, null);
        	});
        }
    }
})();