var categoryStore = Ext.create('Ext.data.Store', {
    fields: ['cat'],
    data : [
        {"cat":"Bankruptcy"},
		{"cat":"Real Estate"},
		{"cat":"Immigration"},
		{"cat":"Intellectual Property"},
		{"cat":"Family Law"},
		{"cat":"Criminal Law"},
		{"cat":"Other"}
        
    ]
});

var category = Ext.create('Ext.form.ComboBox', {
    fieldLabel: 'Choose Category',
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
	Ext.create('Ext.form.Panel', {
		bodyPadding : '5 5 0',
		labelAlign : 'top',
		width : 500,
		title : 'Make Document',
		frame : true,
		bodyPadding : 10,
		bodyBorder : true,
		url : 'savedocumenttemplate',
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
			height:200,
			blankText : 'Enter the Description of Document',
			emptyText : 'Enter the Description of Document'
		},{
        xtype: 'filefield',
        name: 'documentTemplate',
        fieldLabel: 'Document Template',
        msgTarget: 'side',
        allowBlank: true,
        inputWidth: 400,
        buttonText: 'Select File...'
		},{
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
						success : function(form, action) {
							Ext.Msg.alert('Success',"Document added successfully");
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
		],
		renderTo : 'form-ct'
	});
});