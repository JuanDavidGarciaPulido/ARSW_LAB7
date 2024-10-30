//@author hcadavid

apimock=(function(){

	var mockdata=[];

	// Blueprints para el autor "johnconnor"
	mockdata["johnconnor"] = [
		{author: "johnconnor", points: [{"x":150,"y":120}, {"x":215,"y":115}], name: "house"},
		{author: "johnconnor", points: [{"x":340,"y":240}, {"x":15,"y":215}], name: "gear"}
	];

	// Blueprints para el autor "maryweyland"
	mockdata["maryweyland"] = [
		{author: "maryweyland", points: [{"x":140,"y":140}, {"x":115,"y":115}], name: "house2"},
		{author: "maryweyland", points: [{"x":140,"y":140}, {"x":115,"y":115}], name: "gear2"}
	];

	// Blueprints para el autor "juan"
	mockdata["juan"] = [
		{author: "juan", points: [{"x":180,"y":150}, {"x":115,"y":120}], name: "prueba1"}
	];

	// Blueprints para el autor "david"
	mockdata["david"] = [
		{author: "david", points: [{"x":18,"y":15}, {"x":195,"y":140}], name: "prueba2"}
	];

    function addPoints(x, y, author, bpname, callback){
        var insert = {"x": x, "y":y};
        mockdata[author].find(function(e){return e.name===bpname}).points.push(insert);
        callback();
    }

	return {
	    addPoints : addPoints,

		getBlueprintsByAuthor:function(authname,callback){
			callback(mockdata[authname]);
		},

		getBlueprintByAuthorAndName:function(authname,bpname,callback){

			callback(mockdata[authname].find(function(e){return e.name===bpname}));
		}
	}

})();

/*
Example of use:
var fun=function(list){
	console.info(list);
}

apimock.getBlueprintsByAuthor("johnconnor",fun);
apimock.getBlueprintsByNameAndAuthor("johnconnor","house",fun);*/