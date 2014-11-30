Ext.application({
	name : "Owen",
	
	launch : function(){
		Ext.create('Ext.Panel',{
			renderTo : Ext.getBody(),
			width : 200,
			height : 150,
			bodyPadding : 5,
			title : 'Hello world',
			html : 'hello <b>World</b>...'
		});
	}
});