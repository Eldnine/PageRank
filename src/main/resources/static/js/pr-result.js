var curStartUrl = "";

var prResult = new Vue({
	el : '#pr-result',
	data : {
		curStartUrl
	},
	mounted: function () {
		axios.get('/pagerank/spider/currentStartUrl')
		  .then(function (response) {
			prResult.curStartUrl = response.data;
		  })
		  .catch(function (error) {
		    console.log(error);
		  });
		axios.get('/pagerank/result')
		  .then(function (response) {
			console.log(response.data);
		  })
		  .catch(function (error) {
		    console.log(error);
		  });
	}
});
