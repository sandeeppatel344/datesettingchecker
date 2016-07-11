
var exec = require('cordova/exec');

function DateSettingsChecker() { 
	console.log("DateSettingsChecker.js: is created");
	//alert("Plugin created DateSettingsChecker....");
}

DateSettingsChecker.prototype.checkDateSettings = function(callback){
	console.log("DateSettingsChecker.js: showToast");
	var aString = "checkDateSettings";
	
	return exec(function(result){
			/* if(result.isNetworkModeSelected){
				alert("Use Network mode selected.");
			}
			else {
				alert("Use Network mode not selected.");
			} */
			callback(result);
		},
		function(result){
			alert("Error - " + result);
			callback(result);
		},"DateSettingsChecker",aString,[]
	);
}

 var dateSettingsChecker = new DateSettingsChecker();
 module.exports = dateSettingsChecker
 