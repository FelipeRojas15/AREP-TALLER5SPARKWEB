var app = (function () {

  var add = function() {
      var valor = $("#data").val();
      apiclient.insertData(valor, view);
  }
   var view = function(error, res) {
  	alert(res);
    if(error != null){
        alert("Vuelva a intentar");
        return;
    }
      alert(res);
    }

    return {
  	  insertData: function() {
            add();
      }

    }

 })();