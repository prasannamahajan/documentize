
var getParameter = function(parameterName){
	try{
var link = window.location.toString();
var array = link.split("?");
var elements=array[1].split("&");
var length = elements.length;
for(var k=0;k<length;k++)
{
var value = elements[k].split("=");
if(value[0]==parameterName)
	return value[1].split("%20").join(" ");
}
return null;
}
	catch(error)
	{
		return null;
	}
	return null;
};

var documentId=getParameter('documentId');

var categoryStore = Ext.create('Ext.data.Store', {
    fields: ['cat'],
    data : [
        {"cat":"Personal"},
		{"cat":"Business"},
		{"cat":"Property"},
		{"cat":"Other"}   
    ]
});

var category = Ext.create('Ext.form.ComboBox', {
    fieldLabel: 'Choose Category',
    name :'documentCategory',
    store: categoryStore,
    queryMode: 'local',
    displayField: 'cat',
    valueField: 'cat',
	allowBlank: false,
	inputWidth: 300,
	blankText: 'Enter Category of Document',
	typeAhead: true,
	emptyText:'Select Category of document'
});

Ext.onReady(function() {
	Ext.QuickTips.init();
var formPn = Ext.create('Ext.form.Panel', {
		bodyPadding : '5 5 0',
		labelAlign : 'top',
		width :'100%',
		title : 'Make Document',
		//frame : true,
		bodyPadding : 10,
		bodyBorder : true,
		url : 'updatedocumentinfo',
		layout : 'form',
		fieldDefaults : {
			labelAlign : 'top',
			msgTarget : 'side',
			labelWidth : 75
		},
		defaults : {
			anchor : '80%'
		},

		// The fields
		defaultType : 'textfield',
		items : [ {
			fieldLabel : 'Document Name',
			name : 'documentName',
			allowBlank : false,
			inputWidth: 300,
			height:50,
			blankText : 'Enter your document name',
			emptyText : 'Enter your document name'
		}, 
		category,
		{
			xtype : 'textareafield',
			fieldLabel : 'Description',
			name : 'documentDescription',
			allowBlank : false,
			grow : true ,
			height : 320,
			blankText : 'Enter the Description of Document',
			emptyText : 'Enter the Description of Document'
		},	
		{
			xtype : 'button',
			text : 'Proceed',
			formBind : false,
			disabled : false,
			width : 100,
			margin: '10 0 0 0',
			handler : function() {
				var form = this.up('form').getForm();
				if (form.isValid()) {
					Ext.Msg.alert('Success',
							"You clicked submit button and valid");
				
					form.submit({
						method : 'POST',
						params : {
							documentId:documentId
						},
						success : function(form, action) {
							Ext.Msg.alert('Success',"Document Updated successfully");
							form.reset();
						},
						failure : function(form, action) {
							Ext.Msg.alert('Failed', "Sorry for inconvinience , see server log");
						},
						waitMsg : "Processing..."
					});
				}
			}
		}
		]
		//,renderTo : 'form-ct'
	});

var Filepanel = Ext.create('Ext.form.Panel', {
	bodyPadding : '5 5 0',
	labelAlign : 'top',
	width :'100%',
	title : 'Files',
	//frame : true,
	bodyPadding : 10,
	bodyBorder : true,
	url : 'updatedocumentfiles',
	layout : 'form',
	fieldDefaults : {
		labelAlign : 'top',
		msgTarget : 'side',
		labelWidth : 75
	},
	defaults : {
		anchor : '80%'
	},

	// The fields
	defaultType : 'textfield',
	items : [ {
    xtype: 'filefield',
    name: 'documentTemplate',
    fieldLabel: 'Document Template',
    msgTarget: 'side',
    allowBlank: true,
    inputWidth: 400,
    buttonText: 'Select File...'
	},
	{
		 xtype: 'filefield',
	        name: 'documentTemplate',
	        fieldLabel: 'Document Json File',
	        msgTarget: 'side',
	        allowBlank: true,
	        inputWidth: 400,
	        buttonText: 'Select File ...'
		
	}
	,
	
	{
		xtype : 'button',
		text : 'Proceed',
		formBind : false,
		disabled : false,
		width : 100,
		margin: '10 0 0 0',
		handler : function() {
			var form = this.up('form').getForm();
			if (form.isValid()) {
				Ext.Msg.alert('Success',
						"You clicked submit button and valid");
			
				form.submit({
					method : 'POST',
					params : {
						documentId:documentId
					},
					success : function(form, action) {
						Ext.Msg.alert('Success',"File Updated successfully");
						form.reset();
					},
					failure : function(form, action) {
						Ext.Msg.alert('Failed', action.result.message);
					},
					waitMsg : "Processing..."
				});
			}
		}
	}
	]
	//,renderTo : 'form-ct'
});

Ext.create('Ext.tab.Panel', {
    width: '100%',
	height: 550,
	frame: true,
    renderTo: 'form-ct',
    items: [
	formPn,
	Filepanel
	]
});

formPn.getForm().load({
    url: '../admin/getdocument',
    params:{
							documentId:documentId
							},
    success:function(form,action)
    {
    	var responseObj = Ext.JSON.decode(action.response.responseText);
    },
    failure: function(form, action) {
        Ext.Msg.alert("Load failed", action.response.responseText);
    }
});
});

