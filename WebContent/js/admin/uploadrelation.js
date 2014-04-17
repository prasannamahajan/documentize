
Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.create('Ext.form.Panel', {
		bodyPadding : '5 5 0',
		labelAlign : 'top',
		width :'100%',
		title : 'Upload Relations',
		frame : true,
		bodyPadding : 10,
		bodyBorder : true,
		url : 'insertrelations',
		layout : 'form',
		fieldDefaults : {
			labelAlign : 'top',
			msgTarget : 'side',
			labelWidth : 75
		},
		defaults : {
			anchor : '80%'
		},

		items : [ 
		{
			xtype : 'textareafield',
			fieldLabel : 'Document Parameter relation',
			name : 'relations',
			allowBlank : false,
			grow : true ,
			height:400,
			blankText : 'Enter the relations',
			emptyText : '[docid1]-[param1]-[docid2]-[param2]'
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

				
					form.submit({
						method : 'POST',
						success : function(form, action) {
							Ext.Msg.alert('Success',"Document relations added successfully");
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